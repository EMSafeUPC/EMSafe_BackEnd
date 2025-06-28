package com.emsafe.platform.em.application.profileimpl.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordValidationRequestDto {

    @NotBlank(message = "Current password is required")
    private String currentPassword;
}
