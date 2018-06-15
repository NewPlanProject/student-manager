package org.heran.edu.student.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by YaphetS on 2018/5/23.
 */
@Data
@NoArgsConstructor
public class AdminAddVo {


    @ApiModelProperty(value = "角色id")
    private Long roleId;
    @ApiModelProperty(value = "管理用户名")
    private String name;
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    @ApiModelProperty(value = "登录密码")
    private String loginPwd;
    @ApiModelProperty(value = "手机号")
    private String adminPhone;
    @ApiModelProperty(value = "电子邮箱")
    private String adminEmail;
    @ApiModelProperty(value = "登录ip")
    private String lastLoginIp;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "用户状态：0正常 1：禁用  2 逻辑删除")
    private String status;

}
