package com.leave996.leave996.lombok;

import lombok.AccessLevel;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @Setter 注解
 */
public class SetterTest {

    @Setter(
            value = AccessLevel.PRIVATE,
            onMethod_= {@NotNull}
    )
    private String demo;

}
