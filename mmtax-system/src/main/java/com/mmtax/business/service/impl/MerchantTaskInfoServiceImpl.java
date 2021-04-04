package com.mmtax.business.service.impl;

import cn.hutool.core.date.BetweenFormater;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.Cooperation;
import com.mmtax.business.domain.MerchantTaskInfo;
import com.mmtax.business.domain.OnlinePaymentInfo;
import com.mmtax.business.dto.BatchTaskRecordDTO;
import com.mmtax.business.dto.MerchantTaskInfoQueryDTO;
import com.mmtax.business.dto.TaskCompletedDTO;
import com.mmtax.business.dto.TaskRecordDTO;
import com.mmtax.business.mapper.CooperationMapper;
import com.mmtax.business.mapper.MerchantInfoMapper;
import com.mmtax.business.mapper.MerchantTaskInfoMapper;
import com.mmtax.common.enums.DelStatusEnum;
import com.mmtax.common.enums.DeleteEnum;
import com.mmtax.common.utils.DateUtils;
import com.mmtax.common.utils.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IMerchantTaskInfoService;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

/**
 * 任务记录 服务层实现
 *
 * @author meimiao
 * @date 2020-10-13
 */
@Service
public class MerchantTaskInfoServiceImpl implements IMerchantTaskInfoService {
    @Autowired
    private MerchantTaskInfoMapper merchantTaskInfoMapper;
    @Autowired
    private CooperationMapper cooperationMapper;
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Override
    public List<TaskCompletedDTO> selectAllTaskInfo(MerchantTaskInfoQueryDTO queryDTO) {
        List<TaskCompletedDTO> taskCompletedDTOS = merchantTaskInfoMapper.selectAllTaskInfo(queryDTO);
        for (TaskCompletedDTO dto : taskCompletedDTOS) {
            String name = dto.getName();
            if(StringUtils.isNotEmpty(name)){
                int length = name.length();
                if(length==2){
                    name = name.substring(0, 1) + "*";
                }else {
                    name = name.substring(0, 1) + "*" + name.substring(name.length() - 1);
                }
                dto.setName(name);
            }
            String mobileNo = dto.getMobileNo();
            if(StringUtils.isNotEmpty(mobileNo)&&mobileNo.length()>7){
                mobileNo = mobileNo.substring(0,3)+"****"+mobileNo.substring(7);
                dto.setMobileNo(mobileNo);
            }
        }
        return taskCompletedDTOS;
    }
    @Override
    public List<TaskRecordDTO> selectTaskRecord(MerchantTaskInfoQueryDTO queryDTO) {
        List<TaskRecordDTO> taskRecordDTOS = merchantTaskInfoMapper.selectTaskRecord(queryDTO);
        //设置剩余时间
        for (TaskRecordDTO taskRecordDTO : taskRecordDTOS) {
            DateTime currentTime = DateUtil.parse(DateUtil.now(), DateUtils.YYYY_MM_DD_HH_MM_SS);
            DateTime endTime = DateUtil.parse(taskRecordDTO.getTaskRequireCompleteTime(), DateUtils.YYYY_MM_DD_HH_MM_SS);
            String remainTime ;
            if(endTime.isBeforeOrEquals(currentTime)){
                remainTime = "0";
            }else{
                remainTime = DateUtil.formatBetween(currentTime, endTime, BetweenFormater.Level.HOUR);
            }
            taskRecordDTO.setRemainTime(remainTime);
            //任务Id
            String taskId = taskRecordDTO.getTaskId();
            //商户Id
            String merchantId = taskRecordDTO.getMerchantId();
            //根据任务Id和商户Id查询任务简介
            Example example = new Example(MerchantTaskInfo.class);
            example.createCriteria().andEqualTo("merchantId",merchantId)
                            .andEqualTo("taskId",taskId);
            List<MerchantTaskInfo> merchantTaskInfos = merchantTaskInfoMapper.selectByExample(example);
            //任务简介
            if(StringUtils.isNotEmpty(merchantTaskInfos)){
                String taskInfo = merchantTaskInfos.get(0).getTaskInfo();
                taskRecordDTO.setTaskInfo(taskInfo);
            }
        }
        return taskRecordDTOS;
    }

    @Override
    public List<MerchantTaskInfo> selectMerchantTaskInfoByMerchantId(Integer merchantId) {
        //查询任务信息
        MerchantTaskInfo merchantTaskInfo = new MerchantTaskInfo();
        merchantTaskInfo.setMerchantId(merchantId);
        merchantTaskInfo.setDelStatus(0);
        List<MerchantTaskInfo> merchantTaskInfos = merchantTaskInfoMapper.select(merchantTaskInfo);
        return merchantTaskInfos;
    }

    @Override
    public List<MerchantTaskInfo> queryTaskInfo(Integer merchantId) {
        MerchantTaskInfo query = new MerchantTaskInfo();
        query.setMerchantId(merchantId);
        query.setDelStatus(0);
        return merchantTaskInfoMapper.select(query);
    }

    @Override
    public void updateTaskInfo() {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        MerchantTaskInfoMapper mapper = sqlSession.getMapper(MerchantTaskInfoMapper.class);
        Example example = new Example(Cooperation.class);
        example.createCriteria().andEqualTo("signingType",1).orEqualTo("signingType",3).orEqualTo("signingType",0);
        List<Cooperation> cooperations = cooperationMapper.selectByExample(example);
        for (int i = 0; i < cooperations.size(); i++) {
            MerchantTaskInfo merchantTaskInfo = new MerchantTaskInfo();
            merchantTaskInfo.setMerchantId(cooperations.get(i).getMerchantId());
            merchantTaskInfo.setDelStatus(DelStatusEnum.NORMAL.code);
            List<MerchantTaskInfo> list = merchantTaskInfoMapper.select(merchantTaskInfo);
            if (list.size() == 0) {
                merchantTaskInfo.setTaskId(16);
                merchantTaskInfo.setTaskName("市场推广");
                merchantTaskInfo.setTaskInfo("通过线上推广和线下推广等方式提高甲方（包括但不限于甲方App网站等）知名度和点击量，其中线上推广方式包括但不限于在各类社交平台或线上社群发帖、转帖、发优惠券等，线下推广方式包括但不限于贴海报、发传单、发赠品/试用品等");
                mapper.insertSelective(merchantTaskInfo);
            }
            //五十次提交一次
            if ( (i+1) % 50 == 0) {
                sqlSession.flushStatements();
            }
        }
        sqlSession.flushStatements();
    }
}
