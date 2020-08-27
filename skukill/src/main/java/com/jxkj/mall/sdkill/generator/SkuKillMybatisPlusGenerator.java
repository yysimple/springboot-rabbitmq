package com.jxkj.mall.sdkill.generator;


/**
 * 功能描述：
 *
 * @author wcx
 * @version 1.0
 */
public class SkuKillMybatisPlusGenerator {
    public static void main(String[] args) {
        GeneratorEntity generatorEntity = new GeneratorEntity();
        generatorEntity.setAuthor("wcx");
        generatorEntity.setParentPackage("com.jxkj.mall.sdkill");
        generatorEntity.setUrl("jdbc:mysql://182.92.89.118:3306/mall_sms?serverTimezone=UTC&useUnicode=true&useSSL=false&characterEncoding=utf8");
        generatorEntity.setUsername("root");
        generatorEntity.setPassword("123456");
        generatorEntity.setProjectPath("F:\\springboot2\\skukill");
        String[] tables = {
                "sms_seckill_session"
        };
        /*"sms_order",
                "sms_goods",
                "sms_coupon",
                "sms_coupon_history",
                "sms_coupon_spu_category_relation",
                "sms_coupon_spu_relation",
                "sms_home_adv",
                "sms_home_subject",
                "sms_home_subject_spu",
                "sms_member_price",
                "sms_seckill_session",
                "sms_seckill_promotion",
                "sms_seckill_sku_notice",
                "sms_seckill_sku_relation",
                "sms_sku_full_reduction",
                "sms_sku_ladder",
                "sms_spu_bounds"*/
        generatorEntity.setTableNames(tables);
        generatorEntity.setOpenSwagger(true);
        MybatisPlusGenerator.generator(generatorEntity);

    }
}
