package com.github.boybeak.easypermission.app;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RequestPermissionFragment extends Fragment {

    private static final String TAG = RequestPermissionFragment.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "FM=" + getFragmentManager().hashCode());
        requestPermissions(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION
        }, 123);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.v(TAG, "onRequestPermissionsResult FM=" + getFragmentManager().hashCode());
    }
}
