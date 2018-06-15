package org.heran.edu.student.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by YaphetS on 2018/6/12.
 */

@Data
@NoArgsConstructor
public class AdminRoleVo {

    @ApiModelProperty(value = "角色id")
    private long id;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "角色类型")
    private String isDisabled;
    @ApiModelProperty(value = "创建人")
    private long createUser;
    @ApiModelProperty(value = "创建时间")
    private Date createtime;
    @ApiModelProperty(value = "是否删除 0 否 1 是")
    private String isDel;


}
