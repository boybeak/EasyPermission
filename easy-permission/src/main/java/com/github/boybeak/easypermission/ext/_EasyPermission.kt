package com.github.boybeak.easypermission.ext

import android.content.Context
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
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

fun Fragment.withPermissionList(permissions: List<String>,
                                onGranted: (permissions: List<String>) -> Unit,
                                onDenied: ((permission: String, shouldShowRequestPermissionRationale: Boolean) -> Unit)?) {
    requireContext().withPermissionList(permissions, onGranted, onDenied)
}

fun Fragment.withPermissionList(
    permissions: List<String>,
    onGranted: (permissions: List<String>) -> Unit
) {
    requireContext().withPermissionList(permissions, onGranted)
}

fun Fragment.withPermission(permission: String,
                           onGranted: (permissions: List<String>) -> Unit) {
    requireContext().withPermission(permission, onGranted)
}
fun Fragment.withPermission(permission: String,
                           onGranted: (permissions: List<String>) -> Unit,
                           onDenied: ((permission: String, shouldShowRequestPermissionRationale: Boolean) -> Unit)?) {
    requireContext().withPermission(permission, onGranted, onDenied)
}

fun Fragment.withPermissions(
    vararg permissions: String,
    onGranted: (permissions: List<String>) -> Unit
) {
    requireContext().withPermissions(*permissions, onGranted = onGranted)
}
fun Fragment.withPermissions(
    vararg permissions: String,
    onGranted: (permissions: List<String>) -> Unit,
    onDenied: ((permission: String, shouldShowRequestPermissionRationale: Boolean) -> Unit)?
) {
    requireContext().withPermissions(*permissions, onGranted = onGranted, onDenied = onDenied)
}

fun Context.isPermissionsGranted(vararg permissions: String): Boolean {
    return EasyPermission.isPermissionGranted(this, *permissions)
}

fun Context.isPermissionsGranted(permissions: List<String>): Boolean {
    return EasyPermission.isPermissionGranted(this, permissions)
}