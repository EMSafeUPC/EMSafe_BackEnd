package com.emsafe.platform.em.application.profileimpl.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponseDto {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String role;
}
