package test.com.opengles11_2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by QiuXi'an on 2018/3/9 0009.
 * Email Zionlife1025@163.com
 */

public class BufferUtils {
    public static ByteBuffer arr2ByteBuffer(byte[] arr) {
        ByteBuffer ibb = ByteBuffer.allocateDirect(arr.length);
        ibb.order(ByteOrder.nativeOrder());
        ibb.put(arr);
        ibb.position(0);
        return ibb;
    }
}
