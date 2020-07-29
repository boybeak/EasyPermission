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
            Toast.makeText(this@MainActivity, "get permission success $context", Toast.LENGTH_SHORT).show()
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

    fun ask(v: View) {
        clickListener.onClick(v)
        /*EasyPermission.ask(
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

        })*/
    }

}
