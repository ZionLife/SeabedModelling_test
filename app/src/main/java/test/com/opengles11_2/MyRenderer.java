package test.com.opengles11_2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static test.com.opengles11_2.Constant.yArray;
import static test.com.opengles11_2.Constant.loadLandforms;

/**
 * Created by QiuXi'an on 2018/4/10 0010.
 * Email Zionlife1025@163.com
 */

public class MyRenderer implements GLSurfaceView.Renderer {
    Mountain mMountain;
    //山的纹理id
    int mountainId;
    int rockId;

    Obstacle mObstacle;
    GLSurfaceView mSurfaceView;

    public MyRenderer(GLSurfaceView surfaceView) {
        mSurfaceView = surfaceView;
        mObstacle = new Obstacle(0.3f);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0, 0, 0, 0);
        MatrixState.setInitStack();
        yArray = loadLandforms(MyApplication.mContext.getResources(), R.mipmap.land);
        mMountain = new Mountain(mSurfaceView, yArray, yArray.length - 1, yArray[0].length - 1);
        mountainId = initTexture(R.mipmap.grass_01);
        rockId = initTexture(R.mipmap.rock_01);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // 设置3D视窗的大小及位置
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        mObstacle.draw(gl);
        mMountain.drawSelf(mountainId, rockId);
    }

    //生成纹理Id的方法
    public int initTexture(int drawableId) {
        //生成纹理ID
        int[] textures = new int[1];
        GLES30.glGenTextures
                (
                        1,          //产生的纹理id的数量
                        textures,   //纹理id的数组
                        0           //偏移量
                );
        int textureId = textures[0];
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureId);    //绑定纹理
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER,
                GLES30.GL_LINEAR_MIPMAP_LINEAR);        //使用MipMap线性纹理采样
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER,
                GLES30.GL_LINEAR_MIPMAP_NEAREST);        //使用MipMap最近点纹理采样
        //ST方向纹理拉伸方式
        GLES30.glTexParameterf(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_S, GLES30.GL_REPEAT);
        GLES30.glTexParameterf(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_T, GLES30.GL_REPEAT);

        //通过输入流加载图片
        InputStream is = MyApplication.mContext.getResources().openRawResource(drawableId);
        Bitmap bitmapTmp;
        try {
            bitmapTmp = BitmapFactory.decodeStream(is);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //实际加载纹理
        GLUtils.texImage2D
                (
                        GLES30.GL_TEXTURE_2D,   //纹理类型
                        0,                      //纹理的层次，0表示基本图像层，可以理解为直接贴图
                        bitmapTmp,              //纹理图像
                        0                      //纹理边框尺寸
                );
        //自动生成Mipmap纹理
        GLES30.glGenerateMipmap(GLES30.GL_TEXTURE_2D);
        //释放纹理图
        bitmapTmp.recycle();
        //返回纹理ID
        return textureId;
    }
}
