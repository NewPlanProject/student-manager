package org.heran.edu.student.service;

import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.vo.StuInfoInVO;
import org.heran.edu.student.vo.StudentRegisterInVO;
import org.heran.edu.student.vo.StudentUpdateInVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * User: Mrs.Jia
 * Date: 2018/4/26
 * Time: 11:40
 */
@Transactional
public interface ExamInfoService {

    /**
     *
     * @return
     */
    Result<Map<String, Object>> colleges();

    /**
     *
     * @param pid
     * @return
     */
    Result<Map<String,Object>> majors(Integer pid);

    /**
     *
     * @param pid
     * @return
     */
    Result<Map<String,Object>> courses(Integer pid);
}
