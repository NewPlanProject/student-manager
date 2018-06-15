package org.heran.edu.student.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by YaphetS on 2018/6/14.
 */
@Data
@NoArgsConstructor
public class AdminLoginVo {

    @ApiModelProperty(value = "管理用户名")
    private String adminEmail;
    @ApiModelProperty(value = "登录密码")
    private String loginPwd;




}
