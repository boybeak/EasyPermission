package com.github.boybeak.easypermission

import android.Manifest
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.github.boybeak.easypermission.aspect.OnPermissionDenied
import com.github.boybeak.easypermission.aspect.RequestPermissions
import com.github.boybeak.easypermission.ext.withPermissionList
import com.github.boybeak.easypermission.ext.withPermissions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    @RequestPermissions(
        permissions = [
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ]
    )
    fun getLocation(view: View) {
        Toast.makeText(this, classLoader::class.java.name, Toast.LENGTH_SHORT).show()
    }

    @OnPermissionDenied
    fun onGetLocationDenied(permission: String, requestCode: Int, neverAsk: Boolean) {
        Toast.makeText(this, "$permission $requestCode $neverAsk", Toast.LENGTH_SHORT).show()
    }

}
