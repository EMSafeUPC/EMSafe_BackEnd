package com.emsafe.platform.em.domain.services.profile;

import com.emsafe.platform.auth.user.User;

public interface ProfileService {
    void validatePassword(User user, String currentPassword);
    void changePassword(User user, String newPassword);
    void updateInfo(User user, String name, String email);
}
