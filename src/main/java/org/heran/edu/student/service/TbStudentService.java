package org.heran.edu.student.service;

import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.vo.TbStudentInVO;

import java.util.Map;

/**
 * User: Mr.zheng
 * Date: 2017/8/16
 * Time: 11:39
 */
public interface TbStudentService {


    Result<Map<String,Object>> getStudentList(TbStudentInVO tbStudentInVO);
}
