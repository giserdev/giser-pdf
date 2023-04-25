package com.giser.pdf;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author giserDev
 * @description
 * @date 2023-03-27 23:04:40
 */
public class PdfTest {

        //    作者：CuteXiaoKe
        //    链接：https://zhuanlan.zhihu.com/p/375109293
        //    来源：知乎
        //    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        public static void testPdf() throws IOException {
            PdfWriter writer = new PdfWriter("dest");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4.rotate());
            document.setMargins(20, 20, 20, 20);
            PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
            PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
            Table table = new Table(new float[]{4, 1, 3, 4, 3, 3, 3, 3, 1});
            table.setWidthPercent(100);
            BufferedReader br = new BufferedReader(new FileReader("data.csv"));
            String line = br.readLine();
            process(table, line, bold, true);
            while ((line = br.readLine()) != null) {
                process(table, line, font, false);
            }
            br.close();
            document.add(table);
            document.close();
        }

    //    作者：CuteXiaoKe
    //    链接：https://zhuanlan.zhihu.com/p/375109293
    //    来源：知乎
    //    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public static void process(Table table, String line, PdfFont font, boolean isHeader) {
        StringTokenizer tokenizer = new StringTokenizer(line, ";");
        while (tokenizer.hasMoreTokens()) {
            if (isHeader) {
                table.addHeaderCell(
                        new Cell().add(
                                new Paragraph(tokenizer.nextToken()).setFont(font)));
            } else {
                table.addCell(
                        new Cell().add(
                                new Paragraph(tokenizer.nextToken()).setFont(font)));
            }
        }
    }

}
