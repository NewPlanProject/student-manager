package org.heran.edu.student.domain;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.heran.edu.student.util.mybatis.domain.PageEntity;

@Data
@NoArgsConstructor
public class TbAdmin extends PageEntity implements Serializable {

    private static final long serialVersionUID = -1L;
    private long adminId;
    private long roleId;
    private String name;
    private String realName;
    private String loginPwd;
    private String adminPhone;
    private String adminEmail;
    private String lastLoginIp;
    private String lastLoginTime;
    private String remark;
    private String status;
    private long createUser;
    private java.sql.Timestamp createtime;
    private long updateUser;
    private java.sql.Timestamp updatetime;

}
