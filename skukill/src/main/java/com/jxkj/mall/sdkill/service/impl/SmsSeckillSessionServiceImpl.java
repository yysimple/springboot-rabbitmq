package com.jxkj.mall.sdkill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.jxkj.mall.sdkill.entity.SmsSeckillSession;
import com.jxkj.mall.sdkill.entity.SmsSeckillSkuRelation;
import com.jxkj.mall.sdkill.mapper.SmsSeckillSessionMapper;
import com.jxkj.mall.sdkill.service.ISmsSeckillSessionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxkj.mall.sdkill.service.ISmsSeckillSkuRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wcx
 * @since 2020-08-26
 */
@Service
public class SmsSeckillSessionServiceImpl extends ServiceImpl<SmsSeckillSessionMapper, SmsSeckillSession> implements ISmsSeckillSessionService {

    @Autowired
    private SmsSeckillSessionMapper smsSeckillSessionMapper;

    @Autowired
    private ISmsSeckillSkuRelationService skuRelationService;

    /**
     * 上架最近三天的秒杀活动
     * @return
     */
    @Override
    public List<SmsSeckillSession> getThreeDaySession() {
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusDays(2);
        // 查询出最近三天需要上架的商品
        List<SmsSeckillSession> threeDaySession = smsSeckillSessionMapper.getThreeDaySession(start, end);
        // 将对应产品信息放入每个时段中作为级联
        if (null != threeDaySession && threeDaySession.size() > 0) {
            List<SmsSeckillSession> collect = threeDaySession.stream().map(smsSeckillSession -> {
                List<SmsSeckillSkuRelation> smsSeckillSkuRelations = skuRelationService.list(new QueryWrapper<SmsSeckillSkuRelation>()
                        .eq("promotion_session_id", smsSeckillSession.getId()));
                smsSeckillSession.setSmsSeckillSkuRelations(smsSeckillSkuRelations);
                return smsSeckillSession;
            }).collect(Collectors.toList());
            return collect;
        }
        return null;
    }
}
