package org.heran.edu.student.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.heran.edu.student.util.mybatis.domain.PageEntity;

@Data
@NoArgsConstructor
public class TbStudent extends PageEntity implements Serializable {

    private static final long serialVersionUID = -1L;
    private int id;
    private String name;

}
