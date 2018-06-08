package org.heran.edu.student.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.heran.edu.student.util.mybatis.domain.PageEntity;

@Data
@NoArgsConstructor
public class ExamManager extends PageEntity implements Serializable {

    private static final long serialVersionUID = -1L;
    private String id;  //主键
    private String stuId;   //学生id
    private String name;    //姓名
    private String idCard;  //身份证号码
    private String certificateNo;   //证书准考证号
    private String admissiontNo;    //自考准考证号
    private String oldAcademy;  //原院校
    private String nowAcademy;  //现院校
    private String major;   //专业
    private String courseName;  //课程名称
    private String courseId;    //课程id
    private String courseCode;  //课程代码
    private String courseMark;  //学分
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registionTime; //报考时间
    private String gradation;   //层次(高起专/专升本/高起本)
    private String testNumber;  //考次号
    private String stuRecord;   //01:新生，02:老生
    private String createUser;  //创建人
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;    //创建时间
    private String updateUser;  //修改人
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;    //修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startDate;     //开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endDate;       //结束时间

}
