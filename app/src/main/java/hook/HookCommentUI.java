package hook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import aweme.MyLinear;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import log.Vlog;
import version.Version;

/**
 * Created by Administrator on 2018/7/21.
 */

public class HookCommentUI {
    public static void hookCommentUI(ClassLoader classLoader){
        XposedHelpers.findAndHookMethod(Version.VideoCommentDialogFragment2,
                classLoader,
                "onCreateView",
                LayoutInflater.class,
                ViewGroup.class,
                Bundle.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Object o = param.getResult();
                        Vlog.log("评论界面View>>"+o);
                        LinearLayout linearLayout = (LinearLayout) param.getResult();
                        if (linearLayout != null){
                            MyLinear myLinear = new MyLinear(HookAwemeUI.globalActivity);
                            linearLayout.addView(myLinear, 0);
                        }
                    }
                }
        );
    }
}
