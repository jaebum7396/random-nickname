package com.example.gitaction.common;

import lombok.Data;

@Data
public class SingleResult<T> extends CommonResult {
    private T data;
}
