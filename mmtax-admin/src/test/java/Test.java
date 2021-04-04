import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mmtax.MmtaxApplication;
import com.mmtax.business.domain.CustomerProtocol;
import com.mmtax.business.dto.BatchErrorResultDTO;
import com.mmtax.business.dto.InvoiceInfoDetailDTO;
import com.mmtax.business.dto.ManagerCompanyInfoQueryDTO;
import com.mmtax.business.dto.PaymentInfoDTO;
import com.mmtax.business.mapper.CustomerProtocolMapper;
import com.mmtax.business.mapper.MerchantInfoMapper;
import com.mmtax.business.service.IMerchantAccountService;
import com.mmtax.business.service.INotifySendAgainService;
import com.mmtax.business.service.IOnlineBankService;
import com.mmtax.business.service.IWorkOrderService;
import com.mmtax.common.utils.esign.comm.CacheKeyConstant;
import com.mmtax.common.utils.esign.domain.request.CommonRequest;
import com.mmtax.common.utils.esign.domain.signflow.ConfigInfo;
import com.mmtax.common.utils.esign.domain.signflow.SignFlowStart;
import com.mmtax.common.utils.esign.helper.SignHelper;
import com.mmtax.common.utils.esign.helper.TokenHelper;
import com.mmtax.common.utils.redis.RedisLoaderUtils;
import com.mmtax.common.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.common.Mapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MmtaxApplication.class)
@Slf4j
@Service
public class Test {

    @Autowired
    private IWorkOrderService workOrderService;

    @org.junit.Test
    public void TestOne(){
        List<PaymentInfoDTO> paymentInfoDTOS = new ArrayList<>();
        PaymentInfoDTO dto = new PaymentInfoDTO();
        dto.setUseBigRate(false);
        paymentInfoDTOS.add(dto);
        for (PaymentInfoDTO one:paymentInfoDTOS) {
            one.setUseBigRate(true);
        }
        log.info(JSON.toJSONString(paymentInfoDTOS));
    }

    @org.junit.Test
    public void TestOne1(){

        workOrderService.queryNameAndIdByApplyNum("20201203135503692082936");
    }

}
