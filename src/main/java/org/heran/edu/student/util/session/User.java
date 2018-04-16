package org.heran.edu.student.util.session;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by mazh3 on 2017/9/16.
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 994094523249853765L;

    private String userid;
    private String username;
    private String fullName;
    private String orgid;
    private String orgname;
    private String orgcode;
    private String roleid;
    private String rolename;
    private String rolelevel;
    private Map<String,String> roleResources;

}
