package com.ld.common.demo.read;

import com.ld.common.demo.common.DontProduct;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author luodong
 * @date 2021/11/18
 */
public class ReadExcel {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "/Users/luodong/Desktop/DontProduct.xlsx";
        ArrayList<DontProduct> dontProducts = new ArrayList<>();
        System.out.println("s");
//        ExcelReaderBuilder read = ExcelUtil.read(new FileInputStream(new File(filePath)), DontProduct.class,
//                dontProducts::addAll);
//        System.out.println(read);
    }
}
