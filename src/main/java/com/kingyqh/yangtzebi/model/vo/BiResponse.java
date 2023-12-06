package com.kingyqh.yangtzebi.model.vo;

import lombok.Data;

/**
 * Ai生成数据返回类
 */
@Data //注在类上，提供类的get、set、equals、hashCode、toString等方法
public class BiResponse {
    /**
     * 生成图表
     */
    private String genChart;

    /**
     * 生成结论
     */
    private String getResult;

    /**
     * 新生成图表的id
     */
    private long chartId;
}
