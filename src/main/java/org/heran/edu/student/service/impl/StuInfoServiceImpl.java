package org.heran.edu.student.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.heran.edu.student.dao.StuInfoDao;
import org.heran.edu.student.domain.StuInfo;
import org.heran.edu.student.service.StuInfoService;
import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.util.data.ResultCode;
import org.heran.edu.student.util.dispose.UUIDUtil;
import org.heran.edu.student.vo.StuInfoInVO;
import org.heran.edu.student.vo.StudentRegisterInVO;
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
class StuInfoServiceImpl implements StuInfoService {

    @Autowired
    private StuInfoDao stuInfoDao;

    @Override
    public Result<Boolean> addStuInfo(StudentRegisterInVO studentRegisterInVO) {
        log.info("Enter addStuInfo studentRegisterInVO={}", studentRegisterInVO);
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "保存失败", false);
        StuInfo stuInfo = new StuInfo();
        BeanUtils.copyProperties(studentRegisterInVO, stuInfo);
        try {
            //生成uuid
            String uuid = UUIDUtil.creatUUID();
            stuInfo.setId(uuid);
            stuInfo.setCreatetime(new Date());
            //保存操作
            this.stuInfoDao.save(stuInfo);
            //给返回值赋值
            resBean.setContent(true);
            resBean.setMsg("保存成功");
            resBean.setCode(ResultCode.SUCCESS);
        } catch (Exception e) {
            log.error("Find Exception", e);
        }
        return resBean;
    }

    @Override
    public Result<Map<String, Object>> getStudentList(StuInfoInVO stuInfoInVO) {
        Result<Map<String, Object>> res = new Result<Map<String, Object>>(ResultCode.ERROR_DATA,"查询失败",null);
        log.info("Enter getStudentList stuInfoInVO={}",stuInfoInVO);
        List<StuInfo> stuInfos = new ArrayList<>();
        Map<String, Object> resultMap = new HashedMap();
        StuInfo stuInfo= new StuInfo();
        BeanUtils.copyProperties(stuInfoInVO, stuInfo);
        int totalSize=0;
        try {
            stuInfos = this.stuInfoDao.selectList(stuInfo, "pageStuInfo");
            totalSize = this.stuInfoDao.selectCount(stuInfo,"pageStuInfoCount");
            resultMap = new HashMap<String, Object>();
            resultMap.put("rows",stuInfos);
            resultMap.put("totalSize",totalSize);
        }catch (Exception e){
            log.error("Find Exception", e);
        }
        res.setCode(ResultCode.SUCCESS);
        res.setContent(resultMap);
        res.setMsg("查询成功");
        log.info("Get getStudentList size={}",stuInfos!=null?stuInfos.size() : "");
        return res;
    }
}
