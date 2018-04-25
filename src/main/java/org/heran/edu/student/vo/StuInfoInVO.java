package org.heran.edu.student.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by git on 2017/8/16.
 */
@Data
@NoArgsConstructor
public class StuInfoInVO {

    @ApiModelProperty(value = "起始位置",example = "0")
    private int startLine = 0;
    @ApiModelProperty(value = "每页条数",example = "10")
    private int limitLine = 10;
    @ApiModelProperty(value = "排序字段",example = "createtime")
    private String orderString = "createtime";
    @ApiModelProperty(value = "正序 倒序",example = "DESC")
    private String sequence = "DESC";
    @ApiModelProperty(value = "开始时间",hidden = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startDate;
    @ApiModelProperty(value = "结束时间",hidden = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endDate;
    @ApiModelProperty(value = "姓名",example = "甲甲")
    private String name;
    @ApiModelProperty(value = "自考准考证号",example = "自考准考证号")
    private String admissiontNo;
    @ApiModelProperty(value = "证书准考证号",example = "证书准考证号")
    private String certificateNo;
    @ApiModelProperty(value = "身份证号",example = "身份证号")
    private String idCard;
    @ApiModelProperty(value = "报考项目(自考/成考/网教/职业资格证/MBA)",example = "报考项目")
    private String registionItem;
    @ApiModelProperty(value = "专业",example = "金融")
    private String major;
    @ApiModelProperty(value = "考区",example = "考区")
    private String examArae;
    @ApiModelProperty(value = "考期",example = "考期")
    private String batch;
    @ApiModelProperty(value = "注册时间",example = "2018-04-25 06:08:28")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;
}
