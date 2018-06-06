package org.heran.edu.student.web;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.heran.edu.student.service.StuInfoService;
import org.heran.edu.student.util.data.ParsePictureUtil;
import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.util.data.ResultCode;
import org.heran.edu.student.util.fdfs.FastDFSClientWrapper;
import org.heran.edu.student.vo.StuInfoInVO;
import org.heran.edu.student.vo.StudentRegisterInVO;
import org.heran.edu.student.vo.StudentUpdateInVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/img")
public class ProcessImgController {


    @ResponseBody
    @RequestMapping(value = "/getImgBase64", method = RequestMethod.POST)
    public String imageStr(String imgFile) throws IOException {
        String imgBase64 = ParsePictureUtil.getImgBase64(imgFile);
        return JSONUtils.toJSONString(imgBase64);
    }

}
