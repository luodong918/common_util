package com.ld.common.demo.util.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelListener extends AnalysisEventListener<Object> {
    private static final Logger logger = LoggerFactory.getLogger(ExcelListener.class);

    private List<Object> data = new ArrayList<>();

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
       int a = analysisContext.getTotalCount();
        logger.info(String.valueOf(a));
        data.add(o);
    }
    /**
     * 这里会一行行的返回头
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context){
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        logger.info("所有数据解析完成！");
    }

    /**
     * 入库
     */
    private void saveData() {
        logger.info("{}条数据，开始存储数据库！", data.size());
        //这个方法自己实现  能完成保存数据入库即可
        logger.info("存储数据库成功！");
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
