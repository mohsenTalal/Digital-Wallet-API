package com.mohsen.walletapp.enums;

import com.google.common.collect.Sets;

import java.util.Set;

public enum Roles {
    ADMIN(Sets.newHashSet(UserPermissions.USER_READ, UserPermissions.USER_EDIT, UserPermissions.ACCOUNT_EDIT, UserPermissions.ACCOUNT_READ)),
    USER(Sets.newHashSet(UserPermissions.USER_READ, UserPermissions.USER_EDIT));

    private final Set<UserPermissions> permissions;

    Roles(Set<UserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermissions> getPermissions() {
        return permissions;
    }
}
