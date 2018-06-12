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
public class FinanceUpdateInVO{

    @ApiModelProperty(value = "主键",example = "主键")
    private String id;
    @ApiModelProperty(value = "姓名",example = "姓名")
    private String name;
    @ApiModelProperty(value = "身份证号码",example = "身份证号码")
    private String idCard;
    @ApiModelProperty(value = "学院",example = "学院")
    private String nowAcademy;
    @ApiModelProperty(value = "专业",example = "金融")
    private String major;
    @ApiModelProperty(value = "考试类型（层次）",example = "考试类型（层次）")
    private String gradation;
    @ApiModelProperty(value = "高校总学费",example = "高校总学费")
    private String schoolAllCost;
    @ApiModelProperty(value = "高校第一次付款",example = "高校第一次付款")
    private String schoolOne;
    @ApiModelProperty(value = "高校第一次缴费日期",example = "高校第一次缴费日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date schoolOneDate;
    @ApiModelProperty(value = "高校第一次缴费上传凭证路径",example = "高校第一次缴费上传凭证路径")
    private String schoolOneUrl;
    @ApiModelProperty(value = "高校第二次付款",example = "高校第二次付款")
    private String schoolTwo;
    @ApiModelProperty(value = "高校第二次缴费日期",example = "高校第二次缴费日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date schoolTwoDate;
    @ApiModelProperty(value = "高校第二次缴费上传凭证",example = "高校第二次缴费上传凭证")
    private String schoolTwoUrl;
    @ApiModelProperty(value = "高校第三次付款",example = "高校第三次付款")
    private String schoolThree;
    @ApiModelProperty(value = "高校第三次缴费日期",example = "高校第三次缴费日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date schoolThreeDate;
    @ApiModelProperty(value = "高校第三次缴费上传凭证",example = "高校第三次缴费上传凭证")
    private String schoolThreeUrl;
    @ApiModelProperty(value = "招生老师总费用",example = "招生老师总费用")
    private String teacherAllCost;
    @ApiModelProperty(value = "招生老师付经费一",example = "招生老师付经费一")
    private String teacherOne;
    @ApiModelProperty(value = "招生老师第一次缴费日期",example = "招生老师第一次缴费日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date teacherOneDate;
    @ApiModelProperty(value = "招生老师第一次缴费上传凭证",example = "招生老师第一次缴费上传凭证")
    private String teacherOneUrl;
    @ApiModelProperty(value = "招生老师付经费二",example = "招生老师付经费二")
    private String teacherTwo;
    @ApiModelProperty(value = "招生老师第二次缴费日期",example = "招生老师第二次缴费日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date teacherTwoDate;
    @ApiModelProperty(value = "招生老师第二次上传凭证",example = "招生老师第二次上传凭证")
    private String teacherTwoUrl;
    @ApiModelProperty(value = "招生老师付经费三",example = "招生老师付经费三")
    private String teacherThree;
    @ApiModelProperty(value = "招生老师第三次缴费日期",example = "招生老师第三次缴费日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date teacherThreeDate;
    @ApiModelProperty(value = "招生老师第三次上传凭证",example = "招生老师第三次上传凭证")
    private String teacherThreeUrl;
}
