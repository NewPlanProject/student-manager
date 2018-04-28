package org.heran.edu.student.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.heran.edu.student.util.mybatis.domain.PageEntity;

@Data
@NoArgsConstructor
public class TbAdminRole extends PageEntity implements Serializable {

    private static final long serialVersionUID = -1L;
    private long id;
    private String roleName;
    private String remark;
    private String isDisabled;
    private long createUser;
    private java.sql.Timestamp createtime;
    private String isDel;

}
