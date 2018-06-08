package org.heran.edu.student.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.heran.edu.student.util.mybatis.domain.PageEntity;

@Data
@NoArgsConstructor
public class Grade extends PageEntity implements Serializable {

    private static final long serialVersionUID = -1L;
    private String id;  //主键
    private String examManagerId;   //报考管理id
    private String name;    //姓名
    private String idCard;  //身份证号码
    private String testNumber;  //考次号
    private String courseId;    //课程id
    private String courseName;  //课程名称
    private String courseCode;  //课程代码
    private String courseMark;  //学分
    private String mark;    //成绩
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date examTime;  //考试时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;    //创建时间
    private String createUser;  //创建人
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;    //修改时间
    private String updateUser;  //修改人
}
