package com.ld.common.demo.util.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.handler.WriteHandler;
import com.ld.common.demo.util.excel.factory.EasyExcelWriterFactory;
import com.ld.common.demo.util.excel.listener.EasyExcelConsumerListener;
import com.ld.common.demo.util.excel.listener.ExcelListener;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class ExcelUtil extends EasyExcel {
    private static Integer batchSize = 50;

    /**
     * 解析 Excel 文件并处理.
     *
     * @param inputStream 待解析处理的Excel文件流
     * @param head        映射的对象
     * @param consumer    具体的操作方法：lambda 表达式
     * @return
     */
    public static <T> ExcelReaderBuilder read(InputStream inputStream, Class<T> head, Consumer<List<T>> consumer) {
        return read(inputStream, head, new EasyExcelConsumerListener<>(batchSize, consumer));
    }

    /**
     * 解析 Excel 文件并处理.
     *
     * @param filePath 待解析处理的Excel文件路径
     * @param head     映射的对象
     * @param consumer 具体的操作方法：lambda 表达式
     * @return
     */
    public static <T> ExcelReaderBuilder read(String filePath, Class<T> head, Consumer<List<T>> consumer) {
        return read(filePath, head, new EasyExcelConsumerListener<>(batchSize, consumer));
    }

    /**
     * 解析 Excel 文件并处理.
     *
     * @param inputStream 待解析处理的Excel文件流
     * @param head        映射的对象
     * @param batchSize   批次的大小：一次提交的数据量
     * @param consumer    具体的操作方法：lambda 表达式
     * @return
     */
    public static <T> ExcelReaderBuilder read(InputStream inputStream, Class<T> head, Integer batchSize,
                                              Consumer<List<T>> consumer) {
        return read(inputStream, head, new EasyExcelConsumerListener<>(batchSize, consumer));
    }

    /**
     * 解析 Excel 文件并处理.
     *
     * @param filePath  待解析处理的Excel文件路径
     * @param head      映射的对象
     * @param batchSize 批次的大小：一次提交的数据量
     * @param consumer  具体的操作方法：lambda 表达式
     * @return
     */
    public static <T> ExcelReaderBuilder read(String filePath, Class<T> head, Integer batchSize,
                                              Consumer<List<T>> consumer) {
        return read(filePath, head, new EasyExcelConsumerListener<>(batchSize, consumer));
    }

    /**
     * 无模板写文件
     *
     * @param filePath
     * @param head     表头数据
     * @param data     表内容数据
     */
    public static void write(String filePath, List<List<String>> head, List<List<Object>> data) {
        EasyExcel.write(filePath).head(head).sheet().doWrite(data);
    }

    /**
     * 无模板写文件
     *
     * @param filePath
     * @param head      表头数据
     * @param data      表内容数据
     * @param sheetNo   sheet页号，从0开始
     * @param sheetName sheet名称
     */
    public static void write(String filePath, List<List<String>> head, List<List<Object>> data, Integer sheetNo,
                             String sheetName) {
        EasyExcel.write(filePath).head(head).sheet(sheetNo, sheetName).doWrite(data);
    }

    /**
     * 根据excel模板文件写入文件
     *
     * @param filePath
     * @param templateFileName
     * @param headClazz
     * @param data
     */
    public static void writeTemplate(String filePath, String templateFileName, Class headClazz, List data) {
        EasyExcel.write(filePath, headClazz).withTemplate(templateFileName).sheet().doWrite(data);
    }

    /**
     * 根据excel模板文件写入文件
     *
     * @param filePath
     * @param templateFileName
     * @param data
     */
    public static void writeTemplate(String filePath, String templateFileName, List data) {
        EasyExcel.write(filePath).withTemplate(templateFileName).sheet().doWrite(data);
    }

    /**
     * 按模板写文件
     *
     * @param filePath
     * @param headClazz 表头模板
     * @param data      数据
     */
    public static void write(String filePath, Class headClazz, List data) {
        EasyExcel.write(filePath, headClazz).sheet().doWrite(data);
    }

    /**
     * 按模板写文件
     *
     * @param filePath
     * @param headClazz 表头模板
     * @param data      数据
     * @param sheetNo   sheet页号，从0开始
     * @param sheetName sheet名称
     */
    public static void write(String filePath, Class headClazz, List data, Integer sheetNo, String sheetName) {
        EasyExcel.write(filePath, headClazz).sheet(sheetNo, sheetName).doWrite(data);
    }

    /**
     * 按模板写文件
     *
     * @param filePath
     * @param headClazz    表头模板
     * @param data         数据
     * @param writeHandler 自定义的处理器，比如设置table样式，设置超链接、单元格下拉框等等功能都可以通过这个实现（需要注册多个则自己通过链式去调用）
     * @param sheetNo      sheet页号，从0开始
     * @param sheetName    sheet名称
     */
    public static void write(String filePath, Class headClazz, List data, WriteHandler writeHandler, Integer sheetNo,
                             String sheetName) {
        EasyExcel.write(filePath, headClazz).registerWriteHandler(writeHandler).sheet(sheetNo, sheetName).doWrite(data);
    }

    /**
     * 按模板写文件（包含某些字段）
     *
     * @param filePath
     * @param headClazz   表头模板
     * @param data        数据
     * @param includeCols 过滤包含的字段，根据字段名称过滤
     * @param sheetNo     sheet页号，从0开始
     * @param sheetName   sheet名称
     */
    public static void writeInclude(String filePath, Class headClazz, List data, Set<String> includeCols,
                                    Integer sheetNo, String sheetName) {
        EasyExcel.write(filePath, headClazz).includeColumnFiledNames(includeCols).sheet(sheetNo, sheetName).doWrite(data);
    }

    /**
     * 按模板写文件（排除某些字段）
     *
     * @param filePath
     * @param headClazz   表头模板
     * @param data        数据
     * @param excludeCols 过滤排除的字段，根据字段名称过滤
     * @param sheetNo     sheet页号，从0开始
     * @param sheetName   sheet名称
     */
    public static void writeExclude(String filePath, Class headClazz, List data, Set<String> excludeCols,
                                    Integer sheetNo, String sheetName) {
        EasyExcel.write(filePath, headClazz).excludeColumnFiledNames(excludeCols).sheet(sheetNo, sheetName).doWrite(data);
    }

    /**
     * 可用于Web页面数据的导出。
     *
     * @param response
     * @param exportFileName 导出的文件名称
     * @param headClass      映射的对象
     * @param data           待写入的数据
     * @param sheetName      sheet名称
     * @return
     */
    public static void writeWithSheetsWeb(HttpServletResponse response, String exportFileName, Class headClass,
                                          List data, String sheetName) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        String fileName = URLEncoder.encode(exportFileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcelWriterFactory excelWriter = new EasyExcelWriterFactory(response.getOutputStream());
        excelWriter.writeModel(headClass, data, sheetName).finish();
    }

    /**
     * 可用于Web页面数据的导出。
     *
     * @param response
     * @param exportFileName 导出的文件名称
     * @param headClass      映射的对象
     * @param data           待写入的数据
     * @return
     */
    public static void writeWithSheetsWeb(HttpServletResponse response, String exportFileName, Class headClass,
                                          List data) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        String fileName = URLEncoder.encode(exportFileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcelWriterFactory excelWriter = new EasyExcelWriterFactory(response.getOutputStream());
        excelWriter.writeModel(headClass, data);
    }

    /**
     * 获取sheet页的sheetname
     *
     * @param in       InputStream输入流
     * @param sheetNum sheetNum 需要获取sheetname的sheet页
     * @return sheetName
     */
    public static String getSheetName(InputStream in, int sheetNum) {
        ExcelReader excelReader = null;
        excelReader = EasyExcel.read(in).build();
        List<List<String>> head = new ArrayList<>();
        ExcelListener newexeLister = new ExcelListener();
        //获取sheet对象
        ReadSheet readSheet =
                EasyExcel.readSheet(sheetNum).head(head).registerReadListener(newexeLister).build();
        //读取数据
        excelReader.read(readSheet, readSheet);
        String sheetName = excelReader.analysisContext().readSheetHolder().getSheetName();
        return sheetName;
    }
}
