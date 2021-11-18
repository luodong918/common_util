package com.ld.common.demo.util.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;

import java.util.List;
import java.util.Map;

/**
 * 不创建对象的读取excel
 * @author luodong
 * @date 2021/11/18
 */
@Slf4j
public class NoObjectEasyExcelListener extends AnalysisEventListener<Map<Integer, String>> {
    private static final int BATCH_COUNT = 5;

    private List<Map<Integer, String>> cachedDataList ;

    /**
     * 读取每行数据
     * @param integerStringMap
     * @param analysisContext
     */
    @Override
    public void invoke(Map<Integer, String> integerStringMap, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(integerStringMap));
        cachedDataList.add(integerStringMap);
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
//            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 读取完成之后
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        log.info("存储数据库成功！");
    }
}
