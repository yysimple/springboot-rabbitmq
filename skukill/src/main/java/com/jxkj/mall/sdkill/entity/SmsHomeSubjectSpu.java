package com.jxkj.mall.sdkill.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * ר
 * </p>
 *
 * @author wcx
 * @since 2020-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sms_home_subject_spu")
@ApiModel(value="SmsHomeSubjectSpu对象", description="ר")
public class SmsHomeSubjectSpu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "ר")
    private String name;

    @ApiModelProperty(value = "ר")
    private Long subjectId;

    @ApiModelProperty(value = "spu_id")
    private Long spuId;

    private Integer sort;


}
