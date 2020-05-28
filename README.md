# EasyPermission[ ![Download](https://api.bintray.com/packages/boybeak/nulldreams/easy-permission/images/download.svg?version=_latestVersion) ](https://bintray.com/boybeak/nulldreams/easy-permission/1.0.0/link)
## Install

```groovy
implementation 'com.github.boybeak:easy-permission:newest_version'
```

## Usage

```kotlin
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
```

