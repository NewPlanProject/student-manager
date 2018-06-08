package org.heran.edu.student.service;

import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.vo.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * User: Mrs.Jia
 * Date: 2018/4/26
 * Time: 11:40
 */
@Transactional
public interface GradeService {


    /**
     *
     * @param id
     * @return
     */
    Result<Map<String,Object>> detail(String id);

    /**
     *
     * @param ids
     * @return
     */
    Boolean del(String[] ids);


    /**
     *
     * @param gradeSaveInVO
     * @return
     */
    Result<Boolean> add(List<GradeSaveInVO> gradeSaveInVO);


    /**
     *
     * @param gradeUpdateInVO
     * @return
     */
    Result<Boolean> update(GradeUpdateInVO gradeUpdateInVO);

    /**
     *
     * @param gradeInfoInVO
     * @return
     */
    Result<Map<String,Object>> gradeInfoList(GradeInfoInVO gradeInfoInVO);
}
