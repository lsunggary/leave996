package com.leave996.leave996.lombok;

import lombok.AccessLevel;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * @Getter注解
 */
public class GetterTest {

    @Getter(
            value = AccessLevel.PRIVATE,
            onMethod_={@NotNull}
    )
    private String field1;
}
