package org.heran.edu.student.util.dispose;

import java.util.UUID;

/**
 * Created by andyzhao on 2017/5/16.
 */
public class UUIDUtil {

    public static String creatUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
