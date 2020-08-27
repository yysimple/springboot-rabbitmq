package com.jxkj.mall.sdkill.seckill.controller;

import com.jxkj.mall.sdkill.result.ResultBody;
import com.jxkj.mall.sdkill.result.ResultBodyUtil;
import com.jxkj.mall.sdkill.seckill.service.SecKillService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 项目: skukill
 * <p>
 * 功能描述: 秒杀controller
 *
 * @author: WuChengXing
 * @create: 2020-08-27 12:49
 **/
@RestController
@RequestMapping("/seckill")
public class SecKillController {

    @Autowired
    private SecKillService secKillService;

    /**
     * 返回当前时间可以参与秒杀活动的商品
     *
     * @return
     */
    @GetMapping("/getCurrentSecKillSkus")
    public ResultBody getCurrentSecKillSkus() {
        return ResultBodyUtil.success(secKillService.getCurrentSecKillSkus());
    }

    /**
     * 拿到该商品的秒杀信息
     *
     * @param skuId
     * @return
     */
    @GetMapping("/getSeckillSkuInfo")
    public ResultBody getSeckillSkuInfo(@RequestParam("skuId") Long skuId) {
        return ResultBodyUtil.success(secKillService.getSeckillSkuInfo(skuId));
    }

    /**
     * 秒杀
     * @param killId
     * @param key
     * @param num
     * @param userId (这个应该是系统自动校验的，应该是登录状态才能进行秒杀)
     * @return
     */
    @PostMapping("/kill")
    public ResultBody kill(@RequestParam("killId") String killId,
                           @RequestParam("key") String key,
                           @RequestParam("num") Integer num,
                           @RequestParam("userId") Long userId) {
        return ResultBodyUtil.success(secKillService.kill(killId, key, num, userId));
    }
}
