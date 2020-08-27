package com.jxkj.mall.sdkill.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 功能描述：返回失败信息封装
 *
 * @author 吴呈兴
 * @version 1.0.0.RELEASE
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultTypeEnum {

    /**
     *
     */
    ALREADY_EXIST(101,"名称已存在"),
    CAN_NOT_DELETE(103,"资源被引用,不能删除"),

    FILE_TOO_LARGE(501, "文件过大, 请从新上传（小于100MB）"),

    PARAM_NOT_EMPTY(601, "参数不能为空,请填写"),
    PARAM_EXCEPTION(602, "参数传入异常，请输入正确的参数"),

    USER_DISABLED(701, "用户被禁用"),
    USER_NOT_EXIST(702, "用户不存在"),
    USER_ALREADY_EXIST(703, "用户已经存在，请重新输入"),
    USERNAME_PASSWORD_FAIL(704, "请检查用户名和密码是否正确"),
    OLD_PASSWORD_ERROR(705, "旧密码不正确，请重新输入"),
    PASSWORD_NOT_TRUE(706, "密码不正确，请确认密码"),
    USER_NOT_USE(707, "账号正在审批，请耐心等待"),
    RECYCLER_ALREADY_LEAVE(708, "该回员已离职"),
    REGISTER_NOT_PASS(709, "您的注册信息被驳回，请重新注册"),
    USERNAME_NOT_EMPTY(710, "用户名不能为空"),
    PASSWORD_NOT_EMPTY(711, "密码不能为空"),

    PHONE_ALREADY_REGISTER(801, "手机号已注册，请选择其他手机号或直接登录"),
    PHONE_NOT_REGISTER(802, "该手机号用户不存在，请检查手机号和密码"),
    PHONE_INFO_EXCEPTION(803, "手机信息异常，请仔细核对"),
    PHONE_VERIFY_FAIL(804, "手机校验失败，请检查验证码"),
    CODE_NOT_EXPIRATION(805, "发送频繁，请稍后再次发送"),
    CODE_FAILURE(806, "验证码已失效，请重试发送验证码"),
    CODE_NOT_TRUE(807, "验证码不正确，请重新输入验证码")
    ;
    private Integer code;

    private String msg;


}
