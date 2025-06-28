package com.emsafe.platform.em.application.profileimpl.services;

import com.emsafe.platform.auth.user.User;
import com.emsafe.platform.em.domain.services.profile.ProfileService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final PasswordEncoder passwordEncoder;

    public ProfileServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void validatePassword(User user, String currentPassword) {
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
    }

    @Override
    public void updateInfo(User user, String name, String email) {
        if (StringUtils.hasText(name)) {
            user.setFirstName(name);
        }
        if (StringUtils.hasText(email)) {
            user.setEmail(email);
        }
    }
}
