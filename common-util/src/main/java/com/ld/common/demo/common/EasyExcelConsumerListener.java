package com.ld.common.demo.common;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
                    excelDataConvertException.getColumnIndex();
        }
    }
}
