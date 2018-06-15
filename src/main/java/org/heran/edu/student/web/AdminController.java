package org.heran.edu.student.web;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.heran.edu.student.service.AdminService;
import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.util.data.ResultCode;
import org.heran.edu.student.util.dispose.StringUtil;
import org.heran.edu.student.vo.AdminAddVo;
import org.heran.edu.student.vo.AdminLoginVo;
import org.heran.edu.student.vo.AdminVo;
import org.heran.edu.student.vo.StudentRegisterInVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.Map;

/**
 * Created by YaphetS on 2018/5/2.
 */

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "管理员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminVO", value = "管理员查询", required = false, paramType = "body", dataType = "AdminVo")
    })
    @PutMapping(value = "selectAdminPage", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectAdminPage(@RequestBody AdminVo adminVo){
        Result<Map<String,Object>> resBean = new Result<Map<String,Object>>(ResultCode.SUCCESS,"查询成功",null);
        try {
            resBean = this.adminService.selectAdminPage(adminVo);
        }catch (Exception e){
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("查詢錯誤"+ StringUtil.getException(e));
        }
        return  JSON.toJSONString(resBean);
    }

    @ApiOperation(value = "管理员添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminAddVo", value = "管理员添加", required = false, paramType = "body", dataType = "AdminAddVo")
    })
    @PutMapping(value = "addAdmin", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addAdmin(@RequestBody AdminAddVo adminAddVo){
        Result<Boolean> resBean = new Result();
        try{
            resBean = this.adminService.addAdmin(adminAddVo);
        }catch (Exception e){
            e.printStackTrace();
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("添加失败"+ StringUtil.getException(e));
        }
        return JSON.toJSONString(resBean);
    }


    @ApiOperation(value = "管理员修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminAddVo", value = "管理员修改", required = false, paramType = "body", dataType = "AdminAddVo")
    })
    @PutMapping(value = "updateAdmin", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateAdmin(@RequestBody AdminAddVo adminAddVo){
        Result<Boolean> resBean = new Result();
        try{
            resBean = this.adminService.updateAdmin(adminAddVo);
        }catch (Exception e){
            e.printStackTrace();
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("修改失败"+ StringUtil.getException(e));
        }
        return JSON.toJSONString(resBean);
    }

    @ApiOperation(value = "管理员删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "管理员删除", required = true, dataType = "string", paramType = "query")
    })
    @PutMapping(value = "deleteAdmin", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteAdmin(@RequestParam (value = "ids") String ids){
        Result<Boolean> resBean = new Result();
        try{
            resBean = this.adminService.deleteAdmin(ids);
        }catch (Exception e){
            e.printStackTrace();
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("删除失败"+ StringUtil.getException(e));
        }
        return JSON.toJSONString(resBean);
    }

    @ApiOperation(value = "密码重置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "密码重置", required = true, dataType = "string", paramType = "query")
    })
    @PutMapping(value = "resetPassWord", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String resetPassWord(@RequestParam (value = "ids") String ids){
        Result<Boolean> resBean = new Result();
        try{
            resBean = this.adminService.resetPassWord(ids);
        }catch (Exception e){
            e.printStackTrace();
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("密码重置失败"+ StringUtil.getException(e));
        }
        return JSON.toJSONString(resBean);
    }


    @ApiOperation(value = "管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminLoginVo", value = "管理员登录", required = false, paramType = "body", dataType = "AdminLoginVo")
    })
    @PutMapping(value = "loginAdmin", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String loginAdmin(@RequestBody AdminLoginVo adminLoginVo){
        Result<Map<String,Object>> resBean = new Result<Map<String,Object>>();
        try{
            resBean = this.adminService.loginAdmin(adminLoginVo);
        }catch (Exception e){
            e.printStackTrace();
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("登录出错了。。。"+ StringUtil.getException(e));
       }
        return  JSON.toJSONString(resBean);
    }

    @ApiOperation(value = "管理员登出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员登出", required = true, dataType = "Long", paramType = "query")
    })
    @PutMapping(value = "loginOffAdmin", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String loginAdmin(@RequestParam (value = "id") Long id){
        Result<Boolean> resBean = new Result();
        try{
            resBean = this.adminService.loginOffAdmin(id);
        }catch (Exception e){
            e.printStackTrace();
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("登录出错了。。。"+ StringUtil.getException(e));
        }
        return  JSON.toJSONString(resBean);
    }



}
