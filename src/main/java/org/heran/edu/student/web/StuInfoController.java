package org.heran.edu.student.web;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.heran.edu.student.service.StuInfoService;
import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.util.data.ResultCode;
import org.heran.edu.student.util.dispose.SpringContextUtil;
import org.heran.edu.student.util.fdfs.FastDFSClientWrapper;
import org.heran.edu.student.vo.StuInfoInVO;
import org.heran.edu.student.vo.StudentRegisterInVO;
import org.heran.edu.student.vo.StudentUpdateInVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/stu")
public class StuInfoController {

    @Autowired
    private StuInfoService stuInfoService;

    @Autowired
    private FastDFSClientWrapper dfsClient;

    // 上传文件
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileUrl= dfsClient.uploadFile(file);
        return JSON.toJSONString(fileUrl);
    }

    @ApiOperation(value = "学生注册功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentRegisterInVO", value = "学生保存对象", required = false, paramType = "body", dataType = "StudentRegisterInVO")
    })
    @PutMapping(value = "stuRegister", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String stuRegister(@RequestBody StudentRegisterInVO studentRegisterInVO){
        log.info("Enter stuRegister studentRegisterInVO={}", studentRegisterInVO);
        Result<Boolean> resBean = new Result();
        //获取登录用户的相关信息
        try {
            resBean = stuInfoService.addStuInfo(studentRegisterInVO);
        }catch (Exception e){
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("保存失败");
            log.error("stuRegister failed", e);
        }
        log.info("stuRegister={}",resBean);
        return JSON.toJSONString(resBean);
    }

    @ApiOperation(value = "学生花名册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuInfoInVO", value = "学生查询对象", required = false, paramType = "body", dataType = "StuInfoInVO")
    })
    @PostMapping(value = "studentList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String studentList(@RequestBody StuInfoInVO stuInfoInVO, HttpServletRequest request){
        log.info("Enter studentList stuInfoInVO={}", stuInfoInVO);
        Result<Map<String,Object>> resBean = new Result<Map<String,Object>>(ResultCode.SUCCESS,"查询成功",null);
        //获取登录用户的相关信息
        try {
            resBean = stuInfoService.getStudentList(stuInfoInVO);
        }catch (Exception e){
            log.error("studentList failed",e);
        }
        log.info("studentList={}",resBean);
        return JSON.toJSONString(resBean);
    }


    @ApiOperation(value = "学生信息修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentUpdateInVO", value = "学生修改对象", required = false, paramType = "body", dataType = "StudentUpdateInVO")
    })
    @PutMapping(value = "updateStu", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateStu(@RequestBody StudentUpdateInVO studentUpdateInVO){
        log.info("Enter updateStu studentUpdateInVO={}", studentUpdateInVO);
        Result<Boolean> resBean = new Result();
        //获取登录用户的相关信息
        try {
            resBean = stuInfoService.updateStu(studentUpdateInVO);
        }catch (Exception e){
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("修改失败");
            log.error("updateStu failed", e);
        }
        log.info("updateStu={}",resBean);
        return JSON.toJSONString(resBean);
    }


    @ApiOperation(value = "审核状态批量修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", required = true, dataType = "string", paramType = "query")
    })
    @PutMapping(value = "updateBatchStatus", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateBatchStatus(@RequestParam(value = "ids") String ids){
        log.info("Enter updateBatchStatus ids={}", ids);
        Result<Boolean> resBean = new Result();
        //获取登录用户的相关信息
        try {
            resBean = stuInfoService.updateBatchStatus(ids);
        }catch (Exception e){
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("修改失败");
            log.error("updateBatchStatus failed", e);
        }
        log.info("updateBatchStatus={}",resBean);
        return JSON.toJSONString(resBean);
    }

    @PostMapping(value = "upLoadAccessory", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String upLoadAccessory(@RequestParam("file")MultipartFile file,HttpServletRequest request){
        Result<Map<String, Object>> res = new Result<Map<String, Object>>(ResultCode.ERROR_DATA, "上传失败", null);

        Map<String,Object> map = new HashMap<>();
        //保存时的文件名
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime())+file.getOriginalFilename();

        System.out.println(dateName);
        //保存文件的绝对路径
        WebApplicationContext webApplicationContext = (WebApplicationContext) SpringContextUtil.applicationContext;
        ServletContext servletContext = webApplicationContext.getServletContext();
        String realPath = servletContext.getRealPath("/");
        String filePath = realPath + "resources"+ File.separator +"static" + File.separator+dateName;
        System.out.println("绝对路径:"+filePath);

        File newFile = new File(filePath);

        //MultipartFile的方法直接写文件
        try {
            //上传文件
            file.transferTo(newFile);

            //数据库存储的相对路径
            String projectPath = servletContext.getContextPath();
            String contextpath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+projectPath;
            String url = contextpath + "/resource/"+dateName;
            System.out.println("相对路径:"+url);
            //文件名与文件URL存入数据库表



        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        res.setCode(ResultCode.SUCCESS);
        res.setContent(null);
        res.setMsg("上传成功");
        return JSONUtils.toJSONString(res);
    }

}
