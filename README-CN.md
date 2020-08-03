# EasyPermission[ ![Download](https://api.bintray.com/packages/boybeak/nulldreams/easy-permission/images/download.svg) ](https://bintray.com/boybeak/nulldreams/easy-permission/_latestVersion)
## 安装

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

## 使用

**实例1**

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



**实例2**

如果你使用 `kotlin`来开发程序, 你可以使用这种方式.

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



**实例3** - *推荐使用*

这个实例基于 `AspectJ` - 一个 java的**AOP** 库.

> 什么是 [AOP](https://en.wikipedia.org/wiki/Aspect-oriented_programming) ?
>
> 相关: [ASM](https://asm.ow2.io/), 直接在字节码中修改逻辑.
>
> 区别于: [APT](https://medium.com/@mauryahyd/what-is-android-apt-1fca2c4fc95a), 生成新的类.

借助 `easy-permission-aspect` 库，通过该库中提供的 **@RequestPermissions** 和 **@OnPermissionDenied**来获取动态权限.

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



- 在 **Context**, **Fragment** 或者 **View** 类中

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

- 如果你使用动态权限的方法没有在 **Context**, **Fragment** 或者 **View**类中, 你必须在方法的参数中，传入一个 **Context** 对象作为参数.

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

