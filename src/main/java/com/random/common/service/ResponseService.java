package com.random.common.service;

import com.random.common.CommonResponse;
import com.random.common.CommonResult;
import com.random.common.ListResult;
import com.random.common.SingleResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setWord(list);
        setSuccessResult(result);
        return result;
    }


    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

    public CommonResult getFailResult(CommonResponse commonResponse) {
        CommonResult result = new CommonResult();
        setFailResult(commonResponse, result);
        return result;
    }
    private void setFailResult(CommonResponse commonResponse, CommonResult result) {
        result.setSuccess(false);
        result.setMsg(commonResponse.getWord());
        result.setCode(commonResponse.getCode());
    }

    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getWord());
    }

}