package com.github.boybeak.easypermission;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class PermissionAgentFragment extends Fragment {

    private String id = null;
    private String[] permissions;
    private int requestCode = EasyPermission.REQUEST_CODE_DEFAULT;

    void setPermissionsArgs(@NonNull String id, int requestCode, List<String> permissions) {
        this.id = id;
        this.requestCode = requestCode;

        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        this.permissions = permissionArray;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (id != null && permissions != null && permissions.length > 0) {
            requestPermissions(permissions, requestCode);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        EasyPermission.actionPermissionResult(id, requireActivity(), requestCode, permissions, grantResults);

        getFragmentManager().beginTransaction().remove(this).commitNowAllowingStateLoss();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        id = null;
        permissions = null;
    }
}