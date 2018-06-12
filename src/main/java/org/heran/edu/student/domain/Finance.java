package org.heran.edu.student.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.heran.edu.student.util.mybatis.domain.PageEntity;

@Data
@NoArgsConstructor
public class Finance extends PageEntity implements Serializable {

    private static final long serialVersionUID = -1L;
    private String id;  //主键
    private String name;    //姓名
    private String idCard;  //身份证号码
    private String oldAcademy;  //原院校
    private String nowAcademy;  //现院校
    private String major;   //专业
    private String gradation;   //层次
    private String schoolAllCost;   //高校总学费
    private String schoolOne;   //高校第一次付款
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date schoolOneDate; //高校第一次缴费日期
    private String schoolOneUrl;    //高校第一次缴费上传凭证路径
    private String schoolTwo;   //高校第二次付款
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date schoolTwoDate; //高校第二次缴费日期
    private String schoolTwoUrl;    //高校第二次缴费上传凭证
    private String schoolThree; //高校第三次付款
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date schoolThreeDate;   //高校第三次缴费日期
    private String schoolThreeUrl;  //高校第三次缴费上传凭证
    private String teacherAllCost;  //招生老师总费用
    private String teacherOne;  //招生老师付经费一
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date teacherOneDate;    //招生老师第一次缴费日期
    private String teacherOneUrl;   //招生老师第一次缴费上传凭证
    private String teacherTwo;  //招生老师付经费二
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date teacherTwoDate;    //招生老师第二次缴费日期
    private String teacherTwoUrl;   //招生老师第二次上传凭证
    private String teacherThree;    //招生老师付经费三
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date teacherThreeDate;  //招生老师第三次缴费日期
    private String teacherThreeUrl; //招生老师第三次上传凭证
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;    //创建时间
    private String createUser;  //创建人
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;    //修改时间
    private String updateUser;  //修改人

}
