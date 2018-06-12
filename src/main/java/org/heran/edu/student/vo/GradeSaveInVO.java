package org.heran.edu.student.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.heran.edu.student.util.mybatis.domain.PageEntity;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class GradeSaveInVO{

    @ApiModelProperty(value = "报考信息id",example = "报考信息id")
    private String examManagerId;
    @ApiModelProperty(value = "姓名",example = "姓名")
    private String name;
    @ApiModelProperty(value = "身份证号码",example = "身份证号码")
    private String idCard;
    @ApiModelProperty(value = "考次号",example = "考次号")
    private String testNumber;
    @ApiModelProperty(value = "课程id",example = "课程id")
    private String courseId;
    @ApiModelProperty(value = "课程名称",example = "课程名称")
    private String courseName;
    @ApiModelProperty(value = "课程代码",example = "课程代码")
    private String courseCode;
    @ApiModelProperty(value = "学分",example = "学分")
    private String courseMark;
    @ApiModelProperty(value = "成绩",example = "成绩")
    private String mark;
    @ApiModelProperty(value = "考试时间",example = "考试时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date examTime;

}
