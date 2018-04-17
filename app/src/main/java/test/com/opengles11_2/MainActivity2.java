package test.com.opengles11_2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity2 extends Activity {

    private static final String TAG = "opengldemo";

    private GLSurfaceView glSurfaceView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如果本设备支持OpenGl ES 2.0
        if (IsSupported()) {

            // 先建GLSurfaceView实例
            glSurfaceView = new GLSurfaceView(this);

            // 创建渲染器实例
            MyRenderer mRenderer = new MyRenderer();

            // 设置渲染器
            glSurfaceView.setRenderer(mRenderer);

            // 显示SurfaceView
            setContentView(glSurfaceView);
        }
    }

    private boolean IsSupported() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x2000;

        return supportsEs2;
    }

    public class MyRenderer implements GLSurfaceView.Renderer {
        float r = 0.3f;
        // 定义立方体的8个顶点
        float[] cubeVertices = {
                //左面
                -r, r, r,
                -r, -r, r,
                -r, r, -r,
                -r, -r, -r,

                //右面
                r, r, r,
                r, -r, r,
                r, -r, -r,
                r, r, -r,

                //前面
                -r, r, r,
                -r, -r, r,
                r, -r, r,
                r, r, r,

                //后面
                r, -r, -r,
                r, r, -r,
                -r, r, -r,
                -r, -r, -r,

                //上面
                -r, r, r,
                r, r, r,
                r, r, -r,
                -r, r, -r,

                //下面
                -r, -r, r,
                r, -r, r,
                r, -r, -r,
                -r, -r, -r
        };
        //  颜色数组
        float[] cubeColors = {
                1f, 0f, 0f, 1f,
                0f, 1f, 0f, 1f,
                0f, 0f, 1f, 1f,
                1f, 0f, 0f, 1f,

                1f, 0f, 0f, 1f,
                0f, 1f, 0f, 1f,
                0f, 0f, 1f, 1f,
                1f, 0f, 0f, 1f,

                1f, 0f, 0f, 1f,
                0f, 1f, 0f, 1f,
                0f, 0f, 1f, 1f,
                1f, 0f, 0f, 1f,

                1f, 0f, 0f, 1f,
                0f, 1f, 0f, 1f,
                0f, 0f, 1f, 1f,
                1f, 0f, 0f, 1f,


                1f, 0f, 0f, 1f,
                0f, 1f, 0f, 1f,
                0f, 0f, 1f, 1f,
                1f, 0f, 0f, 1f,

                1f, 0f, 0f, 1f,
                0f, 1f, 0f, 1f,
                0f, 0f, 1f, 1f,
                1f, 0f, 0f, 1f,
        };
        //索引数组
        private short[] indices = {
                0, 1, 2,
                0, 2, 3,

                4, 5, 6,
                4, 6, 7,

                8, 9, 10,
                8, 10, 11,

                12, 13, 14,
                12, 14, 15,

                16, 17, 18,
                16, 18, 19,

                20, 21, 22,
                20, 22, 23,
        };


        // 控制旋转的角度
        private float rotate;

        FloatBuffer VerticesBuffer;
        FloatBuffer Colorbuffer;

        ShortBuffer indexbuffer;


        public MyRenderer() {
            //获取浮点形缓冲数据
            VerticesBuffer = Utils.getFloatBuffer(cubeVertices);
            //获取浮点型颜色数据
            Colorbuffer = Utils.getFloatBuffer(cubeColors);
            //获取浮点型索引数据
            indexbuffer = Utils.getShortBuffer(indices);

        }

        // Surface创建的时候调用
        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {

            gl.glClearColor(0, 0, 0, 0);

        }

        // Surface改变的的时候调用
        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            // 设置3D视窗的大小及位置
            gl.glViewport(0, 0, width, height);

        }

        // 在Surface上绘制的时候调用
        @Override
        public void onDrawFrame(GL10 gl) { // 清除屏幕缓存和深度缓存
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
            // 启用顶点座标数据
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            // 启用顶点颜色数据
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

            // 重置当前的模型视图矩阵
            gl.glLoadIdentity();
            // 沿着Y轴旋转
            gl.glRotatef(rotate, 0f, 1f, 0f);

            // 设置顶点的位置数据
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, VerticesBuffer);

            // 设置顶点的颜色数据
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, Colorbuffer);

            //绘制三角形
            // gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP  , 0, 24);
            gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexbuffer);

            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

            // 旋转角度增加1
            rotate -= 1;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (glSurfaceView != null) {
            glSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (glSurfaceView != null) {
            glSurfaceView.onResume();
        }
    }
}
