package vd.android.vdShadowView.entry;

import android.content.Context;

import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

import app.vd.framework.extend.annotation.ModuleEntry;
import vd.android.vdShadowView.component.vdShadowViewComponent;

@ModuleEntry
public class vdShadowViewEntry {

    /**
     * APP启动会运行此函数方法
     * @param content Application
     */
    public void init(Context content) {

        try {
            WXSDKEngine.registerComponent("shadow-view", vdShadowViewComponent.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }
}
