package com.emsafe.platform.em.application.profileimpl.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordValidationResponseDto {
    private boolean valid;
}
