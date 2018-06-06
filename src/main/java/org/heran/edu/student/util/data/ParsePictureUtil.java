package org.heran.edu.student.util.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

/**
 * Created by git on 2018/6/6.
 */
public class ParsePictureUtil {

    public static String getImgBase64(String imgFile) throws IOException {

        // 对图像进行base64编码
        String imgBase64 = "";
        FileInputStream finputstream =null;
        try {
            File file = new File(imgFile);
            byte[] content = new byte[(int) file.length()];
            finputstream = new FileInputStream(file);
            finputstream.read(content);
            imgBase64 = new String(encodeBase64(content));
        }finally {
            finputstream.close();
        }
        return imgBase64;
    }
}
