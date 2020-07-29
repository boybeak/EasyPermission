# EasyPermission[ ![Download](https://api.bintray.com/packages/boybeak/nulldreams/easy-permission/images/download.svg) ](https://bintray.com/boybeak/nulldreams/easy-permission/_latestVersion)
## Install

```groovy
implementation 'com.github.boybeak:easy-permission:newest_version'
```

## Usage

**Example 1**

```kotlin
EasyPermission.ask(
		Manifest.permission.WRITE_EXTERNAL_STORAGE,
  	Manifest.permission.CAMERA,
  	Manifest.permission.ACCESS_FINE_LOCATION
).go(this, REQUEST_CODE, object : Callback {
    override fun onGranted(permissions: MutableList<String>, requestCode: Int) {
      TODO("Do sth.")
    }

    override fun onDenied(permission: String, requestCode: Int, neverAsk: Boolean) {
      TODO("Permission denied.")
    }
})
```



**Example 2**

If you use `kotlin`, you can use this way.

```kotlin
//this may be Context or Fragment
this.withPermissions(
  requestCode = 1,
  permissions = *arrayOf(
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.CAMERA,
    Manifest.permission.ACCESS_FINE_LOCATION
  ),
  onGranted = { permissions ->

              },
  onDenied = { permission, requestCode, neverAsk ->

             }
)
```



**Example 3**

Use `easy-permission-aspect` to get dynamic permission with **@RequestPermissions** and **@OnPermissionDenied**.



