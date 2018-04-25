package org.heran.edu.student.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.heran.edu.student.util.mybatis.domain.PageEntity;

@Data
@NoArgsConstructor
public class StuInfo extends PageEntity implements Serializable {

    private static final long serialVersionUID = -1L;
    private String id;  //学生id
    private String name;    //姓名
    private String nativePlace; //籍贯
    private String sex; //性别
    private String nation;  //民族
    private String mobile;  //手机号
    private String mailbox; //邮箱
    private String idCard;  //身份证号
    private String certificateNo;   //证书准考证号
    private String admissiontNo;    //自考准考证号
    private String gradation;   //层次(高起专/专升本/高起本)
    private String nowAcademy;  //现院校
    private String major;   //专业
    private String registionItem;   //报考项目(自考/成考/网教/职业资格证/MBA)
    private String oldAcademy;  //原院校
    private String examArae;    //考区
    private String examPlace;    //考试地点
    private String testPeriod;  //考期
    private String batch;  //考期
    private String degreeOrNo; //学位与否[true:是;false:否]
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date enlistsTime;   //报名时间
    private String highestEdu;  //最高学历
    private String consultant;  //课程顾问
    private String counsellors; //咨询师
    private String contractNo;  //合同编号
    private String receiptNo;   //收据编号
    private String trainingFee; //培训费用
    private String yetFee;  //已交费用
    private String noFee;   //未交费用
    private String reducedPrice;    //优惠价格
    private String status;  //审核状态[1:通过;2:不通过]
    private String channelRealize;  //渠道了解
    private String payment; //支付方式
    private String dataGather;  //资料收集
    private String remark;  //备注
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startDate;        //开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endDate;         //结束时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;    //注册时间
    private String createUser;  //创建人
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatetime;    //修改时间
    private String updateUser;  //修改人

}
