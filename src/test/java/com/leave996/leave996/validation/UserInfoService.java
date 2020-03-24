package com.leave996.leave996.validation;

import javax.validation.Valid;

/**
 * 用户信息使用类
 */
public class UserInfoService {

    /**
     * UserInfo 作为输入参数
     * @param userInfo
     */
    public void setUserInfo (@Valid UserInfo userInfo) {

    }

    /**
     * UserInfo 作为输出参数
     * @return
     */
    public @Valid UserInfo getUserInfo() {
        return new UserInfo();
    }

    /**
     * 默认构造
     */
    public UserInfoService() {}

    /**
     * 接收UserInfo的构造
     * @param userInfo
     */
    public UserInfoService(@Valid UserInfo userInfo) {}
}
