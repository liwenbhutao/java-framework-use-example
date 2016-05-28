package com.yz.bourse.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hutao on 15/7/1.
 * 下午2:07
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    /**
     * 成功返回值
     */
    private final static int SUCCESS_CODE = 0;
    /**
     * 成功回复报文包含的对象
     */
    @JsonProperty("result")
    private final Object obj;
    /**
     * 返回值
     */
    @JsonProperty("code")
    private final int code;
    /**
     * 返回msg
     */
    @JsonProperty("msg")
    private final String message;
    /**
     * 其余参数
     */
    @JsonProperty("params")
    private final Map<String, String> params;

    private BaseResponse(final Object obj, final int code, final String message,
                         final Map<String, String> params) {
        this.obj = obj;
        this.code = code;
        this.message = message;
        this.params = params;
    }

    /**
     * 构造成功报文builder
     *
     * @return 成功报文builder
     */
    public static Builder successCustom() {
        return successCustom("");
    }

    /**
     * 构造成功报文builder
     *
     * @param message
     * @return
     */
    public static Builder successCustom(final String message) {
        return new Builder(SUCCESS_CODE, message);
    }

    /**
     * 构造错误返回报文builder
     *
     * @param errorCode 错误码
     * @param errorMsg  错误信息
     * @return 错误返回报文builder
     */
    public static Builder failedCustom(final int errorCode, final String errorMsg) {
        return new Builder(errorCode, errorMsg);
    }

    /**
     *
     */
    public static final class Builder {
        /**
         * 返回值
         */
        private final int retCode;
        /**
         * msg
         */
        private final String message;
        /**
         * 其他参数
         */
        private final Map<String, String> params = new HashMap<>();
        /**
         * 任意可json化的对象
         */
        private Object obj;

        private Builder(final int retCode, final String message) {
            this.retCode = retCode;
            this.message = message;
        }

        /**
         * 添加参数信息
         *
         * @param key
         * @param value
         * @return
         */
        public Builder addParam(final String key, final String value) {
            params.put(key, value);
            return this;
        }

        /**
         * 设置result obj
         *
         * @param obj
         * @return
         */
        public Builder setObj(final Object obj) {
            this.obj = obj;
            return this;
        }

        /**
         * build BaseResponse
         *
         * @return
         */
        public BaseResponse build() {
            return new BaseResponse(obj == null ? "" : obj,
                    retCode, message, params);
        }
    }
}
