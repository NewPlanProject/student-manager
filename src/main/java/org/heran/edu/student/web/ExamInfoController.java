package org.heran.edu.student.web;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.heran.edu.student.service.ExamInfoService;
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
@RequestMapping("/exam")
public class ExamInfoController {

    @Autowired
    private ExamInfoService examInfoService;

    @ApiOperation(value = "查询学院")
    @GetMapping(value = "colleges", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String colleges(){
        Result<Map<String,Object>> resBean = new Result<Map<String,Object>>(ResultCode.SUCCESS,"查询成功",null);
        try {
            resBean = examInfoService.colleges();
        }catch (Exception e){
            log.error("colleges failed",e);
        }
        log.debug("colleges={}",resBean);
        return JSON.toJSONString(resBean);
    }

    @ApiOperation(value = "查询某学院专业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "父级id", required = false, paramType = "query", dataType = "int")
    })
    @GetMapping(value = "majors", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String majors(@RequestParam Integer pid){
        log.debug("Enter majors pid={}", pid);
        Result<Map<String,Object>> resBean = new Result<Map<String,Object>>(ResultCode.SUCCESS,"查询成功",null);
        try {
            resBean = examInfoService.majors(pid);
        }catch (Exception e){
            log.error("majors failed",e);
        }
        log.debug("majors={}",resBean);
        return JSON.toJSONString(resBean);
    }

    @ApiOperation(value = "查询某专业课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "父级id", required = false, paramType = "query", dataType = "int")
    })
    @GetMapping(value = "courses", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String courses(@RequestParam Integer pid){
        log.debug("Enter courses pid={}", pid);
        Result<Map<String,Object>> resBean = new Result<Map<String,Object>>(ResultCode.SUCCESS,"查询成功",null);
        try {
            resBean = examInfoService.courses(pid);
        }catch (Exception e){
            log.error("courses failed",e);
        }
        log.debug("courses={}",resBean);
        return JSON.toJSONString(resBean);
    }

    @ApiOperation(value = "查询多个课程相关信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "课程id", required = false, paramType = "query", dataType = "int")
    })
    @GetMapping(value = "courseInfos", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String courseInfos(@RequestParam Long[] ids){
        log.debug("Enter courseInfos ids={}", ids);
        Result<Map<String,Object>> resBean = new Result<Map<String,Object>>(ResultCode.SUCCESS,"查询成功",null);
        try {
            resBean = examInfoService.courseInfos(ids);
        }catch (Exception e){
            log.error("courses failed",e);
        }
        log.debug("courses={}",resBean);
        return JSON.toJSONString(resBean);
    }
}
