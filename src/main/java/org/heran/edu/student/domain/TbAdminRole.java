package org.heran.edu.student.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    private String isDel;

}
