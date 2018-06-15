package org.heran.edu.student.service;

import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.vo.AdminAddVo;
import org.heran.edu.student.vo.AdminLoginVo;
import org.heran.edu.student.vo.AdminVo;

import java.util.Map;

/**
 * Created by YaphetS on 2018/5/2.
 */
public interface AdminService {


    Result<Map<String,Object>> selectAdminPage(AdminVo adminVo);

    Result<Boolean> addAdmin(AdminAddVo adminAddVo);

    Result<Boolean> updateAdmin(AdminAddVo adminAddVo);

    Result<Boolean> deleteAdmin(String ids);

    Result<Boolean> resetPassWord(String ids);

    Result<Map<String, Object>> loginAdmin(AdminLoginVo adminLoginVo);

    Result<Boolean> loginOffAdmin(Long id);
}
