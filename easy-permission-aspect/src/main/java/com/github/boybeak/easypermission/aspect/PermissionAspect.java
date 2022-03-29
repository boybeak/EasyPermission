package com.github.boybeak.easypermission.aspect;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.boybeak.easypermission.Callback;
import com.github.boybeak.easypermission.EasyPermission;

//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

//@Aspect
public class PermissionAspect {

    private static final String TAG = PermissionAspect.class.getSimpleName();

    private static final String pointcutExpression = "execution(@com.github.boybeak.easypermission.aspect.RequestPermissions * *(..)) && @annotation(permissions)";

    /*@Pointcut(value = pointcutExpression, argNames = "permissions")
    public void requestPermissions(RequestPermissions permissions) {

    }

    @Around("requestPermissions(permissions)")
    public void doRequestPermissions(final ProceedingJoinPoint joinPoint, RequestPermissions permissions) {
        EasyPermission.ask(permissions.permissions()).go(getContext(joinPoint), permissions.requestCode(),
                new Callback() {
                    @Override
                    public void onGranted(@NonNull List<String> permissions, int requestCode) {
                        try {
                            joinPoint.proceed();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void onDenied(@NonNull String permission, int requestCode, boolean neverAsk) {
                        invokeOnDenied(joinPoint.getThis(), permission, requestCode, neverAsk);
                    }
                });
    }

    private Context getContext(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Context cxt = null;
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                Context c = getContext(arg);
                if (c != null) {
                    cxt = c;
                    break;
                }
            }
        }
        if (cxt == null) {
            cxt = getContext(joinPoint.getThis());
        }
        if (cxt == null) {
            throw new IllegalStateException("Context not found, because ProceedingJoinPoint.getThis() == " + joinPoint.getThis().getClass().getName());
        }
        return cxt;
    }

    private Context getContext(Object obj) {
        if (obj instanceof Context) {
            return (Context) obj;
        } else if (obj instanceof Fragment) {
            return ((Fragment) obj).getContext();
        } else if (obj instanceof android.app.Fragment) {
            return ((android.app.Fragment) obj).getActivity();
        } else if (obj instanceof View) {
            return ((View) obj).getContext();
        }
        return null;
    }


    private void invokeOnDenied(Object obj, String permission, int requestCode, boolean neverAsk) {
        Method method = getMethodFor(obj, OnPermissionDenied.class);
        if (method != null) {
            try {
                Class<?>[] parameters = method.getParameterTypes();
                if (parameters.length != 3 || parameters[0] != String.class || parameters[1] != int.class || parameters[2] != boolean.class) {
                    throw new IllegalArgumentException("The method parameters must be (String permission, int requestCode, boolean neverAsk)");
                }

                method.setAccessible(true);
                method.invoke(obj, permission, requestCode, neverAsk);
                method.setAccessible(false);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private Method getMethodFor(Object obj, Class<? extends Annotation> annotationClz) {
        for (Method method : obj.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClz)) {
                return method;
            }
        }
        return null;
    }*/

}
