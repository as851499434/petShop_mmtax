package com.mmtax.business.service;

import com.mmtax.business.domain.PersonalMerchant;
import com.mmtax.business.domain.WorkOrder;
import com.mmtax.business.dto.*;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 工单 服务层
 * 
 * @author meimiao
 * @date 2020-12-10
 */
@Service
public interface IWorkOrderService
{
    /**
     * 发布工单
     * @param publishWokeOrderDTO
     */
    void publishWorkOrder(PublishWokeOrderDTO publishWokeOrderDTO);

    /**
     * 根据申请编号获取姓名和身份证号
     * @param applyNumber
     */
    PersonalMerchant queryNameAndIdByApplyNum(String applyNumber);

    /**
     * 关闭工单
     * @param workOrderSerialNumber
     */
    void closeWorkOrder(String workOrderSerialNumber);

    /**
     *  已处理工单
     * @param workOrderSerialNumber
     */
    void disPoseWorkOrder(String workOrderSerialNumber);

    /**
     * 根据条件查询 工单列表
     * @param listWorkOrderDTO
     * @return
     */
    List<ListWorOrderResultDTO> listWorkOrder(ListWorkOrderDTO listWorkOrderDTO);

    /**
     * 查询工单详情
     * @param workOrderSerialNumber
     * @return
     */
    WorkOrder queryWorkOrderDetail(String workOrderSerialNumber );

    /**
     * 公众号 工单列表
     * @param wechatInfoId
     * @return
     */
    List<WorkOrderRecordResultDTO>  workOrderRecord(Integer wechatInfoId);

    void submitData(WorkOrderFeedBackDTO workOrderFeedBackDTO);

    void downloadWorOrder(String url, HttpServletResponse response);
}
