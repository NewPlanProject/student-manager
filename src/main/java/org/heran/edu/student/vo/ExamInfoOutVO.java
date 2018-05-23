package org.heran.edu.student.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by git on 2017/8/16.
 */
@Data
@NoArgsConstructor
public class ExamInfoOutVO {
    private long id;
    private String name;
}
