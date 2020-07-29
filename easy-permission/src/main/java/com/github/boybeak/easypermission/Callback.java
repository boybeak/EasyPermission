package com.github.boybeak.easypermission;

import androidx.annotation.NonNull;

import java.util.List;

public interface Callback {
    void onGranted(@NonNull List<String> permissions, int requestCode);
    void onDenied(@NonNull String permission, int requestCode, boolean neverAsk);
}
