package org.heran.edu.student.domain;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.heran.edu.student.util.mybatis.domain.PageEntity;

@Data
@NoArgsConstructor
public class StuDictionary extends PageEntity implements Serializable {

    private static final long serialVersionUID = -1L;
    private long id;
    private String value;
    private String label;
    private String type;
    private String description;
    private long sort;
    private long createUser;
    private java.sql.Timestamp createtime;
    private long updateUser;
    private java.sql.Timestamp updatetime;
    private String remarks;
    private String delFlag;

}
