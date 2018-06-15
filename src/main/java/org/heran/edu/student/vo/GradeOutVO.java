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
public class GradeOutVO{

    private String id;
    private String examManagerId;
    private String name;
    private String idCard;
    private String testNumber;
    private String courseId;
    private String courseName;
    private String courseCode;
    private String courseMark;
    private String mark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date examTime;

}
