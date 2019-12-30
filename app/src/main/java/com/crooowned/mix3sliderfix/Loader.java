package com.crooowned.mix3sliderfix;

import android.os.Message;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Loader implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if(lpparam.packageName.contains("com.android.systemui"))
        {
            try {
                XposedHelpers.findAndHookMethod("com.android.systemui.sliderview.slideranimationView.SliderCameraController$H", lpparam.classLoader, "handleMessage", Message.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                        param.setResult(null);
                        return;
                    }
                });
            } catch (XposedHelpers.ClassNotFoundError e){
                XposedBridge.log("Couldnt hook sliderfix, not on Mix 3 MIUI?");
            }
        }
    }
}
