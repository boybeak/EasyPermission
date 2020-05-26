package com.github.boybeak.easypermission.ext

import android.content.Context
import com.github.boybeak.easypermission.Callback
import com.github.boybeak.easypermission.EasyPermission

fun Context.withPermission(permission: String,
                           onGranted: (permissions: List<String>) -> Unit) {
    withPermission(permission, onGranted, null)
}
fun Context.withPermission(permission: String,
                           onGranted: (permissions: List<String>) -> Unit,
                           onDenied: ((permission: String, shouldShowRequestPermissionRationale: Boolean) -> Unit)?) {
    withPermissions(permission, onGranted = onGranted, onDenied = onDenied)
}

fun Context.withPermissions(
    vararg permissions: String,
    onGranted: (permissions: List<String>) -> Unit
) {
    withPermissions(*permissions, onGranted = onGranted, onDenied = null)
}
fun Context.withPermissions(
    vararg permissions: String,
    onGranted: (permissions: List<String>) -> Unit,
    onDenied: ((permission: String, shouldShowRequestPermissionRationale: Boolean) -> Unit)?
) {
    EasyPermission.ask(*permissions).go(this, object : Callback {
        override fun onGranted(permissions: MutableList<String>) {
            onGranted.invoke(permissions)
        }

        override fun onDenied(permission: String, shouldShowRequestPermissionRationale: Boolean) {
            onDenied?.invoke(permission, shouldShowRequestPermissionRationale)
        }
    })
}

fun Context.withPermissionList(
    permissions: List<String>,
    onGranted: (permissions: List<String>) -> Unit
) {
    withPermissionList(permissions, onGranted, null)
}
fun Context.withPermissionList(
    permissions: List<String>,
    onGranted: (permissions: List<String>) -> Unit,
    onDenied: ((permission: String, shouldShowRequestPermissionRationale: Boolean) -> Unit)?
) {

    EasyPermission.ask(permissions).go(this, object : Callback {
        override fun onGranted(permissions: MutableList<String>) {
            onGranted.invoke(permissions)
        }

        override fun onDenied(permission: String, shouldShowRequestPermissionRationale: Boolean) {
            onDenied?.invoke(permission, shouldShowRequestPermissionRationale)
        }
    })
}