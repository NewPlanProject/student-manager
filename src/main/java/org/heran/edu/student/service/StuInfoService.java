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
public interface StuInfoService {
    /**
     *
     * @param studentRegisterInVO
     * @return
     */
    Result<Boolean> addStuInfo(StudentRegisterInVO studentRegisterInVO);

    /**
     *
     * @param stuInfoInVO
     * @return
     */
    Result<Map<String,Object>> getStudentList(StuInfoInVO stuInfoInVO);

    /**
     *
     * @param studentUpdateInVO
     * @return
     */
    Result<Boolean> updateStu(StudentUpdateInVO studentUpdateInVO);

    /**
     *
     * @param ids
     * @return
     */
    Result<Boolean> updateBatchStatus(String ids);

    /**
     *
     * @param id
     * @return
     */
    Result<Map<String,Object>> detail(String id);
}
