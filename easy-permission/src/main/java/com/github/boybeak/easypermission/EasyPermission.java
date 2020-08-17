package com.github.boybeak.easypermission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EasyPermission {

    private static final String KEY_ = "com.github.boybeak.easypermission.";
    public static final String KEY_ID = KEY_ + "ID", KEY_PERMISSION_LIST = KEY_ + "PERMISSION_LIST",
            KEY_REQUEST_CODE = KEY_ + "REQUEST_CODE";
    public static final int REQUEST_CODE_DEFAULT = 127;

    private static final String TAG_PERMISSION_FRAGMENT = "com.github.boybeak.easypermission.TAG_PERMISSION_FRAGMENT";

    private static Map<String, Callback> sKeyCallbackMap = new HashMap<>();

    private static void actionGranted(String id, int requestCode, List<String> permissions) {
        Callback callback = sKeyCallbackMap.get(id);
        if (callback != null) {
            callback.onGranted(permissions, requestCode);
        }
    }

    private static void actionDenied(String id, int requestCode, String permission, boolean neverAsk) {
        Callback callback = sKeyCallbackMap.get(id);
        if (callback != null) {
            callback.onDenied(permission, requestCode, neverAsk);
        }
    }

    static void actionPermissionResult(String id, Activity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean result = true;
        boolean neverAsk = false;
        String permission = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = 0; i < grantResults.length; i++) {
                int r = grantResults[i];
                result = r == PackageManager.PERMISSION_GRANTED;
                if (!result) {
                    permission = permissions[i];
                    neverAsk = !ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
                    break;
                }
            }
        }
        if (result) {
            actionGranted(id, requestCode, Arrays.asList(permissions));
        } else {
            actionDenied(id, requestCode, permission, neverAsk);
        }
        releaseCallback(id);
    }

    public static boolean isPermissionGranted(Context context, String ... permissions) {
        return isPermissionGranted(context, Arrays.asList(permissions));
    }

    public static boolean isPermissionGranted(Context context, List<String> permissions) {
        for (String p : permissions) {
            int result = ContextCompat.checkSelfPermission(context, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private static void releaseCallback(String id) {
        sKeyCallbackMap.remove(id);
    }

    public static EasyPermission ask(String ... permissions) {
        return ask(Arrays.asList(permissions));
    }

    public static EasyPermission ask(List<String> permissions) {
        if (permissions.isEmpty()) {
            throw new IllegalArgumentException("permission list should not be empty");
        }
        EasyPermission ph = new EasyPermission();
        ph.permissions.addAll(permissions);
        return ph;
    }

    private ArrayList<String> permissions = new ArrayList<>();

    private EasyPermission() {

    }

    public void go(Context context, Callback callback) {
        go(context, REQUEST_CODE_DEFAULT, callback);
    }

    public void go(Context context, int requestCode, Callback callback) {

        if (EasyPermission.isPermissionGranted(context, permissions)) {
            callback.onGranted(permissions, requestCode);
            return;
        }

        String id = UUID.randomUUID().toString();

        if (context instanceof FragmentActivity) {
            FragmentActivity fa = (FragmentActivity)context;
            FragmentManager fm = fa.getSupportFragmentManager();
            PermissionAgentFragment agentFragment = (PermissionAgentFragment)fm.findFragmentByTag(TAG_PERMISSION_FRAGMENT);
            if (agentFragment == null) {
                agentFragment = new PermissionAgentFragment();
                agentFragment.setPermissionsArgs(id, requestCode, permissions);
                fm.beginTransaction().add(agentFragment, TAG_PERMISSION_FRAGMENT)
                        .commitNowAllowingStateLoss();
            } else {
                String[] permissionArray = new String[permissions.size()];
                permissions.toArray(permissions.toArray());
                agentFragment.requestPermissions(permissionArray, requestCode);
            }
        } else {
            Intent it = new Intent(context, PermissionAgentActivity.class)
                    .putExtra(KEY_ID, id)
                    .putStringArrayListExtra(KEY_PERMISSION_LIST, permissions)
                    .putExtra(KEY_REQUEST_CODE, requestCode);
            context.startActivity(it);
        }

        sKeyCallbackMap.put(id, callback);

    }

}
