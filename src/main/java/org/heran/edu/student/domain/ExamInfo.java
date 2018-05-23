package org.heran.edu.student.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.heran.edu.student.util.mybatis.domain.PageEntity;

@Data
@NoArgsConstructor
public class ExamInfo extends PageEntity implements Serializable {

    private static final long serialVersionUID = -1L;
    private long id;
    private long pid;
    private String name;
    private String code;
    private String value;
    private String type;

}
