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
public class GradeUpdateInVO extends PageEntity implements Serializable {

    private static final long serialVersionUID = -1L;
    @ApiModelProperty(value = "成绩id",example = "成绩id")
    private String id;
    @ApiModelProperty(value = "成绩",example = "成绩")
    private String mark;
    @ApiModelProperty(value = "考试时间",example = "考试时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date examTime;

}
