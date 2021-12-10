package com.hehe;

/**
 * @Author percy.
 * @Date: 2020/10/8
 */

import java.util.List;
import java.util.Map;

public class KeyWordstest {
    public static void main(String[] args) throws Exception {
        KeyWordstest test = new KeyWordstest();
        test.getkeywordtest();
//        test.getkeywordlisttest();
    }

    public void getkeywordtest() {
        System.out.println("======getkeywordtest======");
        // 1.解析pdf文件
        Map<Integer, List<KeyWordBean>> map = KeywordPDFUtils.getPDFText("/Users/percy/Desktop/test.pdf");
        int page = 1;
        int num = 1;
        String keyWord = "文本";
        // 2.获取关键字坐标
        KeyWordBean bean = KeywordPDFUtils.getKeyWordXY(map, page, num, keyWord);
        if (null == bean) {
            System.out.println("未查询到关键字。。。");
        }
        System.out.println(bean.toString());

    }

    public void getkeywordlisttest() {
        System.out.println("======getkeywordlisttest======");
        // 1.解析pdf文件
        Map<Integer, List<KeyWordBean>> map = KeywordPDFUtils.getPDFText("D:\\tmp\\doctopdf\\test.pdf");

        String keyWord = "有效期：";
        // 2.获取关键字坐标
        List<KeyWordBean> beanlist = KeywordPDFUtils.getKeyWordXY(map, keyWord);
        if (beanlist.size() == 0) {
            System.out.println("未查询到关键字。。。");
        }
        for (KeyWordBean bean : beanlist) {
            System.out.println(bean.toString());
        }

    }
}
