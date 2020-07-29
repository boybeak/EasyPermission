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

    private val clickListener = object : View.OnClickListener {

        @RequestPermissions(requestCode = 100, permissions = [Manifest.permission.CAMERA])
        private fun showT(context: Context) {
            Toast.makeText(this@MainActivity, "get permission success $context", Toast.LENGTH_SHORT)
                .show()
        }

        @OnPermissionDenied
        private fun onDenied(permission: String, requestCode: Int, neverAsk: Boolean) {
            Toast.makeText(this@MainActivity, "$permission $requestCode", Toast.LENGTH_SHORT).show()
        }

        override fun onClick(v: View?) {
            showT(v!!.context)
        }
    }

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
    fun getLocation() {
        //TODO("GET LOCATION")
    }

    @OnPermissionDenied
    fun onGetLocationDenied(permission: String, requestCode: Int, neverAsk: Boolean) {
        //TODO("Permission denied")
    }

}
