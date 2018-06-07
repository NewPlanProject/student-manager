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
    private String id;
    private String stuId;
    private String name;
    private String idCard;
    private String certificateNo;
    private String admissiontNo;
    private String oldAcademy;
    private String nowAcademy;
    private String major;
    private String courseName;
    private String courseId;
    private String courseCode;
    private String courseMark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registionTime;
    private String gradation;
    private String testNumber;
    private String stuRecord;
    private String createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String updateUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startDate;        //开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endDate;         //结束时间

}
