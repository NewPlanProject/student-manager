package org.heran.edu.student.web;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.heran.edu.student.domain.ExamInfo;
import org.heran.edu.student.service.StuInfoService;
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
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/stu")
public class StuInfoController {

    @Autowired
    private StuInfoService stuInfoService;

    @Autowired
    private FastDFSClientWrapper dfsClient;

    /**
     * 上传文件
     * @param file
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileUrl= dfsClient.uploadFile(file);
        return JSON.toJSONString(fileUrl);
    }

    /**
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delFile", method = RequestMethod.GET)
    public String delFile(String filePath) throws Exception {
        String result="false";
        try {
            dfsClient.deleteFile(filePath);
            result="true";
        }catch (Exception e){
            log.error("delFile", e);
        }
        return JSON.toJSONString(result);
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


    @ApiOperation(value = "学生信息详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = false, paramType = "query", dataType = "string")
    })
    @GetMapping(value = "detail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String detail(@RequestParam String id){
        log.info("Enter detail id={}", id);
        Result<Map<String,Object>> resBean = new Result<Map<String,Object>>(ResultCode.SUCCESS,"查询成功",null);
        try {
            resBean = stuInfoService.detail(id);
        }catch (Exception e){
            log.error("detail failed",e);
        }
        log.info("detail={}",resBean);
        return JSON.toJSONString(resBean);
    }

}
