package com.dyx.model.pojo;

import lombok.Data;

/**
 * @author Davison
 * @description ResponsePojo
 * @date 2023/1/23 14:23
 */
@Data
public class ResponsePojo {
    private Integer code;

    private String message;

    private Object data;
}
