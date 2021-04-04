package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.OrderStatusInfo;
import com.mmtax.business.domain.PerMerApplySchedule;
import com.mmtax.business.domain.PersonalMerchant;
import com.mmtax.business.domain.WorkOrder;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.OrderStatusInfoMapper;
import com.mmtax.business.mapper.PerMerApplyScheduleMapper;
import com.mmtax.business.mapper.PersonalMerchantMapper;
import com.mmtax.business.mapper.WorkOrderMapper;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.enums.PerMerStatusEnum;
import com.mmtax.common.enums.WorkOrderStatusEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.bean.BeanUtils;
import com.mmtax.common.utils.redis.RedisUtil;
import org.apache.commons.io.IOUtils;
import org.apache.tools.ant.taskdefs.condition.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IWorkOrderService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 工单 服务层实现
 * 
 * @author meimiao
 * @date 2020-12-10
 */
@Service
public class WorkOrderServiceImpl implements IWorkOrderService
{

    private static  final  int CHAR_LIMIT_FORTY = 40;
    private static  final  int CHAR_LIMIT_TWO_HUNDRED = 200;

    @Autowired
    private WorkOrderMapper workOrderMapper;

    @Autowired
    private PersonalMerchantMapper personalMerchantMapper;

    @Autowired
    private OrderStatusInfoMapper orderStatusInfoMapper;

    @Autowired
    private PerMerApplyScheduleMapper perMerApplyScheduleMapper;

    /**
     * 发布工单
     * @param publishWokeOrderDTO
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void publishWorkOrder(PublishWokeOrderDTO publishWokeOrderDTO) {

        //入参校验
        if(StringUtils.isEmpty(publishWokeOrderDTO.getApplyNumber())){
            throw new BusinessException("请填写申请编号");
        }
        if(StringUtils.isEmpty(publishWokeOrderDTO.getTitle())){
            throw new BusinessException("请填写标题");
        }
        if(StringUtils.isEmpty(publishWokeOrderDTO.getContent())){
            throw new BusinessException("请填写内容");
        }
        if(StringUtils.isEmpty(publishWokeOrderDTO.getName())){
            throw new BusinessException("姓名为空");
        }
        if(StringUtils.isEmpty(publishWokeOrderDTO.getCertificateNo())){
            throw new BusinessException("身份证号为空");
        }
        if(publishWokeOrderDTO.getTitle().length() > CHAR_LIMIT_FORTY){
            throw new BusinessException("标题最多四十字符！");
        }
        if(publishWokeOrderDTO.getContent().length() > CHAR_LIMIT_TWO_HUNDRED){
            throw new BusinessException("内容最多200个字符！");
        }
        //根据 入网申请编号查询PersonalMerchant信息
        PersonalMerchant personalMerchant = new PersonalMerchant();
        personalMerchant.setApplyNumber(publishWokeOrderDTO.getApplyNumber());
        personalMerchant = personalMerchantMapper.selectOne(personalMerchant);
        if(personalMerchant == null){
            throw new BusinessException("没有这个申请编号");
        }

        if(!publishWokeOrderDTO.getCertificateNo().equals(personalMerchant.getIdCardNumber())){
            throw new BusinessException("入网申请编号与身份证号不符");
        }
        if(!publishWokeOrderDTO.getName().equals(personalMerchant.getApplyName())){
            throw new BusinessException("入网申请编号与姓名不符");
        }


        //封装插入条件
        WorkOrder workOrder = new WorkOrder();
        BeanUtils.copyProperties(publishWokeOrderDTO,workOrder);
        workOrder.setWorkOrderSerialNumber(ChanPayUtil.generateOutTradeNo());
        workOrder.setStatus(WorkOrderStatusEnum.PUBILSHED.getCode());
        workOrder.setTaxSourceId(personalMerchant.getTaxSounrceCompanyId());
        workOrder.setPublishTime(DateUtil.date());
        workOrder.setCreateTime(DateUtil.date());
        workOrder.setUpdateTime(DateUtil.date());

        workOrderMapper.insertSelective(workOrder);

        //改变订单的状态值
        personalMerchant = new PersonalMerchant();
        personalMerchant.setApplyNumber(publishWokeOrderDTO.getApplyNumber());
        personalMerchant = personalMerchantMapper.selectOne(personalMerchant);

        Example example = new Example(OrderStatusInfo.class);
        example.createCriteria().andEqualTo("applyId",personalMerchant.getId());
        OrderStatusInfo orderStatusInfo = new OrderStatusInfo();
        orderStatusInfo.setOrderStatus(PerMerStatusEnum.WORKORDER.getCode());
        orderStatusInfoMapper.updateByExampleSelective(orderStatusInfo,example);


    }

    /**
     * 根据申请编号 获取 姓名和身份证号
     * @param applyNumber
     */
    @Override
    public PersonalMerchant queryNameAndIdByApplyNum(String applyNumber) {
        if(StringUtils.isEmpty(applyNumber)){
            throw new BusinessException("请填写申请编号");
        }

        PersonalMerchant personalMerchant = new PersonalMerchant();
        personalMerchant.setApplyNumber(applyNumber);
        personalMerchant = personalMerchantMapper.selectOne(personalMerchant);

        return personalMerchant;
    }

    /**
     * 关闭工单
     * @param workOrderSerialNumber
     */
    @Override
    public void closeWorkOrder(String workOrderSerialNumber) {
        if(StringUtils.isEmpty(workOrderSerialNumber)){
            throw new BusinessException("请填写工单编号");
        }

        updateWorkOrderStatusByWorkOrderSerialNumber(workOrderSerialNumber, WorkOrderStatusEnum.CLOSE.getCode());
    }

    @Override
    public void disPoseWorkOrder(String workOrderSerialNumber) {
        if(StringUtils.isEmpty(workOrderSerialNumber)){
            throw new BusinessException("请填写工单编号");
        }

        updateWorkOrderStatusByWorkOrderSerialNumber(workOrderSerialNumber, WorkOrderStatusEnum.DISPOSE.getCode());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateWorkOrderStatusByWorkOrderSerialNumber(String workOrderSerialNumber, Integer status) {
        Example example = new Example(WorkOrder.class);
        example.createCriteria().andEqualTo("workOrderSerialNumber",workOrderSerialNumber);

        WorkOrder workOrder = new WorkOrder();
        workOrder.setStatus(status);
        workOrder.setUpdateTime(DateUtil.date());
        workOrderMapper.updateByExampleSelective(workOrder,example);
        //如果入网申请编号下 还有工单处于发布和反馈状态下 不更新订单的状态
        //查询工单的入网申请编号
        WorkOrder query = new WorkOrder();
        query.setWorkOrderSerialNumber(workOrderSerialNumber);
        WorkOrder result =  workOrderMapper.selectOne(query);
        //填充 要查询的状态
        List<Integer> workOrderStatus = new ArrayList<>();
        workOrderStatus.add(WorkOrderStatusEnum.PUBILSHED.getCode());
        workOrderStatus.add(WorkOrderStatusEnum.FEEDBACK.getCode());

        example = new Example(WorkOrder.class);
        example.createCriteria().andEqualTo("applyNumber",result.getApplyNumber()).andIn("status",workOrderStatus);
        List<WorkOrder> workOrderList = workOrderMapper.selectByExample(example);

        //快速返回
        if(workOrderList != null && workOrderList.size() > 0){
            return;
        }

        //改变工单的状态值
        example = new Example(WorkOrder.class);
        example.createCriteria().andEqualTo("workOrderSerialNumber",workOrderSerialNumber);
        workOrder = workOrderMapper.selectOneByExample(example);

        PersonalMerchant personalMerchant = new PersonalMerchant();
        personalMerchant.setApplyNumber(workOrder.getApplyNumber());
        personalMerchant = personalMerchantMapper.selectOne(personalMerchant);
        
        PerMerApplySchedule perMerApplySchedule = new PerMerApplySchedule();
        perMerApplySchedule.setApplyNumber(workOrder.getApplyNumber());
        perMerApplySchedule.setType(2);
        int count = perMerApplyScheduleMapper.selectCount(perMerApplySchedule);

        example = new Example(OrderStatusInfo.class);
        example.createCriteria().andEqualTo("applyId",personalMerchant.getId());
        OrderStatusInfo orderStatusInfo = new OrderStatusInfo();
        //如果流程在入网申请 就将状态设置为办理中，在营业执照就设置成已办理
        if(count > 0){
            orderStatusInfo.setOrderStatus(PerMerStatusEnum.SUCCESS.getCode());
        }else{
            orderStatusInfo.setOrderStatus(PerMerStatusEnum.PROCESS.getCode());
        }

        orderStatusInfoMapper.updateByExampleSelective(orderStatusInfo,example);
    }

    /**
     * 根据条件查询工单列表
     * @param listWorkOrderDTO
     * @return
     */
    @Override
    public List<ListWorOrderResultDTO> listWorkOrder(ListWorkOrderDTO listWorkOrderDTO) {
        return workOrderMapper.listWorkOrder(listWorkOrderDTO);
    }

    @Override
    public WorkOrder queryWorkOrderDetail(String workOrderSerialNumber) {
        if(StringUtils.isEmpty(workOrderSerialNumber)){
            throw new BusinessException("工单编号为空？");
        }
        WorkOrder workOrder = new WorkOrder();
        workOrder.setWorkOrderSerialNumber(workOrderSerialNumber);
        workOrder =  workOrderMapper.selectOne(workOrder);
        if(workOrder == null){
            throw new BusinessException("没有这个工单信息");
        }
        return workOrder;
    }

    @Override
    public List<WorkOrderRecordResultDTO>  workOrderRecord(Integer wechatInfoId) {
        PersonalMerchant personalMerchant  = new PersonalMerchant();
        personalMerchant.setWechatInfoId(wechatInfoId);
        List<PersonalMerchant> personalMerchants = personalMerchantMapper.select(personalMerchant);
        List<String> applyNumbers = personalMerchants.stream().map(p -> {
            return p.getApplyNumber();
        }).collect(Collectors.toList());
        // 防止空指针 真实的业务数据中并没有0
        applyNumbers.add("0");
        List<WorkOrderRecordResultDTO> workOrderRecordResultDTOS = workOrderMapper.listWorkOrderByApplyNumbers(applyNumbers);
        return workOrderRecordResultDTOS;
    }

    @Override
    public void submitData(WorkOrderFeedBackDTO workOrderFeedBackDTO) {

        if(workOrderFeedBackDTO != null && StringUtils.isEmpty(workOrderFeedBackDTO.getTxt())
                && StringUtils.isEmpty(workOrderFeedBackDTO.getPdfAddress())
                && StringUtils.isEmpty(workOrderFeedBackDTO.getImgAddress())
                && StringUtils.isEmpty(workOrderFeedBackDTO.getVideoAddress())){
            throw new BusinessException("请提交资料");
        }
        if(workOrderFeedBackDTO.getTxt().length() > CHAR_LIMIT_TWO_HUNDRED){
            throw new BusinessException("文本最多输入200个字符！");
        }
        boolean exists = RedisUtil.exists(workOrderFeedBackDTO.getWorkOrderSerialNumber());
        if(exists){
            throw new BusinessException("请勿重复提交");
        }else{
            RedisUtil.put(workOrderFeedBackDTO.getWorkOrderSerialNumber(),workOrderFeedBackDTO.getWorkOrderSerialNumber(),RedisUtil.ONE, TimeUnit.DAYS);
        }



        Example example = new Example(WorkOrder.class);
        example.createCriteria().andEqualTo("workOrderSerialNumber",workOrderFeedBackDTO.getWorkOrderSerialNumber());
        WorkOrder workOrder = new WorkOrder();
        workOrder.setTxt(workOrderFeedBackDTO.getTxt());
        workOrder.setPdfAddress(workOrderFeedBackDTO.getPdfAddress());
        workOrder.setImgAddress(workOrderFeedBackDTO.getImgAddress());
        workOrder.setVideoAddress(workOrderFeedBackDTO.getVideoAddress());
        workOrder.setStatus(WorkOrderStatusEnum.FEEDBACK.getCode());
        workOrder.setUpdateTime(DateUtil.date());
        workOrder.setFeedbackTime(DateUtil.date());

        try {
            workOrderMapper.updateByExampleSelective(workOrder,example);
        }catch (Exception e){
            RedisUtil.remove(workOrderFeedBackDTO.getWorkOrderSerialNumber());
            throw new BusinessException("文本请输入字符！");
        }



    }

    @Override
    public void downloadWorOrder(String urlAddress, HttpServletResponse response) {
       String extention = urlAddress.substring(urlAddress.lastIndexOf("."));

        //new一个URL对象
        URL url = null;
        InputStream inStream = null;
        try {
            url = new URL(urlAddress);
            //打开链接
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            inStream = conn.getInputStream();

            response.getOutputStream();
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition","attachment;filename=" + System.currentTimeMillis() + extention);
            IOUtils.copy(inStream,response.getOutputStream());

        } catch (Exception e) {
            throw  new BusinessException(e.getMessage());
        }finally {
            try {
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
