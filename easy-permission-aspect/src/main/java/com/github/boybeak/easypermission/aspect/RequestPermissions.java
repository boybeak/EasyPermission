package com.github.boybeak.easypermission.aspect;

import com.github.boybeak.easypermission.EasyPermission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestPermissions {
    String[] permissions();
    int requestCode() default EasyPermission.REQUEST_CODE_DEFAULT;
}
