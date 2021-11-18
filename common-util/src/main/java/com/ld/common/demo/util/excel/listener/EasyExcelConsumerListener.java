package com.ld.common.demo.util.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * 一般正常的读取excel
 * @param <T>
 */
@Slf4j
public class EasyExcelConsumerListener<T> extends AnalysisEventListener<T> {
    private int batchSize;
    private List<T> list;
    private Consumer<List<T>> consumer;

    public EasyExcelConsumerListener(int batchSize, Consumer<List<T>> consumer) {
        this.batchSize = batchSize;
        this.consumer = consumer;
        list = new ArrayList<>(batchSize);
    }

    @Override
    public void invoke(T data, AnalysisContext analysisContext) {
        log.info("每行读取数据：{{}}",data.toString());
        list.add(data);
        if (list.size() >= batchSize) {
            consumer.accept(list);
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        consumer.accept(list);
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.info("异常处理");
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
            log.error("第{}行，第{}列解析异常，数据为:{}", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex(), excelDataConvertException.getCellData());
//                    excelDataConvertException.getColumnIndex();
        }
    }



}
