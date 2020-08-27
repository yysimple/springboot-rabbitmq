package com.jxkj.mall.sdkill.mapper;

import com.jxkj.mall.sdkill.entity.SmsSeckillSession;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wcx
 * @since 2020-08-26
 */
@Repository
public interface SmsSeckillSessionMapper extends BaseMapper<SmsSeckillSession> {

    /**
     * 功能描述: 过去三天内需要上架的商品
     * @author WuChengxing
     * @date 2020/8/26
     * @param start
     * @param end
     * @return java.util.List<com.jxkj.mall.sdkill.entity.SmsSeckillSession>
     */
    List<SmsSeckillSession> getThreeDaySession(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
