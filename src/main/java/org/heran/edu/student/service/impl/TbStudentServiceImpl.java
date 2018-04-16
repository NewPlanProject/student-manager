package org.heran.edu.student.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.heran.edu.student.dao.TbStudentDao;
import org.heran.edu.student.domain.TbStudent;
import org.heran.edu.student.service.TbStudentService;
import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.util.data.ResultCode;
import org.heran.edu.student.vo.TbStudentInVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * User: Mr.zheng
 * Date: 2017/8/16
 * Time: 11:40
 */
@Service
@Slf4j
@Transactional
public class TbStudentServiceImpl implements TbStudentService {

    @Autowired
    private TbStudentDao tbStudentDao;

    @Override
    public Result<Map<String, Object>> getStudentList(TbStudentInVO tbStudentInVO) {
        Result<Map<String, Object>> res = new Result<Map<String, Object>>(ResultCode.ERROR_DATA,"查询失败",null);
        log.info("Enter getStudentList tbStudentInVO={}",tbStudentInVO);
        List<TbStudent> tbStudents = null;
        Map<String, Object> resultMap = null;
        TbStudent tbStudent= new TbStudent();
        BeanUtils.copyProperties(tbStudentInVO, tbStudent);
        int totalSize=0;
        try {
            tbStudents = this.tbStudentDao.selectList(tbStudent, "pageTbStudent");
            totalSize = this.tbStudentDao.selectCount(tbStudent,"pageTbStudentCount");
            resultMap = new HashMap<String, Object>();
            resultMap.put("rows",tbStudents);
            resultMap.put("totalSize",totalSize);
        }catch (Exception e){
            log.error("Find Exception", e);
        }
        res.setCode(ResultCode.SUCCESS);
        res.setContent(resultMap);
        res.setMsg("查询成功");
        log.info("Get getStudentList size={}",tbStudents!=null?tbStudents.size() : "");
        return res;
    }
}
