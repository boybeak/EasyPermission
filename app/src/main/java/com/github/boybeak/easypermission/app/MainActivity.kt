package com.github.boybeak.easypermission.app

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.github.boybeak.easypermission.Callback
import com.github.boybeak.easypermission.EasyPermission

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun getPermission(v: View) {
        EasyPermission.ask(Manifest.permission.ACCESS_FINE_LOCATION)
            .go(this, object : Callback {
                override fun onGranted(permissions: MutableList<String>, requestCode: Int) {
                }

                override fun onDenied(permission: String, requestCode: Int, neverAsk: Boolean) {

                }
            })
    }

    /*@RequestPermissions(
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
    }*/

}
