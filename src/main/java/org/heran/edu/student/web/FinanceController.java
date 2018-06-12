package org.heran.edu.student.web;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.heran.edu.student.service.FinanceService;
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
@RequestMapping("/finance")
public class FinanceController {
    @Autowired
    private FinanceService financeService;

    @ApiOperation(value = "新增财务信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "financeSaveInVO", value = "新增财务信息对象", required = false, paramType = "body", dataType = "FinanceSaveInVO")
    })
    @PutMapping(value = "add", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String add(@RequestBody FinanceSaveInVO financeSaveInVO){
        log.debug("Enter add gradeSaveInVOs={}", financeSaveInVO);
        Result<Boolean> resBean = new Result();
        try {
            resBean = financeService.addFinance(financeSaveInVO);
        }catch (Exception e){
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("保存失败");
            log.error("add failed", e);
        }
        log.debug("add={}",resBean);
        return JSON.toJSONString(resBean);
    }


    @ApiOperation(value = "修改财务信息功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "financeUpdateInVO", value = "财务信息修改对象", required = false, paramType = "body", dataType = "FinanceUpdateInVO")
    })
    @PutMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(@RequestBody FinanceUpdateInVO financeUpdateInVO){
        log.debug("Enter update financeUpdateInVO={}", financeUpdateInVO);
        Result<Boolean> resBean = new Result();
        try {
            resBean = financeService.updateFinance(financeUpdateInVO);
        }catch (Exception e){
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("修改失败");
            log.error("update failed", e);
        }
        log.debug("update={}",resBean);
        return JSON.toJSONString(resBean);
    }


    @ApiOperation(value = "财务信息查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "financeInfoInVO", value = "财务信息查询对象", required = false, paramType = "body", dataType = "FinanceInfoInVO")
    })
    @PostMapping(value = "financeInfoList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String financeInfoList(@RequestBody FinanceInfoInVO financeInfoInVO){
        log.debug("Enter financeInfoList financeInfoInVO={}", financeInfoInVO);
        Result<Map<String,Object>> resBean = new Result<Map<String,Object>>(ResultCode.SUCCESS,"查询成功",null);
        try {
            resBean = financeService.financeInfoList(financeInfoInVO);
        }catch (Exception e){
            log.error("financeInfoList failed",e);
        }
        log.debug("financeInfoList={}",resBean);
        return JSON.toJSONString(resBean);
    }


    @ApiOperation(value = "财务详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = false, paramType = "query", dataType = "string")
    })
    @GetMapping(value = "detail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String detail(@RequestParam String id){
        log.debug("Enter detail id={}", id);
        Result<Map<String,Object>> resBean = new Result<Map<String,Object>>(ResultCode.SUCCESS,"查询成功",null);
        try {
            resBean = financeService.detail(id);
        }catch (Exception e){
            log.error("detail failed",e);
        }
        log.debug("detail={}",resBean);
        return JSON.toJSONString(resBean);
    }


    @ApiOperation(value="财务信息删除", notes="根据id删除数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "财务信息对象id", required = true, dataType = "string", paramType = "query")
    })
    @DeleteMapping(value="/del", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String del(@RequestParam(value = "ids") String[] ids) {
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.SUCCESS,"删除成功",true);
        log.debug("Enter del ids={}", ids);
        Boolean result = false;
        try {
            result = financeService.del(ids);
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
