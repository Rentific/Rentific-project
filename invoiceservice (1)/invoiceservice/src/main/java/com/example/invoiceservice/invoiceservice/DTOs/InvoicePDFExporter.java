package com.example.invoiceservice.invoiceservice.DTOs;


import java.awt.Color;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.invoiceservice.invoiceservice.models.Invoice;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class InvoicePDFExporter {
    private Invoice invoiceData;

    public InvoicePDFExporter(Invoice invoiceData) {
        this.invoiceData = invoiceData;
    }



    public void export(HttpServletResponse response) throws DocumentException, IOException, URISyntaxException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        Path path = Paths.get(ClassLoader.getSystemResource("Logo.png").toURI());


        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Racun za rezervisanu nekretninu br.XX", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        Image img = Image.getInstance(path.toAbsolutePath().toString());
        document.add(img);

        //PdfTemplate

        document.close();

    }
}



