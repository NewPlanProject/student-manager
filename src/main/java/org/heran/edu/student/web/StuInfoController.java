package org.heran.edu.student.web;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.heran.edu.student.service.StuInfoService;
import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.util.data.ResultCode;
import org.heran.edu.student.vo.StuInfoInVO;
import org.heran.edu.student.vo.StudentRegisterInVO;
import org.heran.edu.student.vo.StudentUpdateInVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/stu")
public class StuInfoController {

    @Autowired
    private StuInfoService stuInfoService;

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
}
