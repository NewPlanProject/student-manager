package org.heran.edu.student.web;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.heran.edu.student.service.AdminRoleService;
import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.util.data.ResultCode;
import org.heran.edu.student.util.dispose.StringUtil;
import org.heran.edu.student.vo.AdminAddVo;
import org.heran.edu.student.vo.AdminRoleAddVo;
import org.heran.edu.student.vo.AdminRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by YaphetS on 2018/6/12.
 */

@Slf4j
@Controller
@RequestMapping("/adminRole")
public class AdminRoleController {

    @Autowired
    private AdminRoleService adminRoleService;

    @ApiOperation(value = "角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminRoleVo", value = "管理员查询", required = false, paramType = "body", dataType = "AdminRoleVo")
    })
    @PutMapping(value = "selectAdminRolePage", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectAdminPage(@RequestBody AdminRoleVo adminRoleVo){
        Result<Map<String,Object>> resBean = new Result<Map<String,Object>>(ResultCode.SUCCESS,"查询成功",null);
        try {
            resBean = this.adminRoleService.selectAdminRolePage(adminRoleVo);
        }catch (Exception e){
            e.printStackTrace();
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("查詢錯誤"+ StringUtil.getException(e));
        }
        return  JSON.toJSONString(resBean);
    }

    @ApiOperation(value = "新增角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminRoleAddVo", value = "新增角色", required = false, paramType = "body", dataType = "AdminRoleAddVo")
    })
    @PutMapping(value = "adminRoleAddVo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addAdmin(@RequestBody AdminRoleAddVo adminRoleAddVo){
        Result<Boolean> resBean = new Result();
        try{
            resBean = this.adminRoleService.addAdminRole(adminRoleAddVo);
        }catch (Exception e){
            e.printStackTrace();
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("添加失败"+ StringUtil.getException(e));
        }
        return JSON.toJSONString(resBean);
    }


    @ApiOperation(value = "修改角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminRoleAddVo", value = "修改角色", required = false, paramType = "body", dataType = "AdminRoleAddVo")
    })
    @PutMapping(value = "updateAdmin", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateAdmin(@RequestBody AdminRoleAddVo AdminRoleAddVo){
        Result<Boolean> resBean = new Result();
        try{
            resBean = this.adminRoleService.updateAdminRole(AdminRoleAddVo);
        }catch (Exception e){
            e.printStackTrace();
            resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("修改失败"+ StringUtil.getException(e));
        }
        return JSON.toJSONString(resBean);
    }

    @ApiOperation(value = "删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", required = true, dataType = "string", paramType = "query")
    })
    @PutMapping(value = "deleteAdmin", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteAdmin(@RequestParam(value = "ids") String ids){
        Result<Boolean> resBean = new Result();
        try{
            resBean = this.adminRoleService.deleteAdminRole(ids);
        }catch (Exception e){
            e.printStackTrace(); resBean.setCode(ResultCode.ERROR_SERVICE);
            resBean.setMsg("删除失败"+ StringUtil.getException(e));
        }
        return JSON.toJSONString(resBean);
    }

}
