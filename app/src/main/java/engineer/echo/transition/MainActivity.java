package engineer.echo.transition;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;

import engineer.echo.transition.cmpts.context.BaseActivity;
import engineer.echo.transition.cmpts.utils.CommonUtil;
import engineer.echo.transition.cmpts.widget.transition.SafeSlide;
import engineer.echo.transition.fragment.AppCtrlFragment;
import engineer.echo.transition.fragment.AppDisplayFragment;

/**
 * MainActivity
 * Created by Plucky<plucky@echo.engineer> on 2018/1/1 下午3:26.
 * more about me: http://www.1991th.com
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //相机预览
        AppDisplayFragment displayFragment = AppDisplayFragment.newInstance();
        transaction.add(R.id.app_display_view, displayFragment);
        //控制层
        AppCtrlFragment ctrlFragment = AppCtrlFragment.newInstance();

        //设置Fragment离开时执行的动画   --变成不可见
        SafeSlide slideExit = new SafeSlide();
        // TODO: Added By Plucky 2018/1/8 11:39 设定Edge需要API版本>=LOLLIPOP
        if (CommonUtil.isOverLollipop()) {
            slideExit.setSlideEdge(Gravity.LEFT);
        }
        slideExit.addTarget(R.id.progressBar);
        slideExit.setDuration(Constants.TRANSITION_TIME);
        ctrlFragment.setExitTransition(slideExit);
        //设置Fragment重新回到栈顶时执行的动画 ---重新可见
        SafeSlide slideReEnter = new SafeSlide();
        // TODO: Added By Plucky 2018/1/8 11:39 设定Edge需要API版本>=LOLLIPOP
        if (CommonUtil.isOverLollipop()) {
            slideReEnter.setSlideEdge(Gravity.RIGHT);
        }
        slideReEnter.addTarget(R.id.progressBar);
        slideReEnter.setDuration(Constants.TRANSITION_TIME);
        ctrlFragment.setReenterTransition(slideReEnter);

        transaction.add(R.id.app_ctrl_view, ctrlFragment);
        transaction.commit();
    }
}
