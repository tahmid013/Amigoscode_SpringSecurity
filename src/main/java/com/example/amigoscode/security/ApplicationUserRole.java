package com.example.amigoscode.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.amigoscode.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet(
            STUDENT_READ
    )),
    ADMIN(Sets.newHashSet(
            COURSE_READ,
            COURSE_WRITE,
            STUDENT_READ,
            STUDENT_WRITE
    )),
    ADMIN_TRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ));
    private final Set<ApplicationUserPermission> curRolePermissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.curRolePermissions = permissions;
    }
    public Set<ApplicationUserPermission> getCurRolePermissions(){
        return curRolePermissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permissions = getCurRolePermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
