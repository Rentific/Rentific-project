package com.example.invoiceservice.invoiceservice.services;

import com.example.invoiceservice.invoiceservice.models.Invoice;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.stereotype.Service;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Service
public class SendEmailService {

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);

    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 14);

    private static Font smallBold2 = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);

    String body2 = "Poštovani," + System.lineSeparator() + System.lineSeparator() +
            "Ovim putem Vam potvrđujemo da ste uspješno rezervisali željenu nekretninu. Molimo Vas u prilogu ovog email-a pronađite račun za rezervisanu nekreetninu." +
            "Sa računom je potrebno da odete u Vašu banku kako biste izvršili plaćanje rezervisane nekretnine." +
            System.lineSeparator()  + System.lineSeparator()  +"Za više informacija, možete nas kontaktirati putem e-maila ili kontakt telefona 033/ 222-333." +
            System.lineSeparator()  + System.lineSeparator()  + "Hvala Vam na povjerenju," +
            System.lineSeparator() + "Vaš Rentific tim!";

    public void sendEmail(String toEmail,
                          String body,
                          String subject,
                          Invoice invoice) throws Exception {


        String smtpHost = "smtp.gmail.com"; //replace this with a valid host
        int smtpPort = 587; //replace this with a valid port

        String sender = "rentificapp@gmail.com"; //replace this with a valid sender email address
        String recipient = toEmail; //replace this with a valid recipient email address


        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        //properties.put("mail.password", "zlgoubhieydwrmrs");

        Session session = Session.getDefaultInstance(properties,    new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        "rentificapp@gmail.com", "zlgoubhieydwrmrs");
            }
        });

        ByteArrayOutputStream outputStream = null;

        try {
            //construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(body2);

            //now write the PDF content to the output stream
            outputStream = new ByteArrayOutputStream();
            writePdf(outputStream, invoice);
            byte[] bytes = outputStream.toByteArray();

            //construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("Invoice.pdf");

            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);

            //create the sender/recipient addresses
            InternetAddress iaSender = new InternetAddress(sender);
            InternetAddress iaRecipient = new InternetAddress(recipient);

            //construct the mime multi part
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);

            //construct the mime message
            MimeMessage message = new MimeMessage(session);
            message.setSender(iaSender);
            message.setSubject(subject);
            message.setRecipient(Message.RecipientType.TO, iaRecipient);
            message.setContent(mimeMultipart);

            //send off the email
            Transport.send(message);


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //clean off
            if (null != outputStream) {
                try {
                    outputStream.close();
                    outputStream = null;
                } catch (Exception ex) {
                }
            }
        }
    }


    public void writePdf (ByteArrayOutputStream outputStream, Invoice invoice) throws Exception {


        Paragraph preface = new Paragraph();

        // Lets write a big header
        preface.add(new Paragraph("Racun za rezervisanu nekretninu br." + invoice.getInvoiceId()  , catFont));

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                        "Informacije o nekretnini: ", subFont));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(
                        "Naziv Vase nekretnine: " + invoice.getRealEstate().getName(), smallBold));
        preface.add(new Paragraph(
                        "Cijena Vase nekretnine: " + invoice.getRealEstate().getPrice() +" KM", smallBold));
        addEmptyLine(preface, 1);

        preface.add(new Paragraph(
                        "Informacije o kupcu: ", subFont));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph(
                        "Ime i prezime: " + invoice.getUser().getFirstName() +" " + invoice.getUser().getLastName(), smallBold));
        preface.add(new Paragraph(
                        "Email: " + invoice.getUser().getEmail(), smallBold));
        addEmptyLine(preface, 3);
        preface.add(new Paragraph(
                        "UKUPAN IZNOS ZA UPLATU: " + invoice.getRealEstate().getPrice() + " KM",
                        subFont));
        preface.add(new Paragraph(
                        "Datum kreiranja racuna: " + invoice.getInvoiceDate(),
                        smallBold));

        addEmptyLine(preface, 3);

        preface.add(new Paragraph(
                        "Hvala na povjerenju, " +  System.lineSeparator() + "Vas Rentific!",
                        smallBold2));

        Path path = Paths.get(ClassLoader.getSystemResource("logo.png").toURI());

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        document.open();

        document.addTitle("Račun za rezervisanu nekretninu br. " + invoice.getInvoiceId());
        document.addSubject("Račun");
        document.addKeywords("Invoice, email");
        document.addAuthor("Rentific");
        document.addCreator("Rentific");

        document.add(preface);
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        img.scalePercent(15);
        document.add(img);

        document.close();
    }


    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}

