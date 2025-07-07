package com.emsafe.platform.em.interfaces.rest.transform;

import com.emsafe.platform.em.domain.model.aggregates.profile.dto.*;
import com.emsafe.platform.em.application.profileimpl.ProfileApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/profile", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Tag(name = "Profile", description = "Profile management")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileApplicationService profileService;

    private String getUsernameFromContext() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new RuntimeException("Usuario no autenticado");
        }
        return auth.getName();
    }

    @Operation(summary = "Get username", description = "Returns the username of the authenticated user")
    @ApiResponse(responseCode = "200", description = "Username found",
            content = @Content(schema = @Schema(implementation = UsernameResponseDto.class)))
    @GetMapping("/username")
    public ResponseEntity<UsernameResponseDto> getUsername() {
        return ResponseEntity.ok(profileService.getUsernameDto(getUsernameFromContext()));
    }

    @Operation(summary = "Get full name", description = "Returns the full name of the authenticated user")
    @ApiResponse(responseCode = "200", description = "Full name found",
            content = @Content(schema = @Schema(implementation = NameResponseDto.class)))
    @GetMapping("/name")
    public ResponseEntity<NameResponseDto> getFullName() {
        return ResponseEntity.ok(profileService.getFullNameDto(getUsernameFromContext()));
    }

    @Operation(summary = "Get email", description = "Returns the email of the authenticated user")
    @ApiResponse(responseCode = "200", description = "Email found",
            content = @Content(schema = @Schema(implementation = EmailResponseDto.class)))
    @GetMapping("/email")
    public ResponseEntity<EmailResponseDto> getEmail() {
        return ResponseEntity.ok(profileService.getEmailDto(getUsernameFromContext()));
    }

    @Operation(summary = "Validate current password", description = "Validates the current password of the user")
    @ApiResponse(responseCode = "200", description = "Password validation result",
            content = @Content(schema = @Schema(implementation = PasswordValidationResponseDto.class)))
    @PostMapping("/validate-password")
    public ResponseEntity<PasswordValidationResponseDto> validatePassword(
            @Valid @RequestBody PasswordValidationRequestDto request) {
        return ResponseEntity.ok(profileService.validatePasswordDto(getUsernameFromContext(), request));
    }

    @Operation(summary = "Change password", description = "Changes the password of the user")
    @ApiResponse(responseCode = "200", description = "Password changed successfully",
            content = @Content(schema = @Schema(implementation = PasswordChangeResponseDto.class)))
    @PutMapping("/change-password")
    public ResponseEntity<PasswordChangeResponseDto> changePassword(
            @Valid @RequestBody PasswordChangeRequestDto request) {
        return ResponseEntity.ok(profileService.changePasswordDto(getUsernameFromContext(), request));
    }
}
