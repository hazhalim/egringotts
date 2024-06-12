package net.fitriandfriends.egringotts.utility;

import freemarker.core.HTMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import net.fitriandfriends.egringotts.base.Account;
import net.fitriandfriends.egringotts.base.Transaction;
import net.fitriandfriends.egringotts.service.EmailService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class TransactionReceiptGenerator {

    @Autowired
    private EmailService emailService;

    public String generateTransactionReceipt(Transaction transaction) throws IOException, TemplateException {

        // Prepare the data model
        Map<String, Object> dataModel = new HashMap<>();

        dataModel.put("transactionID", replaceSpecialChars(String.valueOf(transaction.getTransactionId())));
        dataModel.put("type", replaceSpecialChars(transaction.getType()));

        // Format the date to the expected format
        dataModel.put("date", replaceSpecialChars(transaction.getDate().toString()));

        Account fromAccount = transaction.getFromAccount();
        Account toAccount = transaction.getToAccount();

        dataModel.put("fromAccountFullName", fromAccount.getFullName());
        dataModel.put("toAccountFullName", toAccount.getFullName());
        dataModel.put("amount", String.format("%.2f", transaction.getAmount()));
        dataModel.put("currency", transaction.getCurrency().getAbbreviation());
        dataModel.put("paymentMethod", transaction.getPaymentMethod());

        dataModel.put("cardNumber", "N/A");
        dataModel.put("description", transaction.getDescription());

        // Create the FreeMarker configuration
        Configuration cfg = new Configuration(new Version(2, 3, 33));
        cfg.setClassForTemplateLoading(TransactionReceiptGenerator.class, "/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setOutputFormat(HTMLOutputFormat.INSTANCE);

        // Load FreeMarker template
        Template template = cfg.getTemplate("transaction_receipt.html");

        // Create a new PDF document
        PDDocument filledDoc = new PDDocument();
        PDPage page = new PDPage();
        filledDoc.addPage(page);

        // Create a content stream for adding text to the page
        try (PDPageContentStream contentStream = new PDPageContentStream(filledDoc, page)) {

            StringWriter writer = new StringWriter();
            template.process(dataModel, writer);
            String filledTemplate = writer.toString();

            // Replace special characters and add line breaks
            String replacedFilledTemplate = filledTemplate.replace("\n", "<br>");
            replacedFilledTemplate = replaceSpecialChars(replacedFilledTemplate);

            // Add the filled template to PDF
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(50, 700);

            // Handle multi-line text
            String[] lines = replacedFilledTemplate.split("<br>");
            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -15);
            }

            contentStream.endText();
        }

        // Save the filled-in PDF document
        String fromAccountReceiptDirectory = ensureReceiptDirectoryExists(fromAccount);
        String toAccountReceiptDirectory = ensureReceiptDirectoryExists(toAccount);

        String receiptFileName = "/e-gringotts_" + transaction.getTransactionId() + "_" + fromAccount.getAccountID() + "_" + toAccount.getAccountID() + ".pdf";

        filledDoc.save(new File(fromAccountReceiptDirectory, receiptFileName));
        filledDoc.save(new File(toAccountReceiptDirectory, receiptFileName));

        // Close the filled-in document
        filledDoc.close();

        emailService.sendTransactionEmail(transaction.getFromAccount().getAccountID(), transaction.getTransactionId());
        emailService.sendTransactionEmail(transaction.getToAccount().getAccountID(), transaction.getTransactionId());

        // Return the receipt's file name
        return receiptFileName;
    }

    private static String replaceSpecialChars(String input) {
        if (input != null) {
            return input.replace('\u202F', '\u00A0');
        }
        return input;
    }

    public static String ensureReceiptDirectoryExists(Account account) {
        String receiptDirectory = "./src/main/resources/receipts/" + account.getAccountID();
        File receiptFolder = new File(receiptDirectory);

        if (!receiptFolder.exists()) {
            receiptFolder.mkdirs();
        }

        return receiptDirectory;
    }

}


//package net.fitriandfriends.egringotts.utility;
//
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//import freemarker.template.Version;
//import net.fitriandfriends.egringotts.base.Account;
//import net.fitriandfriends.egringotts.base.Transaction;
//import org.apache.pdfbox.Loader;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
//import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
//
//import java.io.*;
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.Map;
//
//public class TransactionReceiptGenerator {
//
//    public static String generateTransactionReceipt(Transaction transaction) throws IOException, TemplateException {
//
//        // Load the PDF template
//        File templateFile = new File("./src/main/resources/EGringottsTransactionReceiptTemplate.pdf");
//        PDDocument templateDocument = Loader.loadPDF(templateFile);
//
//        // Prepare the data model
//        Map<String, Object> dataModel = new HashMap<>();
//
//        dataModel.put("transactionID", transaction.getTransactionId());
//        dataModel.put("type", transaction.getType());
//        dataModel.put("date", transaction.getDate());
//
//        Account fromAccount = transaction.getFromAccount();
//        Account toAccount = transaction.getToAccount();
//
//        dataModel.put("fromAccountFullName", fromAccount.getFullName());
//        dataModel.put("toAccountFullName", toAccount.getFullName());
//        dataModel.put("amount", transaction.getAmount());
//        dataModel.put("currency", transaction.getCurrency().getAbbreviation());
//        dataModel.put("paymentMethod", transaction.getPaymentMethod());
//
//        String cardNumber = "";
//        dataModel.put("cardNumber", "N/A");
//
////                transaction.getCard().getCardNumber() == null ? "N/A" : transaction.getCard().getCardNumber();
//
////        if (cardNumber != null) {
////
////            dataModel.put("cardNumber", cardNumber.substring(cardNumber.length() - 4));
////
////        } else {
////
////            dataModel.put("cardNumber", cardNumber);
////
////        }
//
//        dataModel.put("description", transaction.getDescription());
//
//        // Create the FreeMarker configuration
//        Configuration cfg = new Configuration(new Version(2, 3, 33));
//        cfg.setClassForTemplateLoading(TransactionReceiptGenerator.class, "/");
//
//        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
//        cfg.setDefaultEncoding("UTF-8");
//
//        // Load FreeMarker template
//        Template template = cfg.getTemplate("transaction_receipt.html");
//
//        // Create a new PDF document
//        PDDocument filledDoc = new PDDocument();
//        PDPage page = new PDPage();
//        filledDoc.addPage(page);
//
//        // Create a content stream for adding text to the page
//
//        // Process the FreeMarker template and fill in the PDF
//        try (PDPageContentStream contentStream = new PDPageContentStream(filledDoc, page)) {
//
//            StringWriter writer = new StringWriter();
//            template.process(dataModel, writer);
//            String filledTemplate = writer.toString();
//
//            // Split the filled template into lines
////            String replacedFilledTemplate = filledTemplate.replace("\n", "");
////            String replacedSpecialCharactersFilledTemplate = replacedFilledTemplate.replace('\u202F', '\u00A0');
//
//            // Add the filled template to PDF
//            contentStream.beginText();
//            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 18);
//            contentStream.newLineAtOffset(100, 700);
//
//            contentStream.showText(filledTemplate);
//
//            contentStream.endText();
//
//        }
//
//        // Save the filled-in PDF document
//        String fromAccountReceiptDirectory = ensureReceiptDirectoryExists(fromAccount);
//        String toAccountReceiptDirectory = ensureReceiptDirectoryExists(toAccount);
//
//        String receiptFileName = "e-gringotts_" + transaction.getTransactionId() + "_" + fromAccount.getAccountID() + "_" + toAccount.getAccountID() + ".pdf";
//
//        filledDoc.save(fromAccountReceiptDirectory + "/" + receiptFileName);
//        filledDoc.save(toAccountReceiptDirectory + "/" + receiptFileName);
//
//        // Close the filled in document
//        filledDoc.close();
//
//        // Close the template document
//        templateDocument.close();
//
//        // Return the receipt's file name
//        return receiptFileName;
//
//    }
//
//    public static String ensureReceiptDirectoryExists(Account account) {
//
//        String receiptDirectory = "./src/main/resources/receipts/" + account.getAccountID();
//        File receiptFolder = new File(receiptDirectory);
//
//        if (!receiptFolder.exists()) {
//
//            receiptFolder.mkdirs();
//
//        }
//
//        return receiptDirectory;
//
//    }
//
//}