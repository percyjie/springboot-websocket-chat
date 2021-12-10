package com.hehe;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author percy.
 * @Date: 2020/10/8
 */
public class PdfTest {


    public static void main(String[] args) throws Exception {

        addContent("/Users/percy/Desktop/test.pdf", "/Users/percy/Desktop/test2.pdf");
    }

    public static void addContent(String filePath, String savePath) throws IOException, DocumentException {

        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
        Font font = new Font(baseFont);

        PdfReader reader = new PdfReader(new FileInputStream(filePath));
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(savePath));


        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            PdfContentByte over = stamper.getOverContent(i);
            ColumnText columnText = new ColumnText(over);
            // llx 和 urx  最小的值决定离左边的距离. lly 和 ury 最大的值决定离下边的距离
            columnText.setSimpleColumn(150, 131, 300, 131);
            Paragraph elements = new Paragraph(0, new Chunk("CT166732655H"));

            // 设置字体，如果不设置添加的中文将无法显示
            elements.setFont(font);
            columnText.addElement(elements);
            columnText.go();

            ColumnText columnText2 = new ColumnText(over);
            columnText2.setSimpleColumn(150, 115, 300, 115);
            Paragraph elements2 = new Paragraph(0, new Chunk("2017-12-02~2019-03-03"));
            elements2.setFont(font);
            columnText2.addElement(elements2);

            columnText2.go();
        }
        stamper.close();
    }

}

