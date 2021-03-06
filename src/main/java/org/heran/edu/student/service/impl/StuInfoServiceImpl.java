package org.heran.edu.student.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.heran.edu.student.dao.StuInfoDao;
import org.heran.edu.student.domain.ExamManager;
import org.heran.edu.student.domain.StuInfo;
import org.heran.edu.student.service.StuInfoService;
import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.util.data.ResultCode;
import org.heran.edu.student.util.dispose.UUIDUtil;
import org.heran.edu.student.vo.StuInfoInVO;
import org.heran.edu.student.vo.StudentRegisterInVO;
import org.heran.edu.student.vo.StudentUpdateInVO;
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
class StuInfoServiceImpl implements StuInfoService {

    @Autowired
    private StuInfoDao stuInfoDao;

    @Override
    public Result<Boolean> addStuInfo(StudentRegisterInVO studentRegisterInVO) {
        log.debug("Enter addStuInfo studentRegisterInVO={}", studentRegisterInVO);
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "保存失败", false);
        StuInfo stuInfo = new StuInfo();
        BeanUtils.copyProperties(studentRegisterInVO, stuInfo);
        try {
            /*//检查是否有重复学生信息
            List<StuInfo> stuInfos = stuInfoDao.selectList(stuInfo, "getStuInObject");
            if(stuInfos.size()>0){
                resBean.setContent(false);
                resBean.setMsg("学生信息重复");
                resBean.setCode(ResultCode.ERROR_PARAMETER);
            }else{*/
                //生成uuid
                String uuid = UUIDUtil.creatUUID();
                stuInfo.setId(uuid);
                stuInfo.setCreatetime(new Date());
                stuInfo.setStatus("2");
                stuInfo.setStuStatus("1");
                //保存操作
                this.stuInfoDao.save(stuInfo);
                //给返回值赋值
                resBean.setContent(true);
                resBean.setMsg("保存成功");
                resBean.setCode(ResultCode.SUCCESS);
//            }
        } catch (Exception e) {
            log.error("Find Exception", e);
        }
        return resBean;
    }

    @Override
    public Result<Map<String, Object>> getStudentList(StuInfoInVO stuInfoInVO) {
        Result<Map<String, Object>> res = new Result<Map<String, Object>>(ResultCode.ERROR_DATA,"查询失败",null);
        log.debug("Enter getStudentList stuInfoInVO={}",stuInfoInVO);
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
        log.debug("Get getStudentList size={}",stuInfos!=null?stuInfos.size() : "");
        return res;
    }

    @Override
    public Result<Boolean> updateStu(StudentUpdateInVO studentUpdateInVO) {
        log.debug("Enter updateStu studentUpdateInVO={}", studentUpdateInVO);
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "修改失败", false);
        StuInfo stuInfo = new StuInfo();
        BeanUtils.copyProperties(studentUpdateInVO, stuInfo);
        try {
            stuInfo.setUpdatetime(new Date());
            //保存操作
            this.stuInfoDao.update(stuInfo);
            //给返回值赋值
            resBean.setContent(true);
            resBean.setMsg("修改成功");
            resBean.setCode(ResultCode.SUCCESS);
        } catch (Exception e) {
            log.error("Find Exception", e);
        }
        return resBean;
    }

    @Override
    public Result<Boolean> updateBatchStatus(String ids) {
        log.debug("Enter updateBatchStatus ids={}", ids);
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "修改失败", false);
        String[] split = ids.split(",");
        try {
            for(String id:split){
                StuInfo stuInfo = new StuInfo();
                stuInfo.setId(id);
                stuInfo.setStatus("1");
                stuInfo.setStuStatus("2");
                stuInfo.setUpdatetime(new Date());
                this.stuInfoDao.update(stuInfo);
            }
        } catch (Exception e) {
            log.error("Find Exception", e);
        }
        //给返回值赋值
        resBean.setContent(true);
        resBean.setMsg("修改成功");
        resBean.setCode(ResultCode.SUCCESS);
        return resBean;
    }

    @Override
    public Result<Map<String, Object>> detail(String id) {
        Result<Map<String, Object>> res = new Result<Map<String, Object>>(ResultCode.ERROR_DATA,"查询失败",null);
        Map<String, Object> resultMap = new HashedMap();
        StuInfo stuInfo=new StuInfo();
        stuInfo.setId(id);
        try {
            StuInfo stuInfo1 = this.stuInfoDao.selectOne(stuInfo);
            StudentUpdateInVO studentUpdateInVO=new StudentUpdateInVO();
            BeanUtils.copyProperties(stuInfo1,studentUpdateInVO);
            resultMap = new HashMap<String, Object>();
            resultMap.put("rows",studentUpdateInVO);
        }catch (Exception e){
            log.error("Find Exception", e);
        }
        res.setCode(ResultCode.SUCCESS);
        res.setContent(resultMap);
        res.setMsg("查询成功");
        return res;
    }

    @Override
    public Boolean del(String[] ids) {
        log.debug("Enter del knowid={}",ids);
        boolean res = false;
        try {
            for(int i=0;i<ids.length;i++){
                StuInfo stuInfo = new StuInfo();
                stuInfo.setId(ids[i]);
                this.stuInfoDao.delete(stuInfo);
            }
            res=true;
        }catch (Exception e){
            log.error("Find Exception", e);
        }
        log.debug("Leave del res={}",res);
        return res;
    }
}
