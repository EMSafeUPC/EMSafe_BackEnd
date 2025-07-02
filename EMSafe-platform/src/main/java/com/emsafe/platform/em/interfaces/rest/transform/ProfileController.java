package com.emsafe.platform.em.interfaces.rest.transform;

import com.emsafe.platform.em.domain.model.aggregates.profile.dto.*;
import com.emsafe.platform.em.application.profileimpl.ProfileApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@Tag(name = "Profile", description = "Profile management")
public class ProfileController {

    private final ProfileApplicationService profileService;

    public ProfileController(ProfileApplicationService profileService) {
        this.profileService = profileService;
    }

    @Operation(summary = "Get current username", description = "Returns the username of the authenticated user")
    @ApiResponse(responseCode = "200", description = "Username found",
            content = @Content(schema = @Schema(implementation = UsernameResponseDto.class)))
    @GetMapping("/username")
    public UsernameResponseDto getUsername(Authentication auth) {
        return profileService.getUsernameDto(auth.getName());
    }

    @Operation(summary = "Get full name", description = "Returns the full name of the authenticated user")
    @ApiResponse(responseCode = "200", description = "Full name found",
            content = @Content(schema = @Schema(implementation = NameResponseDto.class)))
    @GetMapping("/name")
    public NameResponseDto getFullName(Authentication auth) {
        return profileService.getFullNameDto(auth.getName());
    }

    @Operation(summary = "Get email", description = "Returns the email of the authenticated user")
    @ApiResponse(responseCode = "200", description = "Email found",
            content = @Content(schema = @Schema(implementation = EmailResponseDto.class)))
    @GetMapping("/email")
    public EmailResponseDto getEmail(Authentication auth) {
        return profileService.getEmailDto(auth.getName());
    }

    @Operation(summary = "Validate current password", description = "Validates the current password of the user")
    @ApiResponse(responseCode = "200", description = "Password validation result",
            content = @Content(schema = @Schema(implementation = PasswordValidationResponseDto.class)))
    @PostMapping("/validate-password")
    public PasswordValidationResponseDto validatePassword(
            Authentication auth,
            @Valid @RequestBody PasswordValidationRequestDto request) {
        return profileService.validatePasswordDto(auth.getName(), request);
    }

    @Operation(summary = "Change password", description = "Updates the password of the user")
    @ApiResponse(responseCode = "200", description = "Password changed successfully",
            content = @Content(schema = @Schema(implementation = PasswordChangeResponseDto.class)))
    @PutMapping("/change-password")
    public PasswordChangeResponseDto changePassword(
            Authentication auth,
            @Valid @RequestBody PasswordChangeRequestDto request) {
        return profileService.changePasswordDto(auth.getName(), request);
    }
}
