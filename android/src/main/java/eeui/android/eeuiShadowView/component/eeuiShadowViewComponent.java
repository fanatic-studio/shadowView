package eeui.android.eeuiShadowView.component;

import android.content.Context;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.Map;

import app.eeui.framework.extend.module.eeuiCommon;
import app.eeui.framework.extend.module.eeuiConstants;
import app.eeui.framework.extend.module.eeuiJson;
import app.eeui.framework.extend.module.eeuiParse;
import app.eeui.framework.extend.module.eeuiScreenUtils;
import eeui.android.eeuiShadowView.ShadowLayout;

public class eeuiShadowViewComponent extends WXVContainer<ShadowLayout> {

    private ShadowLayout mShadowLayout;

    public eeuiShadowViewComponent(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
    }

    @Override
    protected ShadowLayout initComponentHostView(@NonNull Context context) {
        mShadowLayout = new ShadowLayout(context);
        //
        if (getEvents().contains(eeuiConstants.Event.READY)) {
            fireEvent(eeuiConstants.Event.READY, null);
        }
        //
        return mShadowLayout;
    }

    @Override
    protected boolean setProperty(String key, Object param) {
        return initProperty(key, param) || super.setProperty(key, param);
    }

    private boolean initProperty(String key, Object val) {
        switch (eeuiCommon.camelCaseName(key)) {
            case "eeui":
                JSONObject json = eeuiJson.parseObject(eeuiParse.parseStr(val, ""));
                if (json.size() > 0) {
                    for (Map.Entry<String, Object> entry : json.entrySet()) {
                        initProperty(entry.getKey(), entry.getValue());
                    }
                }
                return true;

            case "blur":
                mShadowLayout.setmShadowLimit(px2dp(eeuiParse.parseInt(val)));
                return true;

            case "color":
                mShadowLayout.setmShadowColor(eeuiParse.parseColor(val));
                return true;

            case "backgroundColor":
                mShadowLayout.setmBackGroundColor(eeuiParse.parseColor(val));
                return true;

            case "offsetX":
                mShadowLayout.setMDx(px2dp(eeuiParse.parseInt(val)));
                return true;

            case "offsetY":
                mShadowLayout.setMDy(px2dp(eeuiParse.parseInt(val)));
                return true;

            case "placement":
                String placement = eeuiParse.parseStr(val);
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
        return eeuiScreenUtils.weexPx2dp(getInstance(), val);
    }
}