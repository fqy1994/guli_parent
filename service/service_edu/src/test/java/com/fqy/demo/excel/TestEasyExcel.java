package com.fqy.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fan_jennifer
 * @create 2021-09-2021/9/2 15:35
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写操作
        /*String filename = "F:\\write.xlsx";

        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());    */

        String fileName = "F:\\write.xlsx";

        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();;


    }
    

    private  static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10;i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy" + i);
            list.add(data);
        }
        return list;
    }
}
