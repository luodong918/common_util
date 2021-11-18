package com.ld.common.demo.common;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author luodong
 * @date 2021/10/14
 */
@Data
@ToString
public class DontProduct {
    @ExcelProperty("图书ID（产品编码）")
    private String productId;
    @ExcelProperty("图书名称")
    private String productName;
    @ExcelProperty("不可发类型")
    private String type;
    @ExcelProperty("不可发客户")
    private String name;
    @ExcelProperty("客户ID")
    private String clientId;
}
