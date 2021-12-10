package com.hehe;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

public class KeywordPDFUtils {

    /**
     * 将pdf内容按页面读取map中，map中的key为pdf的页面，value为该页的内容块list
     * @param filePath
     * @return
     */
    public static Map<Integer, List<KeyWordBean>> getPDFText(String filePath) {
        Map<Integer, List<KeyWordBean>> map = new HashMap<Integer, List<KeyWordBean>>();
        try {
            PdfReader pdfReader = new PdfReader(filePath);
            int pageNum = pdfReader.getNumberOfPages();
            PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);

            for (int i = 1; i <= pageNum; i++) {
                List<KeyWordBean> lists = new ArrayList<>();
                List<Integer> pagelist = new ArrayList<Integer>();
                pagelist.add(0, i);
                pdfReaderContentParser.processContent(i, new RenderListener() {
                    @Override
                    public void renderText(TextRenderInfo textRenderInfo) {
                        String text = textRenderInfo.getText(); // 整页内容
                        com.itextpdf.awt.geom.Rectangle2D.Float boundingRectange = textRenderInfo.getBaseline()
                                .getBoundingRectange();
                        KeyWordBean bean = new KeyWordBean();
                        bean.setX(boundingRectange.x);
                        bean.setY(boundingRectange.y);
                        bean.setPage(pagelist.get(0));
                        bean.setText(text);
                        lists.add(bean);

                    }

                    @Override
                    public void renderImage(ImageRenderInfo arg0) {
                    }

                    @Override
                    public void endTextBlock() {
                    }

                    @Override
                    public void beginTextBlock() {

                    }
                });
                map.put(i, lists);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 根据第几页第几个关键字查找该关键字的xy坐标
     * @param map2 pdf内容块，以页为key
     * @param page 页
     * @param num 页中的第几个
     * @param keyWord 关键字
     * @return
     */
    public static KeyWordBean getKeyWordXY(Map<Integer, List<KeyWordBean>> map2, int page, int num, String keyWord) {
        List<KeyWordBean> list=getKeyWordXY(map2,keyWord);
        for(KeyWordBean bean:list) {
            if(bean.getPage()==page && bean.getNum()==num) {
                return bean;
            }
        }
        return null;
    }
    /**
     * 根据关键字查找该关键字在pdf中的xy坐标list
     * @param map2 key为pdf的页，value为该的内容块list
     * @param keyWord
     * @return
     */
    public static List<KeyWordBean> getKeyWordXY(Map<Integer, List<KeyWordBean>> map2,String keyWord) {
        int keyMatch = 1;
        StringBuilder content = new StringBuilder();
        List<KeyWordBean> keywordlist= new ArrayList<KeyWordBean>();
        for(int page:map2.keySet()) {
            List<KeyWordBean> list=map2.get(page);

            Collections.sort(list);// 正序比较
            for (int i = 0; i < list.size(); i++) {
                KeyWordBean bean = list.get(i);
                String text = bean.getText();
                if (i + 1 != list.size()) {
                    KeyWordBean beanNext = list.get(i + 1);
                    float x = beanNext.getX() - bean.getX();
                    float y = beanNext.getY() - bean.getY();
                    if (y == 0 && x <= 1) {
                    } else {
                        if (StringUtils.contains(content.toString(), keyWord) || StringUtils.contains(text, keyWord)) {
                            bean.setNum(keyMatch++);
                            keywordlist.add(bean);

                        } else if ((!StringUtils.isEmpty(text) && keyWord.startsWith(text)) || content.length() > 0) {
                            content.append(text);
                            if (content.length() >= keyWord.length()) {
                                if (StringUtils.contains(content.toString(), keyWord)) {
                                    bean.setNum(keyMatch++);
                                    keywordlist.add(bean);

                                }
                                content = new StringBuilder();
                            }
                        }
                    }
                } else {
                    if (StringUtils.contains(content.toString(), keyWord) || StringUtils.contains(text, keyWord)) {
                        bean.setNum(keyMatch++);
                        keywordlist.add(bean);
                    } else if ((!StringUtils.isEmpty(text) && keyWord.startsWith(text)) || content.length() > 0) {
                        content.append(text);
                        if (content.length() >= keyWord.length()) {
                            if (StringUtils.contains(content.toString(), keyWord)) {
                                bean.setNum(keyMatch++);
                                keywordlist.add(bean);
                            }
                            content = new StringBuilder();
                        }
                    }
                }
            }
        }
        return keywordlist;
    }
}