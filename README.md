# EasyPermission ![Download](https://img.shields.io/badge/easy--permission-2.1.0-blue)
[中文README](https://github.com/boybeak/EasyPermission/blob/master/README-CN.md)

## Install

```groovy
// root project build.gradle
buildscript {
  allprojects {
    repositories {
        google()
        jcenter()
    }
  }
}
```

```groovy
implementation 'com.github.boybeak:easy-permission:${newest_version}'
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



**Example 3** - *Recommend*

This example base on [`AspectJ`](https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx) - An **AOP** library for Java.

> What is [AOP](https://en.wikipedia.org/wiki/Aspect-oriented_programming) ?
>
> Related with: [ASM](https://asm.ow2.io/), modify code in .class file.
>
> Different with: [APT](https://medium.com/@mauryahyd/what-is-android-apt-1fca2c4fc95a), generate new class.

Use `easy-permission-aspect` to get dynamic permission with **@RequestPermissions** and **@OnPermissionDenied**.

```groovy
// root project build.gradle
buildscript {
  dependencies {
    classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.10'
  }
}
```



proguard-rules.pro

```groovy
-keepclassmembers class * {
    @com.github.boybeak.easypermission.aspect.OnPermissionDenied <methods>;
    @com.github.boybeak.easypermission.aspect.RequestPermissions <methods>;
}
```



`com.github.boybeak:easy-permission-aspect:`[ ![Download](https://api.bintray.com/packages/boybeak/nulldreams/easy-permission-aspect/images/download.svg) ](https://bintray.com/boybeak/nulldreams/easy-permission-aspect/_latestVersion)

```groovy
// app or some other module's build.gradle
apply plugin: 'android-aspectjx'

// ... some other stuff

dependencies {
  implementation fileTree(dir: 'libs', include: ['*.jar'])
  implementation 'com.github.boybeak:easy-permission-aspect:${new_version}'
  implementation 'org.aspectj:aspectjrt:1.9.5'
}
```



- In **Context**, **Fragment** or **View**

```kotlin
@RequestPermissions(
  permissions = [
    Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION
  ],
  requestCode = REQUEST_CODE
)
fun getLocation() {
  //TODO("GET LOCATION")
}

@OnPermissionDenied
fun onGetLocationDenied(permission: String, requestCode: Int, neverAsk: Boolean) {
  //TODO("Permission denied")
}
```

- If the target method not in **Context**, **Fragment** or **View**, you must pass an **Context** parameter to the method

```kotlin
@RequestPermissions(
  permissions = [
    Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION
  ],
  requestCode = REQUEST_CODE
)
fun getLocation(context: Context) {
  //TODO("GET LOCATION")
}
```

