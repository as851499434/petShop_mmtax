package com.mmtax.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.CertificateType;
import com.mmtax.business.domain.UserList;
import com.mmtax.business.dto.ManagerUserDTO;
import com.mmtax.business.dto.MerchantAddUserDTO;
import com.mmtax.business.dto.MerchantListUserDTO;
import com.mmtax.business.mapper.CertificateTypeMapper;
import com.mmtax.business.mapper.UserListMapper;
import com.mmtax.business.service.IRosterService;
import com.mmtax.common.annotation.DataScope;
import com.mmtax.common.enums.AuditStatusEnum;
import com.mmtax.common.enums.UserTypeEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import com.mmtax.system.domain.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/7 10:17
 */
@Service
public class RosterServiceImpl extends CommonServiceImpl implements IRosterService {


    @Resource
    private UserListMapper userListMapper;

    @Resource
    private CertificateTypeMapper certificateTypeMapper;


    @Override
    public void add(MerchantAddUserDTO merchantAddUserDTO) {
        UserList userList = new UserList();
        userList.setBankCardNo(merchantAddUserDTO.getBankCardNo());
        userList.setIdCardNo(merchantAddUserDTO.getIdCardNo());
        userList.setMerchantId(merchantAddUserDTO.getMerchantId());
        userList.setCertificateTypeId(merchantAddUserDTO.getCertificateTypeId());
        Integer count = userListMapper.selectCount(userList);
        if (count > 0) {
            throw new BusinessException("该证件号和银行卡号已存在");
        }
        BeanUtil.copyProperties(merchantAddUserDTO, userList);
        userList.setType(UserTypeEnum.WHITE.name());
        userList.setStatus(AuditStatusEnum.WAITING_AUDIT.code);
        userList.setCreateTime(DateUtil.date());
        userList.setUpdateTime(DateUtil.date());
        userListMapper.insertSelective(userList);

    }

    @Override
    public List<CertificateType> getListCertificateType() {
        return certificateTypeMapper.selectAll();
    }

    @Override
    public Page getPageUser(Integer merchantId, Integer pageSize, Integer currentPage, String name) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        List<MerchantListUserDTO> list = userListMapper.getListUser(pageSize, queryPage.getStartIndex(), merchantId, name);
        int count = userListMapper.getCountUser(merchantId, name);
        return new Page(count, list, currentPage, pageSize);
    }

    @Override
    @DataScope(tableAlias = "t3")
    public List<ManagerUserDTO> getListUser(ManagerUserDTO managerUserDTO) {
        return userListMapper.getManagerList(managerUserDTO);
    }

    @Override
    public void checkUserStatus(ManagerUserDTO managerUserDTO) {
        UserList userList = new UserList();
        userList.setId(managerUserDTO.getId());
        userList.setStatus(managerUserDTO.getStatus());
        userListMapper.updateByPrimaryKeySelective(userList);
    }
}
