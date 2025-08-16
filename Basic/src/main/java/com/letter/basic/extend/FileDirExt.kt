package com.letter.basic.extend

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.storage.StorageManager
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.reflect.InvocationTargetException


/**
 * （1）内部存储
 * （2）获取内部存储中当前应用程序下的files目录的路径
 * （其路径为/data/data/com.xxx.ooo.filetestdemo/files有些手机的路径为/data/user/0/com.xxx.ooo.filetestdemo/files）
 * （3）个别设备虽然获取内部存储的路径貌似不同，其实最终都是映射在同一个路径下。比如/data/data/对应的映射路径是data/user/0/
 * （4）不需要额外的权限来读取或在返回的路径下写入文件
 * （5）当应用被卸载时，文件数据被清除
 * （6）一般情况下，非root手机不能访问
 * @return
 */
fun Context.getFilesDir(): String {
    val dir: File = filesDir
    return dir.absolutePath
}

/**
 * （1）内部存储
 * （2）获取内部存储中当前应用程序下的cache目录的路径
 * （其路径为/data/data/com.xxx.ooo.filetestdemo/cache有些手机的路径为/data/user/0/com.xxx.ooo.filetestdemo/cache）
 * （3）个别设备虽然获取内部存储的路径貌似不同，其实最终都是映射在同一个路径下。比如/data/data/对应的映射路径是data/user/0/
 * （4）不需要额外的权限来读取或在返回的路径下写入文件
 * （5）当该文件夹超过当前被分配的最大缓存时，系统将自动删除该目录中的文件为其他地方提供需要空间，当未超出时则不会
 * （6）当应用被卸载时，文件数据被清除
 * （7）一般情况下，非root手机不能访问
 * @return
 */
fun Context.getCacheDir(): String {
    val dir: File = cacheDir
    return dir.absolutePath
}

/**
 * （1）外部存储
 * （2）获取外部存储中当前应用程序下的cache目录的路径（/storage/emulated/0/Android/data/com.xxx.ooo.filetestdemo/cache）
 * （3）不需要额外的权限来读取或在返回的路径下写入文件
 * （4）当应用被卸载时，文件数据被清除
 * （5）一般情况下，非root手机可以访问
 * @return
 */
fun Context.getExternalCacheDir(): String? {
    val dir = externalCacheDir
    return dir?.absolutePath
}

/**
 * （1）和getExternalCacheDir类似，getExternalCacheDirs获取所有内置存储器的cache目录的路径
 * （2）Android4.4新增接口
 * @return
 */
fun Context.getExternalCacheDirs(): List<File?> {
    val list: MutableList<File?> = ArrayList()
    list.addAll(this.externalCacheDirs)
    return list
}

/**
 * （1）外部存储
 * （2）获取外部存储中当前应用程序下的files目录中的type文件夹的路径（/storage/emulated/0/Android/data/com.xxx.ooo.filetestdemo/files/aa）
 * （3）不需要额外的权限来读取或在返回的路径下写入文件
 * （4）当应用被卸载时，文件数据被清除
 * （5）一般情况下，非root手机可以访问
 * @return
 */
fun Context.getExternalFilesDir(): String? {
    //参数type就是files目录下的aa文件夹
    val dir = this.getExternalFilesDir("aa")
    return dir?.absolutePath
}


/**
 * （1）外部存储
 * （2）获取外部存储中当前应用程序文件夹的路径（/storage/emulated/0/Android/obb/com.xxx.ooo.filetestdemo）
 * （3）不需要额外的权限来读取或在返回的路径下写入文件
 * （4）当应用被卸载时，文件数据被清除
 * （5）一般情况下，非root手机可以访问
 * @return
 */
fun Context.getObbDir(): String {
    val dir: File = this.obbDir
    return dir.absolutePath
}

/**
 * （1）和getObbDir类似，getObbDirs获取所有内置存储器的相应目录
 * （2）Android4.4新增接口
 * @return
 */
fun Context.getObbDirs(): List<File> {
    val list: MutableList<File> = ArrayList()
    list.addAll(this.obbDirs)

    return list
}

/**
 * （1）内部存储
 * （2）不会自动备份到远程存储的应用程序文件的路径
 * （3）获取内部存储中当前应用程序下的no_backup目录的路径
 * （其路径为/data/data/com.xxx.ooo.filetestdemo/no_backup有些手机的路径为/data/user/0/com.xxx.ooo.filetestdemo/no_backup）
 * （4）个别设备虽然获取内部存储的路径貌似不同，其实最终都是映射在同一个路径下。比如/data/data/对应的映射路径是data/user/0/
 * （5）不需要额外的权限来读取或在返回的路径下写入文件
 * （6）当应用被卸载时，文件数据被清除
 * （7）一般情况下，非root手机不能访问
 * （8）Android 5.0新增接口，低于5.0手机不支持
 * @return
 */
fun Context.getNoBackupFilesDir(): String {
    var dir: File? = null
    dir = this.noBackupFilesDir
    return if (dir == null) "" else dir.absolutePath
}

/**
 * （1）内部存储
 * （2）保存应用程序代码缓存文件的目录路径,适合在运行时存放应用产生的编译或者优化的代码。
 * （3）获取内部存储中当前应用程序下的code_cache目录的路径
 * （其路径为/data/data/com.xxx.ooo.filetestdemo/code_cache有些手机的路径为/data/user/0/com.xxx.ooo.filetestdemo/code_cache）
 * （4）个别设备虽然获取内部存储的路径貌似不同，其实最终都是映射在同一个路径下。比如/data/data/对应的映射路径是data/user/0/
 * （5）不需要额外的权限来读取或在返回的路径下写入文件
 * （6）当应用被卸载时，文件数据被清除
 * （7）一般情况下，非root手机不能访问
 * （8）Android 5.0新增接口，低于5.0手机不支持
 * @return
 */
fun Context.getCodeCacheDir(): String {
    var dir: File? = null
    dir = this.codeCacheDir
    return if (dir == null) "" else dir.absolutePath
}

/**
 * （1）内部存储
 * （2）获取内部存储中当前应用程序路径
 * （其路径为/data/data/com.xxx.ooo.filetestdemo有些手机的路径为/data/user/0/com.xxx.ooo.filetestdemo）
 * （3）个别设备虽然获取内部存储的路径貌似不同，其实最终都是映射在同一个路径下。比如/data/data/对应的映射路径是data/user/0/
 * （4）不需要额外的权限来读取或在返回的路径下写入文件
 * （5）当应用被卸载时，文件数据被清除
 * （6）一般情况下，非root手机不能访问
 * （7）Android 7.0新增接口，低于7.0手机不支持
 * @return
 */
fun Context.getDataDir(): String {
    var dir: File? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        dir = this.dataDir
    }
    return if (dir == null) "" else dir.absolutePath
}

/**
 * （1）内部存储
 * （2）此上下文的主Android包的完整路径。这对应用程序通常没有用处，因为它们不应该直接访问文件系统
 * （其路径为/data/app/com.xxx.ooo.filetestdemo-1PN4Y-p3v7XA-OqXnbud8A==/base.apk)
 * （3）不需要额外的权限来读取或在返回的路径下写入文件
 * （4）当应用被卸载时，文件数据被清除
 * （5）一般情况下，非root手机不能访问
 * @return
 */
fun Context.getPackageCodePath(): String {
    val dir: String = this.packageCodePath
    return dir
}

/**
 * 和getPackageCodePath一致
 * @return
 */
fun Context.getPackageResourcePath(): String {
    val dir: String = this.packageResourcePath
    return dir
}

/**
 * （1）外部存储
 * （2）获取外部存储中指定文件夹的目录
 * （其路径为/storage/emulated/0/type）
 * （3）需要配置文件外部存储权限
 * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"></uses-permission>
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
 * 仅仅添加这读写权限还是不够的，还需要在应用信息中检查存储权限是否打开，如果没有打开，需要手动打开。
 * （4）当应用被卸载时，文件数据不会被清除
 * @return
 */
fun Context.getExternalStoragePublicDirectory(): String {
    val dir = Environment.getExternalStoragePublicDirectory("aaa")
    return dir.absolutePath
}

/**
 * （1）外部存储
 * （2）获取外部存储中指定文件夹的目录
 * （其路径为/storage/emulated/0）
 * （3）需要配置文件外部存储权限
 * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"></uses-permission>
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
 * 仅仅添加这读写权限还是不够的，还需要在应用信息中检查存储权限是否打开，如果没有打开，需要手动打开。
 * （4）当应用被卸载时，文件数据不会被清除
 * @return
 */
fun Context.getExternalStorageDirectory(): String {
    val dir = Environment.getExternalStorageDirectory()
    return dir.absolutePath
}

/**
 * 获取存储状态（媒体是指外部存储，比如SD卡）
 *
 * MEDIA_UNKNOWN：未知存储状态，例如路径没有由已知存储支持时
 * MEDIA_REMOVED：存储媒体被移除
 * MEDIA_UNMOUNTED：存储媒体没有挂载
 * MEDIA_CHECKING：如果媒体存在并正在检查磁盘
 * MEDIA_NOFS：不支持的文件系统
 * MEDIA_MOUNTED：媒体已经挂载，并且可读/写
 * MEDIA_MOUNTED_READ_ONLY：媒体已经挂载，只读
 * MEDIA_SHARED：在通过USB共享
 * MEDIA_BAD_REMOVAL：在没有挂载前存储媒体已经被移除
 * MEDIA_UNMOUNTABLE：存储媒体无法挂载
 * MEDIA_EJECTING：存储媒体处于被弹出的过程
 *
 * 其中MEDIA_MOUNTED最常用，可以判断媒体是否存在，如果不存在可以将数据存储到内部存储中
 * boolean isSDCardExist = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
 * @return
 */
fun Context.getExternalStorageState(): String {
    val state = Environment.getExternalStorageState()
    return state
}

/**
 * （1）内部存储
 * （2）其路径为/data
 * （3）如果写文件需要data文件夹读写权限，不过即使将文件夹的文件设置成可读可写权限，Android的createNewFile方法还是报权限错误（这个问题待定吧）
 * （4）不过一般app开发不需要读写/data目录中的文件
 * （5）一般情况下，非root手机不能访问
 * @return
 */
fun Context.getDataDirectory(): String {
    val file = Environment.getDataDirectory()
    return file.absolutePath
}

/**
 * （1）内部存储
 * （2）其路径为/data/cache
 * （3）如果写文件需要data文件夹读写权限，不过即使将文件夹的文件设置成可读可写权限，Android的createNewFile方法还是报权限错误（这个问题待定吧）
 * （4）不过一般app开发不需要读写/data目录中的文件
 * （5）一般情况下，非root手机不能访问
 * @return
 */
fun Context.getDownloadCacheDirectory(): String {
    val file = Environment.getDownloadCacheDirectory()
    return file.absolutePath
}


/**
 * （1）获取系统目录
 * （2）其路径为/system
 * （3）该文件夹只读权限，不可写
 * （4）一般情况下，非root手机不能访问
 * @return
 */
fun Context.getRootDirectory(): String {
    val file = Environment.getRootDirectory()
    return file.absolutePath
}

/**
 * 获取所有的外部存储路径
 * /storage/emulated/0、/storage/usbotg
 * @return
 */
fun Context.getStoragePaths(): Array<String?>? {
    var paths = arrayOfNulls<String>(0)
    val sm =
        this.getSystemService(Context.STORAGE_SERVICE) as StorageManager
    try {
        paths = sm.javaClass.getMethod("getVolumePaths", null).invoke(sm, null) as Array<String?>
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    } catch (e: InvocationTargetException) {
        e.printStackTrace()
    } catch (e: NoSuchMethodException) {
        e.printStackTrace()
    }
    return paths
}

/**
 * @param context 上下文
 * @param outFilePath 输出文件路径,因为android10的关系,所以该路径只可为应用内沙盒路径
 */
fun Uri.copyAndConvert(context: Context, outFilePath: String): File {
    val pfd = context.contentResolver.openFileDescriptor(this, "r")//r代表读操作
    FileInputStream(pfd?.fileDescriptor).use { fis ->
        FileOutputStream(File(outFilePath)).use { fos ->
            fis.copyTo(fos)
        }
    }
    return File(outFilePath)
}