package test.com.opengles11_2;

import android.app.Activity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
public class MainActivity extends Activity {

    MySurfaceView mView;
    //屏幕对应的宽度和高度
    static float WIDTH;
    static float HEIGHT;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //设置为全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //获得系统的宽度以及高度
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        if(dm.widthPixels>dm.heightPixels)
        {
            WIDTH=dm.widthPixels;
            HEIGHT=dm.heightPixels;
        }
        else
        {
            WIDTH=dm.heightPixels;
            HEIGHT=dm.widthPixels;
        }
        //设置为横屏模式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mView = new MySurfaceView(this);
        mView.requestFocus();//获取焦点
        mView.setFocusableInTouchMode(true);//设置为可触控
        setContentView(mView);

    }
    @Override
    protected void onResume() {
        super.onResume();
        mView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mView.onPause();
    }
}
