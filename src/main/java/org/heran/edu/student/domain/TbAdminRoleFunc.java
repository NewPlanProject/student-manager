package org.heran.edu.student.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.heran.edu.student.util.mybatis.domain.PageEntity;

@Data
@NoArgsConstructor
public class TbAdminRoleFunc extends PageEntity implements Serializable {

    private static final long serialVersionUID = -1L;
    private long roleFunId;
    private long roleId;
    private String funModuleCode;
    private String remark;
    private long createUser;
    private java.sql.Timestamp createtime;

}
