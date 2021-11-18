package com.ld.common.demo.util.excel.factory;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

/**
 * 自定义EasyExcel写工厂
 */
public class EasyExcelWriterFactory<T> {
    private int sheetNo = 0;
    private final ExcelWriter excelWriter;
    private static final String sheetName = "sheet1";

    public EasyExcelWriterFactory(OutputStream outputStream) {
        excelWriter = EasyExcel.write(outputStream).build();
    }

    public EasyExcelWriterFactory(File file) {
        excelWriter = EasyExcel.write(file).build();
    }

    public EasyExcelWriterFactory(String filePath) {
        excelWriter = EasyExcel.write(filePath).build();
    }

    /**
     * 模板表头写入
     *
     * @param headClazz 表头格式
     * @param data      数据 List<ExcelModel> 或者List<List<Object>>
     */
    public void writeModel(Class<T> headClazz, List<T> data) {
        excelWriter.write(data, EasyExcel.writerSheet(this.sheetNo++, sheetName).head(headClazz).build());
        finish();
    }

    /**
     * 自定义表头写入
     *
     * @param head
     * @param data 数据 List<ExcelModel> 或者List<List<Object>>
     */
    public void write(List<List<String>> head, List<T> data) {
        excelWriter.write(data, EasyExcel.writerSheet(this.sheetNo++, sheetName).head(head).build());
        finish();
    }

    /**
     * 链式模板表头写入
     * 注意：方法支持链式调用，但最后一定要一定要调 finish() 方法释放资源
     * @param headClazz 表头格式
     * @param data      数据 List<ExcelModel> 或者List<List<Object>>
     * @return EasyExcelWriterFactory
     */
    public EasyExcelWriterFactory<T> writeModel(Class<T> headClazz, List<T> data, String sheetName) {
        excelWriter.write(data, EasyExcel.writerSheet(this.sheetNo++, sheetName).head(headClazz).build());
        return this;
    }

    /**
     * 链式自定义表头写入
     * 注意：方法支持链式调用，但最后一定要一定要调 finish() 方法释放资源
     * @param head
     * @param data      数据 List<ExcelModel> 或者List<List<Object>>
     * @param sheetName
     * @return EasyExcelWriterFactory
     */
    public EasyExcelWriterFactory<T> write(List<List<String>> head, List<T> data, String sheetName) {
        excelWriter.write(data, EasyExcel.writerSheet(this.sheetNo++, sheetName).head(head).build());
        return this;
    }

    public void finish() {
        excelWriter.finish();
    }
}

