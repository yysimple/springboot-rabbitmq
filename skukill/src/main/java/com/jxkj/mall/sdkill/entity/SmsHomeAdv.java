package com.jxkj.mall.sdkill.entity;

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
@ApiModel(value="SmsHomeAdv对象", description="")
public class SmsHomeAdv implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    @ApiModelProperty(value = "ͼƬ")
    private String pic;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ApiModelProperty(value = "״̬")
    private Boolean status;

    private Integer clickCount;

    private String url;

    private String note;

    private Integer sort;

    private Long publisherId;

    private Long authId;


}
