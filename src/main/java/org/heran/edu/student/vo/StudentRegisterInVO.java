package org.heran.edu.student.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * User: Mrs.Jia
 * Date: 2018/4/26
 * Time: 11:40
 */
@Data
@NoArgsConstructor
public class StudentRegisterInVO {

    @ApiModelProperty(value = "姓名",example = "甲甲")
    private String name;
    @ApiModelProperty(value = "籍贯",example = "河北")
    private String nativePlace;
    @ApiModelProperty(value = "性别",example = "男")
    private String sex;
    @ApiModelProperty(value = "民族",example = "汉")
    private String nation;
    @ApiModelProperty(value = "手机号",example = "110")
    private String mobile;
    @ApiModelProperty(value = "邮箱",example = "124@163.com")
    private String mailbox;
    @ApiModelProperty(value = "身份证号",example = "123456")
    private String idCard;
    @ApiModelProperty(value = "层次(高起专/专升本/高起本)",example = "高起专")
    private String gradation;
    @ApiModelProperty(value = "现院校",example = "北大")
    private String nowAcademy;
    @ApiModelProperty(value = "专业",example = "金融")
    private String major;
    @ApiModelProperty(value = "报考项目(自考/成考/网教/职业资格证/MBA)",example = "MBA")
    private String registionItem;
    @ApiModelProperty(value = "考期",example = "考期")
    private String testPeriod;
    @ApiModelProperty(value = "考区",example = "考区")
    private String examArae;
    @ApiModelProperty(value = "学位与否[true:是;false:否]",example = "学位与否[true:是;false:否]")
    private String degreeOrNo;
    @ApiModelProperty(value = "最高学历",example = "最高学历")
    private String highestEdu;
    @ApiModelProperty(value = "课程顾问",example = "课程顾问")
    private String consultant;
    @ApiModelProperty(value = "咨询师",example = "咨询师")
    private String counsellors;
    @ApiModelProperty(value = "合同编号",example = "合同编号")
    private String contractNo;
    @ApiModelProperty(value = "收据编号",example = "收据编号")
    private String receiptNo;
    @ApiModelProperty(value = "培训费用",example = "培训费用")
    private String trainingFee;
    @ApiModelProperty(value = "已交费用",example = "已交费用")
    private String yetFee;
    @ApiModelProperty(value = "未交费用",example = "未交费用")
    private String noFee;
    @ApiModelProperty(value = "资料收集",example = "身份证,2寸照片,合同")
    private String dataGather;
    @ApiModelProperty(value = "备注",example = "备注")
    private String remark;
    @ApiModelProperty(value = "身份证正面图片地址",example = "身份证正面图片地址")
    private String cardFrontUrl;
    @ApiModelProperty(value = "身份证反面图片地址",example = "身份证反面图片地址")
    private String cardReverseUrl;
    @ApiModelProperty(value = "两寸照片图片地址",example = "两寸照片图片地址")
    private String photoUrl;
    @ApiModelProperty(value = "合同地址",example = "合同地址")
    private String contractUrl;
    @ApiModelProperty(value = "收据地址",example = "收据地址")
    private String receiptUrl;
    @ApiModelProperty(value = "学位证书地址",example = "学位证书地址")
    private String diplomaUrl;
    @ApiModelProperty(value = "报名时间",example = "2018-04-25 06:08:28")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date enlistsTime;
}
