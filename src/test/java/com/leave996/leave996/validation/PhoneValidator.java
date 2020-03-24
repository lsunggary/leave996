package com.leave996.leave996.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义手机号约束注解关联验证器
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    /**
     * 自定义校验逻辑方法
     * @param value
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        // 手机号校验规则 170***
        String check = "170\\d{8}";
        Pattern regex = Pattern.compile(check);

        // 进行非空校验
        String phone = Optional.ofNullable(value).orElse("");

        Matcher matcher = regex.matcher(value);

        return matcher.matches();
    }
}
