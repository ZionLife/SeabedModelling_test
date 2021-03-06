package test.com.opengles11_2;

import android.opengl.GLES20;
import android.opengl.GLES30;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES10.glClear;
import static android.opengl.GLES10.glColorPointer;
import static android.opengl.GLES10.glDisableClientState;
import static android.opengl.GLES10.glDrawElements;
import static android.opengl.GLES10.glEnableClientState;
import static android.opengl.GLES10.glLoadIdentity;
import static android.opengl.GLES10.glRotatef;
import static android.opengl.GLES10.glVertexPointer;

/**
 * Created by QiuXi'an on 2018/3/8 0008.
 * Email Zionlife1025@163.com
 */

public class Obstacle {
//    private FloatBuffer vertexBuffer, colorBuffer;
//    private ShortBuffer indexBuffer;
//    private int mProgram;
//    private int mPositionHandle;
//    private int mColorHandle;
//    private float[] mViewMatrix = new float[16];
//    private float[] mProjectMatrix = new float[16];
//    private float[] mMVPMatrix = new float[16];
//    private int mMatrixHandler;
//    private final int COORDS_PER_VERTEX = 3;
//
//    float r = 0.3f;//边长的一半
//    final float[] cubePosition = {
//            //正面四个顶点
//            -r, r, r, //左上0
//            -r, -r, r, //左下1
//            r, -r, r, //右下2
//            r, r, r, //右上3
//
//            //反面四个顶点
//            -r, r, -r, //左上4
//            -r, -r, -r, //左下5
//            r, -r, -r, //右下6
//            r, r, -r, //右上7
//    };
//
//
//    final short index[] = {
//            0, 3, 2, 0, 2, 1, //正面
//            0, 1, 5, 0, 5, 4, //左面
//            0, 7, 3, 0, 4, 7,//上面
//            6, 7, 4, 6, 4, 5, //后面
//            6, 3, 7, 6, 2, 3, //右面
//            6, 5, 1, 6, 1, 2, //下面
//    };
//
//    //八个顶点颜色，与顶点坐标一一对应
//    float color[] = {
//            0f, 1f, 0f, 1f,
//            0f, 1f, 0f, 1f,
//            0f, 1f, 0f, 1f,
//            0f, 1f, 0f, 1f,
//            1f, 0f, 0f, 1f,
//            1f, 0f, 0f, 1f,
//            1f, 0f, 0f, 1f,
//            1f, 0f, 0f, 1f,
//    };
//    //顶点个数
//    private final int vertexCount = cubePosition.length / COORDS_PER_VERTEX;
//    //顶点之间的偏移量
//    private final int vertexStride = COORDS_PER_VERTEX * 4; //每个顶点4个字节
//
//    public Obstacle(float r) {
//        this.r = r;
//        initVertices();
//        initShader();
//    }
//
//    private void initShader() {
//        String vertexShader = ShaderUtil.readTextFileFromResource(MyApplication.mContext, R.raw.obstacle_vertex);
//        String fragmentShader = ShaderUtil.readTextFileFromResource(MyApplication.mContext, R.raw.obstacle_frag);
//        mProgram = ShaderUtil.createProgram(vertexShader, fragmentShader);
//        //获取变换矩阵vMatrix的句柄并设置值
//        mMatrixHandler = GLES30.glGetUniformLocation(mProgram, "vMatrix");
//        //获取顶点着色器vPosition句柄并设置值
//        mPositionHandle = GLES30.glGetAttribLocation(mProgram, "vPosition");
//
//        //获取片元着色器的vColor成员的句柄
//        mColorHandle = GLES30.glGetAttribLocation(mColorHandle, "aColor");
//    }
//
//    private void initVertices() {
//        ByteBuffer bb = ByteBuffer.allocateDirect(cubePosition.length * 4);
//        bb.order(ByteOrder.nativeOrder());
//        vertexBuffer = bb.asFloatBuffer();
//        vertexBuffer.put(cubePosition);
//        vertexBuffer.position(0);
//
//        ByteBuffer dd = ByteBuffer.allocateDirect(color.length * 4);
//        dd.order(ByteOrder.nativeOrder());
//        colorBuffer = dd.asFloatBuffer();
//        colorBuffer.put(color);
//        colorBuffer.position(0);
//
//        ByteBuffer cc = ByteBuffer.allocateDirect(index.length * 2);
//        cc.order(ByteOrder.nativeOrder());
//        indexBuffer = cc.asShortBuffer();
//        indexBuffer.put(index);
//        indexBuffer.position(0);
//    }
//
//    public void drawSelf() {
//        Log.i("drawSelf", "111");
//        GLES30.glUseProgram(mProgram);
//        GLES30.glUniformMatrix4fv(mMatrixHandler, 1, false, MatrixState.getFinalMatrix(), 0);
//        //启用0三角形顶点的句柄
//        GLES30.glEnableVertexAttribArray(mPositionHandle);
//        //准备三角形的坐标数据
//        GLES30.glVertexAttribPointer(mPositionHandle, 3, GLES30.GL_FLOAT, false, 0, vertexBuffer);
//        //设置绘制三角形的颜色
//        GLES30.glEnableVertexAttribArray(mColorHandle);
//        GLES30.glVertexAttribPointer(mColorHandle, 4, GLES30.GL_FLOAT, false, 0, colorBuffer);
//        //索引法绘制正方体
//        GLES30.glDrawElements(GLES20.GL_TRIANGLES, index.length, GLES30.GL_UNSIGNED_SHORT, indexBuffer);
//        //禁止顶点数组的句柄
//        GLES30.glDisableVertexAttribArray(mPositionHandle);
//        Log.i("drawSelf", "222");
//    }

    float r = 0.05f;
    // 定义立方体的8个顶点
    float[] cubeVertices = {
            //左面
            -r,r,r,
            -r,-r,r,
            -r,r,-r,
            -r,-r,-r,

            //右面
            r, r,r,
            r,-r,r,
            r,-r,-r,
            r,r,-r ,

            //前面
            -r,r,r,
            -r,-r,r,
            r,-r,r,
            r, r,r,

            //后面
            r,-r,-r,
            r,r,-r,
            -r,r,-r,
            -r,-r,-r,

            //上面
            -r,r,r,
            r, r,r,
            r,r,-r,
            -r,r,-r,

            //下面
            -r,-r,r,
            r,-r,r,
            r,-r,-r,
            -r,-r,-r
    };
    //  颜色数组
    float []  cubeColors = {
            1f,0f,0f,1f ,
            0f,1f,0f,1f,
            0f,0f,1f,1f,
            1f,0f,0f,1f,

            1f,0f,0f,1f ,
            0f,1f,0f,1f,
            0f,0f,1f,1f,
            1f,0f,0f,1f,

            1f,0f,0f,1f ,
            0f,1f,0f,1f,
            0f,0f,1f,1f,
            1f,0f,0f,1f,

            1f,0f,0f,1f ,
            0f,1f,0f,1f,
            0f,0f,1f,1f,
            1f,0f,0f,1f,


            1f,0f,0f,1f ,
            0f,1f,0f,1f,
            0f,0f,1f,1f,
            1f,0f,0f,1f,

            1f,0f,0f,1f ,
            0f,1f,0f,1f,
            0f,0f,1f,1f,
            1f,0f,0f,1f,
    };
    //索引数组
    private short[] indices={
            0,1,2,
            0,2,3,

            4,5,6,
            4,6,7,

            8,9,10,
            8,10,11,

            12,13,14,
            12,14,15,

            16,17,18,
            16,18,19,

            20,21,22,
            20,22,23,
    };


    // 控制旋转的角度
    private float rotate;

    FloatBuffer VerticesBuffer;
    FloatBuffer Colorbuffer;

    ShortBuffer indexbuffer;

    public Obstacle(float r) {
        this.r = r;
        init();
    }

    private void init() {
        //获取浮点形缓冲数据
        VerticesBuffer = Utils.getFloatBuffer(cubeVertices);
        //获取浮点型颜色数据
        Colorbuffer = Utils.getFloatBuffer(cubeColors);
        //获取浮点型索引数据
        indexbuffer = Utils.getShortBuffer(indices);
    }

    public void draw(GL10 gl) {
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
