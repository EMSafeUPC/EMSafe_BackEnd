package com.emsafe.platform.em.application.profileimpl.services;

import com.emsafe.platform.auth.user.User;
import com.emsafe.platform.auth.user.UserRepository;
import com.emsafe.platform.em.application.profileimpl.dto.*;
import com.emsafe.platform.em.domain.services.profile.ProfileService;
import org.springframework.stereotype.Service;

@Service
public class ProfileApplicationService {

    private final UserRepository userRepository;
    private final ProfileService profileService;

    public ProfileApplicationService(UserRepository userRepository, ProfileService profileService) {
        this.userRepository = userRepository;
        this.profileService = profileService;
    }

    public String getFullName(String username) {
        User user = getUserByUsername(username);
        return user.getFirstName() + " " + user.getLastName();
    }

    public String getEmail(String username) {
        User user = getUserByUsername(username);
        return user.getEmail();
    }

    public String getUsername(String username) {
        User user = getUserByUsername(username);
        return user.getUsername();
    }

    public ProfileResponseDto getProfile(String username) {
        User user = getUserByUsername(username);
        return mapToDto(user);
    }

    public ProfileResponseDto updateProfile(String username, UpdateProfileRequestDto request) {
        User user = getUserByUsername(username);
        profileService.updateInfo(user, request.getName(), request.getEmail());
        userRepository.save(user);
        return mapToDto(user);
    }

    public boolean isCurrentPasswordValid(String username, PasswordValidationRequestDto request) {
        User user = getUserByUsername(username);
        try {
            profileService.validatePassword(user, request.getCurrentPassword());
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public void changePassword(String username, PasswordChangeRequestDto request) {
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("New password and confirmation do not match.");
        }

        User user = getUserByUsername(username);
        profileService.changePassword(user, request.getNewPassword());
        userRepository.save(user);
    }

    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private ProfileResponseDto mapToDto(User user) {
        return ProfileResponseDto.builder()
                .id(Math.toIntExact(user.getId()))
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getFirstName() + " " + user.getLastName())
                .role(user.getRole().name())
                .build();
    }

    public UsernameResponseDto getUsernameDto(String username) {
        return UsernameResponseDto.builder().username(getUsername(username)).build();
    }

    public NameResponseDto getFullNameDto(String username) {
        return NameResponseDto.builder().name(getFullName(username)).build();
    }

    public EmailResponseDto getEmailDto(String username) {
        return EmailResponseDto.builder().email(getEmail(username)).build();
    }

    public PasswordValidationResponseDto validatePasswordDto(String username, PasswordValidationRequestDto request) {
        boolean valid = isCurrentPasswordValid(username, request);
        return PasswordValidationResponseDto.builder().valid(valid).build();
    }

    public PasswordChangeResponseDto changePasswordDto(String username, PasswordChangeRequestDto request) {
        changePassword(username, request);
        return PasswordChangeResponseDto.builder().success(true).build();
    }
}
