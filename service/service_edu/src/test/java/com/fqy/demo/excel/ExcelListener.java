package com.fqy.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;


/**
 * @author fan_jennifer
 * @create 2021-09-2021/9/2 15:52
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {
    //一行一行读取excel内容
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("****" + demoData);

    }
    //读取表头的内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头 " + headMap);
    }


    //读取完成后作的事情
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
