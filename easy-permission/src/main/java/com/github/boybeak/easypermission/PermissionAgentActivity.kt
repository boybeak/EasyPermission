package com.github.boybeak.easypermission

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat

class PermissionAgentActivity : Activity() {

    private var id: String? = null
    private var permissions: Array<String>? = null

    private var requestCode: Int = 0
    private var grantResults: IntArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(View(this))

        id = intent.getStringExtra(EasyPermission.KEY_ID)
        requestCode = intent.getIntExtra(EasyPermission.KEY_REQUEST_CODE, 127)
        permissions = intent.getStringArrayListExtra(EasyPermission.KEY_PERMISSION_LIST)!!.toTypedArray()

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            grantResults = IntArray(permissions!!.size) {
                ActivityCompat.checkSelfPermission(this, permissions!![it])
            }
            finish()
        } else {
            ActivityCompat.requestPermissions(this, permissions!!, requestCode)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        this.requestCode = requestCode
        this.grantResults = grantResults

        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        EasyPermission.actionPermissionResult(id, this, requestCode, permissions!!, grantResults!!)
        /*var result = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            grantResults!!.forEachIndexed { index, i ->
                result = result and (i == PackageManager.PERMISSION_GRANTED)
                if (!result) {
                    val permission = permissions!![index]
                    val neverAsk = !ActivityCompat.shouldShowRequestPermissionRationale(this, permission)
                    EasyPermission.actionDenied(id, requestCode, permission, neverAsk)
                    return
                }
            }
        }
        if (result) {
            EasyPermission.actionGranted(id, requestCode, permissions)
        }*/
    }

}
