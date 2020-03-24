package com.leave996.leave996.validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * 验证测试类
 */
public class ValidationTest {

    /**
     * 验证器对象
     */
    private Validator validator;
    /**
     * 待验证对象
     */
    private UserInfo userInfo;

    private Set<ConstraintViolation<UserInfo>> set;

    private Set<ConstraintViolation<UserInfoService>> serviceSet;

    /**
     * 实例化对象
     */
    @Before
    public void init() {
        //初始化验证器
        validator = Validation.buildDefaultValidatorFactory().getValidator();

        //初始化验证对象
        userInfo = new UserInfo();
        userInfo.setUsername("发的实现");
//        userInfo.setUserId("0001");
//        userInfo.setPassword("fedsfvcxds");
        userInfo.setEmail("4213546211");
        userInfo.setAge(22);
        userInfo.setPhone("17011865800");
        //获取时间
        Calendar calendar = Calendar.getInstance();
//        calendar.set(2020,10,1);
        /**
         * calendar.set()方法 第二个参数 月 需要比当前月少1.即3月需要填入2.1月需要填入0
         */
        calendar.set(2020,2,01);
        userInfo.setBirthday(calendar.getTime());

        UserInfo friend = UserInfo.builder()
                .age(18)
                .birthday(calendar.getTime())
                .email("124555@aa.cm")
                .password("12312333")
                .phone("")
                .userId("123fsdf")
                .username("1fsdt2")
                .friends(new ArrayList())
                .build();

        userInfo.setFriends(new ArrayList(){{add(friend);}});
//        userInfo.setFriends(new ArrayList());
    }

    /**
     * 结果打印
     */
    @After
    public void print() {
        set.forEach(item -> {
            //输出验证错误信息
            System.out.println(item.getMessage());
        });

//        serviceSet.forEach(item -> {
//            //输出验证错误信息
//            System.out.println(item.getMessage());
//        });
    }

    @Test
    public void nullValidation() {
        set = validator.validate(userInfo);
    }

    /**
     * 级联验证的测试类
     */
    @Test
    public void graphValidation() {
        set = validator.validate(userInfo);
    }

    /**
     * 分组验证
     */
    @Test
    public void groupValidation() {
        set = validator.validate(userInfo,
                UserInfo.RegisterGroup.class,
                UserInfo.LoginGroup.class);
    }

    /**
     * 组序列
     * 按照指定顺序验证，如果验证失败即终止验证。避免耗时操作
     */
    @Test
    public void groupSequenceValidation() {
        set = validator.validate(userInfo,
                UserInfo.Group.class);
    }

    /**
     * 对方法输入参数进行约束注解校验
     */
    @Test
    public void paramsValidation() throws NoSuchMethodException {
        //获取校验执行器
        ExecutableValidator executableValidator =
                validator.forExecutables();

        // 待验证对象
        UserInfoService userInfoService = new UserInfoService();

        // 待验证方法
        Method method = userInfoService.getClass()
                .getMethod("setUserInfo", UserInfo.class);

        // 待验证参数
        Object[] paramObject = new Object[] {new UserInfo()};
        //对方法输入参数进行验证
        serviceSet =
                executableValidator.validateParameters(
                        userInfoService,
                        method,
                        paramObject);
    }

    /**
     * 对方法返回值进行校验
     */
    @Test
    public void returnValueValidation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();

        // 构造要验证的方法对象
        UserInfoService service = new UserInfoService();

        Method method = service.getClass()
                .getMethod("getUserInfo");

        // 调用方法得到返回值
        Object returnValue = method.invoke(service);

        // 校验方法返回值是否符合约束
        serviceSet = executableValidator.validateReturnValue(service, method, returnValue);

    }

    /**
     * 对构造函数输入参数进行校验
     */
    @Test
    public void constructorValidation() throws NoSuchMethodException {

        // 获取验证执行器
        ExecutableValidator executableValidator = validator.forExecutables();

        // 获取构造函数
        Constructor constructgor = UserInfoService.class.getConstructor(UserInfo.class);
        Object[] paramsObjects = new Object[]{new UserInfo()};

        // 校验构造函数
        serviceSet = executableValidator.validateConstructorParameters(constructgor, paramsObjects);
    }
}
