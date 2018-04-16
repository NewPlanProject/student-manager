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
public class TbStudentInVO {

    @ApiModelProperty(value = "起始位置",example = "0")
    private int startLine = 0;
    @ApiModelProperty(value = "每页条数",example = "10")
    private int limitLine = 10;
    @ApiModelProperty(value = "排序字段",example = "name")
    private String orderString = "name";
    @ApiModelProperty(value = "正序 倒序",example = "DESC")
    private String sequence = "DESC";
    @ApiModelProperty(value = "开始时间",hidden = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startDate;
    @ApiModelProperty(value = "结束时间",hidden = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endDate;
    @ApiModelProperty(value = "状态",example = "1")
    private int id;
    private String name;
}
