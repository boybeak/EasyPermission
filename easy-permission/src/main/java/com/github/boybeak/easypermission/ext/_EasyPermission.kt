package com.github.boybeak.easypermission.ext

import android.content.Context
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.github.boybeak.easypermission.Callback
import com.github.boybeak.easypermission.EasyPermission

fun Context.withPermission(
    requestCode: Int = EasyPermission.REQUEST_CODE_DEFAULT,
    permission: String,
    onGranted: (permissions: List<String>) -> Unit
) {
    withPermission(requestCode, permission, onGranted, null)
}

fun Context.withPermission(
    requestCode: Int = EasyPermission.REQUEST_CODE_DEFAULT,
    permission: String,
    onGranted: (permissions: List<String>) -> Unit,
    onDenied: ((permission: String, neverAsk: Boolean) -> Unit)?
) {
    withPermissions(requestCode, permission, onGranted = onGranted, onDenied = onDenied)
}

fun Context.withPermissions(
    requestCode: Int = EasyPermission.REQUEST_CODE_DEFAULT,
    vararg permissions: String,
    onGranted: (permissions: List<String>) -> Unit
) {
    withPermissions(requestCode, *permissions, onGranted = onGranted, onDenied = null)
}

fun Context.withPermissions(
    requestCode: Int = EasyPermission.REQUEST_CODE_DEFAULT,
    vararg permissions: String,
    onGranted: (permissions: List<String>) -> Unit,
    onDenied: ((permission: String, neverAsk: Boolean) -> Unit)?
) {
    EasyPermission.ask(*permissions).go(this, requestCode, object : Callback {
        override fun onGranted(permissions: MutableList<String>, requestCode: Int) {
            onGranted.invoke(permissions)
        }

        override fun onDenied(
            permission: String,
            requestCode: Int,
            neverAsk: Boolean
        ) {
            onDenied?.invoke(permission, neverAsk)
        }
    })
}

fun Context.withPermissionList(
    requestCode: Int = EasyPermission.REQUEST_CODE_DEFAULT,
    permissions: List<String>,
    onGranted: (permissions: List<String>) -> Unit
) {
    withPermissionList(requestCode, permissions, onGranted, null)
}

fun Context.withPermissionList(
    requestCode: Int = EasyPermission.REQUEST_CODE_DEFAULT,
    permissions: List<String>,
    onGranted: (permissions: List<String>) -> Unit,
    onDenied: ((permission: String, neverAsk: Boolean) -> Unit)?
) {

    EasyPermission.ask(permissions).go(this, requestCode, object : Callback {
        override fun onGranted(permissions: MutableList<String>, requestCode: Int) {
            onGranted.invoke(permissions)
        }

        override fun onDenied(
            permission: String,
            requestCode: Int,
            neverAsk: Boolean
        ) {
            onDenied?.invoke(permission, neverAsk)
        }
    })
}

fun Fragment.withPermissionList(
    requestCode: Int = EasyPermission.REQUEST_CODE_DEFAULT,
    permissions: List<String>,
    onGranted: (permissions: List<String>) -> Unit,
    onDenied: ((permission: String, neverAsk: Boolean) -> Unit)?
) {
    requireContext().withPermissionList(requestCode, permissions, onGranted, onDenied)
}

fun Fragment.withPermissionList(
    requestCode: Int = EasyPermission.REQUEST_CODE_DEFAULT,
    permissions: List<String>,
    onGranted: (permissions: List<String>) -> Unit
) {
    requireContext().withPermissionList(requestCode, permissions, onGranted)
}

fun Fragment.withPermission(
    requestCode: Int = EasyPermission.REQUEST_CODE_DEFAULT,
    permission: String,
    onGranted: (permissions: List<String>) -> Unit
) {
    requireContext().withPermission(requestCode, permission, onGranted)
}

fun Fragment.withPermission(
    requestCode: Int = EasyPermission.REQUEST_CODE_DEFAULT,
    permission: String,
    onGranted: (permissions: List<String>) -> Unit,
    onDenied: ((permission: String, neverAsk: Boolean) -> Unit)?
) {
    requireContext().withPermission(requestCode, permission, onGranted, onDenied)
}

fun Fragment.withPermissions(
    requestCode: Int = EasyPermission.REQUEST_CODE_DEFAULT,
    vararg permissions: String,
    onGranted: (permissions: List<String>) -> Unit
) {
    requireContext().withPermissions(requestCode, *permissions, onGranted = onGranted)
}

fun Fragment.withPermissions(
    requestCode: Int = EasyPermission.REQUEST_CODE_DEFAULT,
    vararg permissions: String,
    onGranted: (permissions: List<String>) -> Unit,
    onDenied: ((permission: String, neverAsk: Boolean) -> Unit)?
) {
    requireContext().withPermissions(requestCode, *permissions, onGranted = onGranted, onDenied = onDenied)
}

fun Context.isPermissionsGranted(vararg permissions: String): Boolean {
    return EasyPermission.isPermissionGranted(this, *permissions)
}

fun Context.isPermissionsGranted(permissions: List<String>): Boolean {
    return EasyPermission.isPermissionGranted(this, permissions)
}