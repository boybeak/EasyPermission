package com.github.boybeak.easypermission

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.github.boybeak.easypermission.ext.withPermissionList
import com.github.boybeak.easypermission.ext.withPermissions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun ask(v: View) {
        EasyPermission.ask(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION
        ).go(this, object : Callback {
            override fun onGranted(permissions: MutableList<String>) {
                Toast.makeText(this@MainActivity, "Granted", Toast.LENGTH_SHORT).show()
            }

            override fun onDenied(
                permission: String,
                shouldShowRequestPermissionRationale: Boolean
            ) {
                Toast.makeText(this@MainActivity, "Denied $permission", Toast.LENGTH_SHORT).show()
            }

        })
    }

}
