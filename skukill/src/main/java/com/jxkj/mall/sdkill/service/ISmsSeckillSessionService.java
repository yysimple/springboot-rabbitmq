package com.jxkj.mall.sdkill.service;

import com.jxkj.mall.sdkill.entity.SmsSeckillSession;
import com.baomidou.mybatisplus.extension.service.IService;import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wcx
 * @since 2020-08-26
 */
public interface ISmsSeckillSessionService extends IService<SmsSeckillSession> {

    /**
     * 功能描述: 上架最近三天的秒杀活动
     * @author WuChengxing
     * @date 2020/8/26
     * @param
     * @return java.util.List<com.jxkj.mall.sdkill.entity.SmsSeckillSession>
     */
    List<SmsSeckillSession> getThreeDaySession();
}
