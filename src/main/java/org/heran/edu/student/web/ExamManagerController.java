package org.heran.edu.student.web;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.heran.edu.student.service.ExamManagerService;
import org.heran.edu.student.service.StuInfoService;
import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.util.data.ResultCode;
import org.heran.edu.student.util.dispose.SpringContextUtil;
import org.heran.edu.student.util.fdfs.FastDFSClientWrapper;
import org.heran.edu.student.vo.*;
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
@RequestMapping("/exam-m")
public class ExamManagerController {

    @Autowired
    private ExamManagerService examManagerService;


    @ApiOperation(value = "判断是老生还是新生")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idCard", value = "身份证号", required = false, paramType = "query", dataType = "string")
    })
    @GetMapping(value = "ifIdCard", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String ifIdCard(@RequestParam String idCard){
        log.debug("Enter ifIdCard idCard={}", idCard);
        Result<Map<String,Object>> resBean = new Result<Map<String,Object>>(ResultCode.SUCCESS,"查询成功",null);
        try {
            resBean = examManagerService.ifIdCard(idCard);
        }catch (Exception e){
            log.error("ifIdCard failed",e);
        }
        log.debug("ifIdCard={}",resBean);
        return JSON.toJSONString(resBean);
    }


    @ApiOperation(value = "提交报考信息功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examMangerSaveInVO", value = "报考信息保存对象", required = false, paramType = "body", dataType = "ExamMangerSaveInVO")
    })
    @PutMapping(value = "add", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String add(@RequestBody ExamMangerSaveInVO examMangerSaveInVO){
        log.debug("Enter add examMangerSaveInVO={}", examMangerSaveInVO);
        Result<Boolean> resBean = new Result();
        try {
            resBean = examManagerService.add(examMangerSaveInVO);
        }catch (Exception e){
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("保存失败");
            log.error("add failed", e);
        }
        log.debug("add={}",resBean);
        return JSON.toJSONString(resBean);
    }

    @ApiOperation(value = "报考信息查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examMangerInfoInVO", value = "学生查询对象", required = false, paramType = "body", dataType = "ExamMangerInfoInVO")
    })
    @PostMapping(value = "examInfoList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String examInfoList(@RequestBody ExamMangerInfoInVO examMangerInfoInVO){
        log.debug("Enter examInfoList examMangerInfoInVO={}", examMangerInfoInVO);
        Result<Map<String,Object>> resBean = new Result<Map<String,Object>>(ResultCode.SUCCESS,"查询成功",null);
        try {
            resBean = examManagerService.examInfoList(examMangerInfoInVO);
        }catch (Exception e){
            log.error("examInfoList failed",e);
        }
        log.debug("examInfoList={}",resBean);
        return JSON.toJSONString(resBean);
    }

    @ApiOperation(value = "报考详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = false, paramType = "query", dataType = "string")
    })
    @GetMapping(value = "detail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String detail(@RequestParam String id){
        log.debug("Enter detail id={}", id);
        Result<Map<String,Object>> resBean = new Result<Map<String,Object>>(ResultCode.SUCCESS,"查询成功",null);
        try {
            resBean = examManagerService.detail(id);
        }catch (Exception e){
            log.error("detail failed",e);
        }
        log.debug("detail={}",resBean);
        return JSON.toJSONString(resBean);
    }

}
