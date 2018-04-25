package org.heran.edu.student.service;

import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.vo.StuInfoInVO;
import org.heran.edu.student.vo.StudentRegisterInVO;

import java.util.Map;

/**
 * User: Mr.zheng
 * Date: 2017/8/16
 * Time: 11:39
 */
public interface StuInfoService {

    Result<Boolean> addStuInfo(StudentRegisterInVO studentRegisterInVO);

    Result<Map<String,Object>> getStudentList(StuInfoInVO stuInfoInVO);
}
