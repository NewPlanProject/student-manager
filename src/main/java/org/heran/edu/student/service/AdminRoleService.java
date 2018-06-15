package org.heran.edu.student.service;

import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.vo.AdminRoleAddVo;
import org.heran.edu.student.vo.AdminRoleVo;

import java.util.Map;

/**
 * Created by YaphetS on 2018/6/12.
 */
public interface AdminRoleService {


    Result<Map<String,Object>> selectAdminRolePage(AdminRoleVo adminRoleVo);


    Result<Boolean> addAdminRole(AdminRoleAddVo adminRoleAddVo);

    Result<Boolean> updateAdminRole(AdminRoleAddVo adminRoleAddVo);

    Result<Boolean> deleteAdminRole(String ids);
}
