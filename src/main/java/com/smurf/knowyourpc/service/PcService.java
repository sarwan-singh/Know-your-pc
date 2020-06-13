package com.smurf.knowyourpc.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PcService {
    public static ByteArrayInputStream getPc() {

        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Properties properties = System.getProperties();

        try{
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            float[] columnWidths = {1f,1f};
            table.setWidths(columnWidths);
            table.addCell("Key");
            table.addCell("Value");
            table.addCell("");
            Map<String , String> map = new HashMap<>();

            properties.forEach((k,v)->table.addCell( map.put(k.toString(),  v.toString())));

            Set< Map.Entry< String,String> > st = map.entrySet();

            for (Map.Entry< String,String> me:st)
            {
                table.addCell(me.getKey());
                table.addCell(me.getValue());
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);
            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(PcService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ByteArrayInputStream(out.toByteArray());


    }
}
