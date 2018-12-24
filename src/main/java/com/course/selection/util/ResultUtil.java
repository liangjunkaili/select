package com.course.selection.util;


import com.course.selection.dto.Result;
import com.course.selection.enums.ResultEnum;

/**
 * 描述
 *
 * @author Y.S.K
 * @date 2018/8/8 14:35
 */
public class ResultUtil {
    public static Result success(Object object) {
        return Result.builder()
                .state(ResultEnum.SUCCESS.getState())
                .msg(ResultEnum.SUCCESS.getMsg())
                .data(object)
                .build();
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(ResultEnum resultEnum) {
        return Result.builder()
                .state(resultEnum.getState())
                .msg(resultEnum.getMsg())
                .build();
    }
}
