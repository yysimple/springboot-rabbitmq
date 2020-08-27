package com.jxkj.mall.sdkill.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wcx
 * @since 2020-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SmsCoupon对象", description="")
public class SmsCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Boolean couponType;

    private String couponImg;

    private String couponName;

    private Integer num;

    private BigDecimal amount;

    @ApiModelProperty(value = "ÿ")
    private Integer perLimit;

    @ApiModelProperty(value = "ʹ")
    private BigDecimal minPoint;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ApiModelProperty(value = "ʹ")
    private Boolean useType;

    private String note;

    private Integer publishCount;

    private Integer useCount;

    private Integer receiveCount;

    private LocalDateTime enableStartTime;

    private LocalDateTime enableEndTime;

    private String code;

    private Boolean memberLevel;

    private Boolean publish;


}
