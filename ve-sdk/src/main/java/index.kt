@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME")
package uts.sdk.modules.veSdk;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.widget.Toast;
import com.example.veplayer.TestNormalActivity2;
import com.example.veplayer.TestVEActivity;
import com.vecore.VECore;
import com.vecore.exception.InitializeException;
import com.vesdk.camera.api.manager.CameraConfiguration;
import com.vesdk.camera.api.manager.CameraUI;
import com.vesdk.camera.api.manager.NetWorkConfiguration;
import io.dcloud.uniapp.*;
import io.dcloud.uniapp.extapi.*;
import io.dcloud.uniapp.framework.*;
import io.dcloud.uniapp.runtime.*;
import io.dcloud.uniapp.vue.*;
import io.dcloud.uniapp.vue.shared.*;
import io.dcloud.uts.*;
import io.dcloud.uts.Map;
import io.dcloud.uts.Set;
import io.dcloud.uts.UTSAndroid;
import java.io.File;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.async;
import com.vesdk.camera.api.SdkEntry as CameraEntry;
import com.example.veplayer.Entry as Entry2;
val ROOT_URL = "http://vesystem.effectlib.com/api/v1/";
val APP_DATA = ROOT_URL + "file/list";
val TYPE_URL = ROOT_URL + "category/list";
val SOUND_URL = ROOT_URL + "file/list";
fun getExternalFilesDirEx(context: Context, type: String): File {
    if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
        var ef = context.getExternalFilesDir(type);
        if (ef != null && ef.isDirectory()) {
            return ef;
        }
    }
    return File(Environment.getExternalStorageDirectory(), type);
}
fun init(): Boolean {
    var appKey = "515c3a7d68c7276e";
    var appSecret = "71f8f74e564cea5ec89e40da68b6d371tKU89mLkbPzOfuI4J4NFCe/zuuYQ9irtEXnbCozwAiQEVeOqZtHyi+/qpIOlVD4zB2THMj0QfrjhBAdALkopmGR20n/16OiZ42+6lxL1+AsqBPZScSkDIB5bCYPabtc7vORlUDIwuKudQObMKJmlG83UzNqUVvnMFG7W+iRToku7SWpSeA4pcKrDd11XyN8gUg88zenZFsGKOcyaxS/75y5kruL+DszvMGx8ME7ovEotWqtGu2tQjv2LdCTTxN5NM3Ln05DNrMx8njeENHGXz4Q3E+ASTIbiFYbTVkgf/V4PM6aISB7P3ZtxIgkvka1KwzrkO5vB/mgpFojO9sj5LbGWTNnXFz6P+zmwliCxeccjpoSSaIlR09b4dLBJtUZ8EklLGrNbj7YYGY5MtcK97nHOjHkS3QPS6v1IjDLdny/WFf0x08lVWvIVcKSwTx2+7VCWvbK3HJL/YlAVZgv7VD8xzEbo8F9+oe+ulH0ydBU609y7Ej2Bv3AEMeeuRXFbxkqPnBtH+AkUJu4DnJVkfbJ3xYReqs87KboupcaLHNDx9XbEvynMPjafEfyJRVvc9wZ3R8KjNmvEYWCqArdHk2qYqQE4UJsEF2FasaqpIlaaHa6xHQ5XV5Q+tmHaaFYh7Z5ju2FDm12tucP1EilH49gw2tNXzHmw3rDysTbMp7X4IHISjYUUX/eLP/Oo94Gpk+/RBS0G3d7L96VFv7RYUzzqKYeHaJCKtY5MhdhiVLVukVGqukOc66X9ZiWOpiQwypcARwVuWPePABl70BCVvedQNduy5/Q/z5UMmv4XUZGD5AB7Ap5XLLDczo91plS38kphUO+iJLp3iY6vhrw4EUq2z9kouywNZlh2dmELZfubzPa0GBkpS7+iZHQpkYo51kOq8tX0stfA2IwyM9DVxKzCnT0EKtvfLa4AXiYVthmBc4I8mLeI9FjSki8Hovgvl95QgRa1djOBvzNAj9QXyNrQgPLJ2F0CIFwYa6QTlv+d+qQc/B/MOfcS5FhWicVWwQOnLLDLT4sZ6NSwPYYcKA==";
    var SPACE_NAME = "ve";
    var tmp = UTSAndroid.getAppContext();
    var mStrCustomPath = getExternalFilesDirEx(tmp!!, SPACE_NAME).getAbsolutePath();
    console.error("path:" + mStrCustomPath, " at uni_modules/ve-sdk/utssdk/app-android/index.uts:76");
    try {
        VECore.initialize(tmp!!, mStrCustomPath, appKey, appSecret, "", true, false);
        CameraEntry.initialize(tmp!!, appKey, mStrCustomPath);
        Toast.makeText(tmp!!, "初始化成功", Toast.LENGTH_SHORT).show();
    }
     catch (e: InitializeException) {
        console.error("init: error:" + e, " at uni_modules/ve-sdk/utssdk/app-android/index.uts:82");
        Toast.makeText(tmp, "初始化失败", Toast.LENGTH_SHORT).show();
        return false;
    }
    return true;
}
fun initCameraConfig(type: Int) {
    var cameraConfig = CameraConfiguration.Builder().useMultiShoot(false).setCameraUIType(type).setSingleCameraSaveToAlbum(true).setAudioMute(false).setDefaultRearCamera(false).enableBeauty(true).get();
    var builder = NetWorkConfiguration.Builder();
    builder.setUrl(TYPE_URL, APP_DATA).setMusicPaging(SOUND_URL);
    CameraEntry.getSdkService().initConfiguration(builder.get(), cameraConfig);
}
fun camera(): Boolean {
    var type = CameraUI.ONLY_FREE_SWITCHING_CAN_CHANGE;
    initCameraConfig(type);
    var cameraConfig = CameraConfiguration.Builder().useMultiShoot(false).setCameraUIType(type).setSingleCameraSaveToAlbum(true).setAudioMute(false).setDefaultRearCamera(false).enableBeauty(true).get();
    var builder = NetWorkConfiguration.Builder();
    builder.setUrl(TYPE_URL, APP_DATA).setMusicPaging(SOUND_URL);
    CameraEntry.getSdkService().initConfiguration(builder.get(), cameraConfig);
    UTSAndroid.onAppActivityResult(fun(requestCode: Int, resultCode: Int, data: Intent?){
        var eventName = "camera :onAppActivityResult requestCode:" + requestCode + " -resultCode:" + resultCode + " -data:" + data;
        console.log(eventName, " at uni_modules/ve-sdk/utssdk/app-android/index.uts:135");
    }
    );
    CameraEntry.record(UTSAndroid.getUniActivity()!!, 456);
    return true;
}
@Suppress("DEPRECATION")
fun gotoDemoActivity(callback: (msg: String) -> Unit): Boolean {
    var num = Entry2();
    var tmp = num.getCount(19, 69);
    console.log("按钮被点击222--22--->" + tmp, " at uni_modules/ve-sdk/utssdk/app-android/index.uts:152");
    var host = UTSAndroid.getUniActivity();
    console.log("按钮被点击host----->" + host, " at uni_modules/ve-sdk/utssdk/app-android/index.uts:157");
    console.log("按钮被点击num2----->" + num.getCount(136, 9), " at uni_modules/ve-sdk/utssdk/app-android/index.uts:166");
    var intent = Intent(UTSAndroid.getUniActivity(), TestNormalActivity2().javaClass);
    var file = File(Environment.getExternalStorageDirectory(), "/4k/gz.mp4");
    console.error("path:" + file.length() + " a:" + file.getAbsolutePath(), " at uni_modules/ve-sdk/utssdk/app-android/index.uts:196");
    intent.putExtra("path", file.getAbsolutePath());
    UTSAndroid.onAppActivityResult(fun(requestCode: Int, resultCode: Int, data: Intent?){
        var eventName = "onAppActivityResult  -  requestCode:" + requestCode + " -resultCode:" + resultCode + " -data:" + data;
        console.log(eventName, " at uni_modules/ve-sdk/utssdk/app-android/index.uts:204");
        if (resultCode == Activity.RESULT_OK && requestCode == 123) {
            callback("回调结果:" + data?.getStringExtra("path"));
        } else {
            callback("回调结果:failed");
        }
    }
    );
    UTSAndroid.getUniActivity()?.startActivityForResult(intent, 123);
    return true;
}
@Suppress("DEPRECATION")
fun play(callback: (msg: String) -> Unit): Boolean {
    var host = UTSAndroid.getUniActivity();
    console.log("play" + host, " at uni_modules/ve-sdk/utssdk/app-android/index.uts:226");
    var intent = Intent(UTSAndroid.getUniActivity(), TestVEActivity().javaClass);
    var file = File(Environment.getExternalStorageDirectory(), "/DCIM/tmp2.mp4");
    console.error("path:" + file.length() + " a:" + file.getAbsolutePath(), " at uni_modules/ve-sdk/utssdk/app-android/index.uts:251");
    intent.putExtra("path", file.getAbsolutePath());
    UTSAndroid.onAppActivityResult(fun(requestCode: Int, resultCode: Int, data: Intent?){
        var eventName = "play:onAppActivityResult  -  requestCode:" + requestCode + " -resultCode:" + resultCode + " -data:" + data;
        console.log(eventName, " at uni_modules/ve-sdk/utssdk/app-android/index.uts:258");
        if (resultCode == Activity.RESULT_OK && requestCode == 452) {
            callback("回调结果:" + data?.getStringExtra("path"));
        } else {
            callback("回调结果:failed");
        }
    }
    );
    UTSAndroid.getUniActivity()?.startActivityForResult(intent, 452);
    return true;
}
val runBlock1 = run {
    UTSAndroid.onAppActivityBack(fun(){
        var eventName = "onAppActivityBack- " + Date.now();
        console.error(eventName, " at uni_modules/ve-sdk/utssdk/app-android/index.uts:275");
    }
    );
}
