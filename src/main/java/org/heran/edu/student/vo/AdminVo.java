package org.heran.edu.student.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by YaphetS on 2018/5/2.
 */

@Data
@NoArgsConstructor
public class AdminVo {

    @ApiModelProperty(value = "管理员id")
    private Long adminId;
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
    @ApiModelProperty(value = "上次登录时间")
    private String lastLoginTime;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "用户状态：0正常 1：禁用  2 逻辑删除")
    private String status;
    @ApiModelProperty(value = "创建人ID")
    private Long createUser;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "修改人")
    private Long updateUser;
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;



}
