package com.jxkj.mall.sdkill.seckill.controller;

import com.jxkj.mall.sdkill.result.ResultBody;
import com.jxkj.mall.sdkill.seckill.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * @return
     */
    @GetMapping("/getCurrentSecKillSkus")
    public ResultBody getCurrentSecKillSkus(){
        return null;
    }
}
