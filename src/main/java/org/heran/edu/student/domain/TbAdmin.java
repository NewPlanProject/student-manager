package org.heran.edu.student.domain;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.heran.edu.student.util.mybatis.domain.PageEntity;

@Data
@NoArgsConstructor
public class TbAdmin extends PageEntity implements Serializable {

    private static final long serialVersionUID = -1L;
    private Long adminId;
    private Long roleId;
    private String name;
    private String realName;
    private String loginPwd;
    private String adminPhone;
    private String adminEmail;
    private String lastLoginIp;
    private String lastLoginTime;
    private String remark;
    private String status;
    private Long createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Long updateUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
