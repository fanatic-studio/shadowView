package eeui.android.eeuiShadowView.entry;

import android.content.Context;

import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

import app.eeui.framework.extend.annotation.ModuleEntry;
import eeui.android.eeuiShadowView.component.eeuiShadowViewComponent;

@ModuleEntry
public class eeuiShadowViewEntry {

    /**
     * APP启动会运行此函数方法
     * @param content Application
     */
    public void init(Context content) {

        try {
            WXSDKEngine.registerComponent("shadow-view", eeuiShadowViewComponent.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }
}
