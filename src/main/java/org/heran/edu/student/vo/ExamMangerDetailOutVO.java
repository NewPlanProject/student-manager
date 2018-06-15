package org.heran.edu.student.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by git on 2018/5/25.
 */
@Data
@NoArgsConstructor
public class ExamMangerDetailOutVO {

    @ApiModelProperty(value = "id",example = "123")
    private String id;
    @ApiModelProperty(value = "姓名",example = "甲甲")
    private String name;
    @ApiModelProperty(value = "自考准考证号",example = "自考准考证号")
    private String admissiontNo;
    @ApiModelProperty(value = "证书准考证号",example = "证书准考证号")
    private String certificateNo;
    @ApiModelProperty(value = "身份证号",example = "身份证号")
    private String idCard;
    @ApiModelProperty(value = "学院",example = "学院")
    private String nowAcademy;
    @ApiModelProperty(value = "专业",example = "金融")
    private String major;
    @ApiModelProperty(value = "课程ID",example = "课程ID")
    private String courseId;
    @ApiModelProperty(value = "课程名称",example = "课程名称")
    private String courseName;
    @ApiModelProperty(value = "课程代码",example = "课程代码")
    private String courseCode;
    @ApiModelProperty(value = "学分",example = "学分")
    private String courseMark;
    @ApiModelProperty(value = "考次号",example = "考次号")
    private String testNumber;
    @ApiModelProperty(value = "报考时间",example = "2018-04-25 06:08:28")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date registionTime;
    @ApiModelProperty(value = "考试类型（层次）",example = "考试类型（层次）")
    private String gradation;
    @ApiModelProperty(value = "新生/老生",example = "01：新生，02：老生")
    private String stuRecord;

}
