package org.heran.edu.student.web;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.heran.edu.student.service.CacheService;
import org.heran.edu.student.service.TbStudentService;
import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.util.data.ResultCode;
import org.heran.edu.student.vo.TbStudentInVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/student")
public class TbStudentController {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private TbStudentService tbStudentService;

    @ApiOperation(value = "学生信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tbStudentInVO", value = "学生对象", required = false, paramType = "body", dataType = "TbStudentInVO")
    })
    @PostMapping(value = "studentList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String studentList(@RequestBody TbStudentInVO tbStudentInVO, HttpServletRequest request){
        log.info("Enter studentList tbStudentInVO={}", tbStudentInVO);
        Result<Map<String,Object>> resBean = new Result<Map<String,Object>>(ResultCode.SUCCESS,"查询成功",null);
        //获取登录用户的相关信息
        try {
            resBean = tbStudentService.getStudentList(tbStudentInVO);
        }catch (Exception e){
            log.error("studentList failed",e);
        }
        log.info("studentList={}",resBean);
        return JSON.toJSONString(resBean);
    }
}
