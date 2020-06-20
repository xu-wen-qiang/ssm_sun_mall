package com.xwq.service.impl;

import com.xwq.entity.PayInfo;
import com.xwq.dao.PayInfoDao;
import com.xwq.service.PayInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (PayInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
@Service("payInfoService")
public class PayInfoServiceImpl implements PayInfoService {

    public PayInfo queryById(Integer id) {
        return null;
    }

    public List<PayInfo> queryAllByLimit(int offset, int limit) {
        return null;
    }

    public PayInfo insert(PayInfo payInfo) {
        return null;
    }

    public PayInfo update(PayInfo payInfo) {
        return null;
    }

    public boolean deleteById(Integer id) {
        return false;
    }
}