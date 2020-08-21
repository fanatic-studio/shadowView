package vd.android.vdShadowView.component;

import android.content.Context;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.Map;

import app.vd.framework.extend.module.vdCommon;
import app.vd.framework.extend.module.vdConstants;
import app.vd.framework.extend.module.vdJson;
import app.vd.framework.extend.module.vdParse;
import app.vd.framework.extend.module.vdScreenUtils;
import vd.android.vdShadowView.ShadowLayout;

public class vdShadowViewComponent extends WXVContainer<ShadowLayout> {

    private ShadowLayout mShadowLayout;

    public vdShadowViewComponent(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
    }

    @Override
    protected ShadowLayout initComponentHostView(@NonNull Context context) {
        mShadowLayout = new ShadowLayout(context);
        //
        if (getEvents().contains(vdConstants.Event.READY)) {
            fireEvent(vdConstants.Event.READY, null);
        }
        //
        return mShadowLayout;
    }

    @Override
    protected boolean setProperty(String key, Object param) {
        return initProperty(key, param) || super.setProperty(key, param);
    }

    private boolean initProperty(String key, Object val) {
        switch (vdCommon.camelCaseName(key)) {
            case "vd":
                JSONObject json = vdJson.parseObject(vdParse.parseStr(val, ""));
                if (json.size() > 0) {
                    for (Map.Entry<String, Object> entry : json.entrySet()) {
                        initProperty(entry.getKey(), entry.getValue());
                    }
                }
                return true;

            case "blur":
                mShadowLayout.setmShadowLimit(px2dp(vdParse.parseInt(val)));
                return true;

            case "color":
                mShadowLayout.setmShadowColor(vdParse.parseColor(val));
                return true;

            case "backgroundColor":
                mShadowLayout.setmBackGroundColor(vdParse.parseColor(val));
                return true;

            case "offsetX":
                mShadowLayout.setMDx(px2dp(vdParse.parseInt(val)));
                return true;

            case "offsetY":
                mShadowLayout.setMDy(px2dp(vdParse.parseInt(val)));
                return true;

            case "placement":
                String placement = vdParse.parseStr(val);
                boolean showAll = placement.contains("all") || !(placement.contains("left") || placement.contains("right") || placement.contains("top") || placement.contains("bottom"));
                mShadowLayout.setLeftShow(showAll || placement.contains("left"));
                mShadowLayout.setRightShow(showAll || placement.contains("right"));
                mShadowLayout.setTopShow(showAll || placement.contains("top"));
                mShadowLayout.setBottomShow(showAll || placement.contains("bottom"));
                return true;

            default:
                return false;
        }
    }

    private int px2dp(int val) {
        return vdScreenUtils.weexPx2dp(getInstance(), val);
    }
}