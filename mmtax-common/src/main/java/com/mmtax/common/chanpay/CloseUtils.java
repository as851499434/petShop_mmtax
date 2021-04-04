package com.mmtax.common.chanpay;

import java.io.Closeable;
import java.io.IOException;

/**
 * Description:
 *
 * @author yingjie.wang
 * @since 17/4/21 下午2:16
 */
public class CloseUtils {

    /**
     * 封装一下close方法,避免重复代码
     * @param args
     */
    public static void close(Closeable... args) {
        if (args == null || args.length == 0) {
            return;
        }
        for (Closeable arg : args) {
            try {
                if (arg != null) {
                    arg.close();
                }
            } catch (IOException e) {
                // do nothing...
            }
        }
    }
}
