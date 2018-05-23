package org.heran.edu.student.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.heran.edu.student.dao.ExamInfoDao;
import org.heran.edu.student.dao.StuInfoDao;
import org.heran.edu.student.domain.ExamInfo;
import org.heran.edu.student.domain.StuInfo;
import org.heran.edu.student.service.ExamInfoService;
import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.util.data.ResultCode;
import org.heran.edu.student.util.dispose.UUIDUtil;
import org.heran.edu.student.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * User: Mrs.Jia
 * Date: 2018/4/26
 * Time: 11:40
 */
@Service
@Slf4j
@Transactional
class ExamInfoServiceImpl implements ExamInfoService {

    @Autowired
    private ExamInfoDao examInfoDao;

    @Override
    public Result<Map<String, Object>> colleges() {
        Result<Map<String, Object>> res = new Result<Map<String, Object>>(ResultCode.ERROR_DATA,"查询失败",null);
        List<ExamInfo> examInfos = new ArrayList<>();
        Map<String, Object> resultMap = new HashedMap();
        ExamInfo examInfo=new ExamInfo();
        List<ExamInfoOutVO> examInfoOutVOList=new ArrayList<>();
        try {
            examInfos = this.examInfoDao.selectList(examInfo, "examInfos");
            for(ExamInfo exam:examInfos){
                ExamInfoOutVO examInfoOutVO=new ExamInfoOutVO();
                BeanUtils.copyProperties(exam,examInfoOutVO);
                examInfoOutVOList.add(examInfoOutVO);
            }
            resultMap = new HashMap<String, Object>();
            resultMap.put("rows",examInfoOutVOList);
        }catch (Exception e){
            log.error("Find Exception", e);
        }
        res.setCode(ResultCode.SUCCESS);
        res.setContent(resultMap);
        res.setMsg("查询成功");
        log.debug("Get colleges size={}",examInfos!=null?examInfos.size() : "");
        return res;
    }

    @Override
    public Result<Map<String, Object>> majors(Integer pid) {
        Result<Map<String, Object>> res = new Result<Map<String, Object>>(ResultCode.ERROR_DATA,"查询失败",null);
        List<ExamInfo> examInfos = new ArrayList<>();
        Map<String, Object> resultMap = new HashedMap();
        ExamInfo examInfo=new ExamInfo();
        examInfo.setPid(pid);
        List<ExamInfoOutVO> examInfoOutVOList=new ArrayList<>();
        try {
            examInfos = this.examInfoDao.selectList(examInfo, "majors");
            for(ExamInfo exam:examInfos){
                ExamInfoOutVO examInfoOutVO=new ExamInfoOutVO();
                BeanUtils.copyProperties(exam,examInfoOutVO);
                examInfoOutVOList.add(examInfoOutVO);
            }
            resultMap = new HashMap<String, Object>();
            resultMap.put("rows",examInfoOutVOList);
        }catch (Exception e){
            log.error("Find Exception", e);
        }
        res.setCode(ResultCode.SUCCESS);
        res.setContent(resultMap);
        res.setMsg("查询成功");
        log.debug("Get majors size={}",examInfos!=null?examInfos.size() : "");
        return res;
    }

    @Override
    public Result<Map<String, Object>> courses(Integer pid) {
        Result<Map<String, Object>> res = new Result<Map<String, Object>>(ResultCode.ERROR_DATA,"查询失败",null);
        List<ExamInfo> examInfos = new ArrayList<>();
        Map<String, Object> resultMap = new HashedMap();
        ExamInfo examInfo=new ExamInfo();
        examInfo.setPid(pid);
        List<CourseInfoOutVO> courseInfoOutVOs=new ArrayList<>();
        try {
            examInfos = this.examInfoDao.selectList(examInfo, "courses");
            for(ExamInfo exam:examInfos){
                CourseInfoOutVO courseInfoOutVO=new CourseInfoOutVO();
                BeanUtils.copyProperties(exam,courseInfoOutVO);
                courseInfoOutVOs.add(courseInfoOutVO);
            }
            resultMap = new HashMap<String, Object>();
            resultMap.put("rows",courseInfoOutVOs);
        }catch (Exception e){
            log.error("Find Exception", e);
        }
        res.setCode(ResultCode.SUCCESS);
        res.setContent(resultMap);
        res.setMsg("查询成功");
        log.debug("Get courses size={}",examInfos!=null?examInfos.size() : "");
        return res;
    }
}
