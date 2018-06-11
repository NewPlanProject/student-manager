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
public class GradeInfoInVO {
    @ApiModelProperty(value = "起始位置",example = "0")
    private int startLine = 0;
    @ApiModelProperty(value = "每页条数",example = "10")
    private int limitLine = 10;
    @ApiModelProperty(value = "排序字段",example = "create_time")
    private String orderString = "create_time";
    @ApiModelProperty(value = "正序 倒序",example = "DESC")
    private String sequence = "DESC";
    @ApiModelProperty(value = "考试时间",example = "考试时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date examTime;
    @ApiModelProperty(value = "姓名",example = "甲甲")
    private String name;
    @ApiModelProperty(value = "身份证号",example = "身份证号")
    private String idCard;
    @ApiModelProperty(value = "课程名称",example = "课程名称")
    private String courseName;
    @ApiModelProperty(value = "课程代码",example = "课程代码")
    private String courseCode;
    @ApiModelProperty(value = "成绩",example = "成绩")
    private String mark;
    @ApiModelProperty(value = "考次号",example = "考次号")
    private String testNumber;

}
