package com.leave996.leave996.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.yaml.snakeyaml.util.ArrayUtils;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 待验证对象实体类
 * 用户信息类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    // 登录场景
    public interface LoginGroup {}

    // 注册场景
    public interface RegisterGroup {}

    // 组排序场景
    @GroupSequence({
            RegisterGroup.class,
            LoginGroup.class,
            Default.class
    })
    public interface Group {}

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空"
//            ,groups = LoginGroup.class
            )
    private String username;

    /**d
     * 用户Id
     * @NotEmpty 不会自动去掉前后空格
     */
    @NotEmpty(message = "用户Id不能为空",
            groups = LoginGroup.class)
    private String userId;

    /**
     * 用户密码
     * @NotBlank 会自动去除空格
     */
    @NotBlank(message = "密码不能为空",
            groups = LoginGroup.class)
    @Length(min = 6, max = 20, message = "密码不能少于6位，不能多于20位")
    private String password;

    /**
     * 邮箱
     */
    @Email(message = "邮箱必须为有效邮箱",
            groups = RegisterGroup.class
    )
    private String email;

    /**
     * 电话
     */
    @Phone(message = "手机号不是170后头随便")
    private String phone;

    /**
     * 年龄
     */
    @Min(value = 18, message = "年龄不能小于18岁")
    @Max(value = 60, message = "年龄不能大于60岁")
    private Integer age;

    /**
     * 生日
     */
    @Past(message = "日期不能为未来时间")
    private Date birthday;

    /**
     * 好友列表
     */
    @Size(min = 1, message = "不能是孤儿")
    private List<@Valid UserInfo> friends;

}
