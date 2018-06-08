package org.heran.edu.student.web;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.heran.edu.student.service.ExamManagerService;
import org.heran.edu.student.service.GradeService;
import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.util.data.ResultCode;
import org.heran.edu.student.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @ApiOperation(value = "新增成绩信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gradeSaveInVOs", value = "成绩信息集合", required = false, paramType = "body", dataType = "List")
    })
    @PutMapping(value = "add", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String add(@RequestBody List<GradeSaveInVO> gradeSaveInVOs){
        log.debug("Enter add gradeSaveInVOs={}", gradeSaveInVOs);
        Result<Boolean> resBean = new Result();
        try {
            resBean = gradeService.add(gradeSaveInVOs);
        }catch (Exception e){
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("保存失败");
            log.error("add failed", e);
        }
        log.debug("add={}",resBean);
        return JSON.toJSONString(resBean);
    }


    @ApiOperation(value = "修改成绩信息功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gradeUpdateInVO", value = "成绩信息修改对象", required = false, paramType = "body", dataType = "GradeUpdateInVO")
    })
    @PutMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(@RequestBody GradeUpdateInVO gradeUpdateInVO){
        log.debug("Enter update gradeUpdateInVO={}", gradeUpdateInVO);
        Result<Boolean> resBean = new Result();
        try {
            resBean = gradeService.update(gradeUpdateInVO);
        }catch (Exception e){
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("修改失败");
            log.error("update failed", e);
        }
        log.debug("update={}",resBean);
        return JSON.toJSONString(resBean);
    }


    @ApiOperation(value = "成绩管理信息查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gradeInfoInVO", value = "成绩查询对象", required = false, paramType = "body", dataType = "GradeInfoInVO")
    })
    @PostMapping(value = "gradeInfoList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String gradeInfoList(@RequestBody GradeInfoInVO gradeInfoInVO){
        log.debug("Enter gradeInfoList gradeInfoInVO={}", gradeInfoInVO);
        Result<Map<String,Object>> resBean = new Result<Map<String,Object>>(ResultCode.SUCCESS,"查询成功",null);
        try {
            resBean = gradeService.gradeInfoList(gradeInfoInVO);
        }catch (Exception e){
            log.error("gradeInfoList failed",e);
        }
        log.debug("gradeInfoList={}",resBean);
        return JSON.toJSONString(resBean);
    }


    @ApiOperation(value = "成绩详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = false, paramType = "query", dataType = "string")
    })
    @GetMapping(value = "detail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String detail(@RequestParam String id){
        log.debug("Enter detail id={}", id);
        Result<Map<String,Object>> resBean = new Result<Map<String,Object>>(ResultCode.SUCCESS,"查询成功",null);
        try {
            resBean = gradeService.detail(id);
        }catch (Exception e){
            log.error("detail failed",e);
        }
        log.debug("detail={}",resBean);
        return JSON.toJSONString(resBean);
    }


    @ApiOperation(value="成绩信息删除", notes="根据id删除数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "成绩信息对象id", required = true, dataType = "string", paramType = "query")
    })
    @DeleteMapping(value="/del", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String del(@RequestParam(value = "ids") String[] ids) {
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.SUCCESS,"删除成功",true);
        log.debug("Enter del ids={}", ids);
        Boolean result = false;
        try {
            result = gradeService.del(ids);
        } catch (Exception e) {
            log.error("Found Exception:", e);
        }
        if(!result){
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setContent(result);
            resBean.setMsg("删除失败");
        }
        return JSON.toJSONString(resBean);
    }

}
