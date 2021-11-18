package com.ld.common.demo.read;


import com.ld.common.demo.util.excel.ExcelUtil;
import com.ld.common.demo.vo.DontProduct;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author luodong
 * @date 2021/11/18
 */
public class ReadExcel {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "/Users/luodong/Desktop/DontProduct.xlsx";
        ArrayList<DontProduct> list = new ArrayList<>();
        ExcelUtil.read(new FileInputStream(new File(filePath)), DontProduct.class, list::addAll).sheet(0).doRead();
        list.forEach(System.out::println);
        System.out.println("s");
    }
}
