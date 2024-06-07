package net.fitriandfriends.egringotts;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TransactionReceiptGenerator {

    public static String generateTransactionReceipt(Transaction transaction) throws IOException, TemplateException {

        // Load the PDF template
        File templateFile = new File("./src/main/resources/EGringottsTransactionReceiptTemplate.pdf");
        PDDocument templateDocument = Loader.loadPDF(templateFile);

        // Prepare the data model
        Map<String, Object> dataModel = new HashMap<>();

        dataModel.put("transactionID", transaction.getTransactionId());
        dataModel.put("date", transaction.getDate());

        Account fromAccount = transaction.getFromAccount();
        Account toAccount = transaction.getToAccount();

        dataModel.put("fromAccountFullName", fromAccount.getFullName());
        dataModel.put("toAccountFullName", toAccount.getFullName());
        dataModel.put("amount", transaction.getAmount());
        dataModel.put("currency", transaction.getCurrency().getAbbreviation());
        dataModel.put("paymentMethod", transaction.getPaymentMethod());

        String cardNumber = transaction.getCard().getCardNumber();

        if (cardNumber != null) {

            dataModel.put("cardNumber", cardNumber.substring(cardNumber.length() - 4));

        } else {

            dataModel.put("cardNumber", "N/A");

        }

        // Create the FreeMarker configuration
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_33);
        cfg.setClassForTemplateLoading(TransactionReceiptGenerator.class, "/");

        // Load FreeMarker template
        Template template = cfg.getTemplate("transaction_receipt.ftl");

        // Create a new PDF document
        PDDocument filledDoc = new PDDocument();
        PDPage page = new PDPage();
        filledDoc.addPage(page);

        // Create a content stream for adding text to the page

        // Process the FreeMarker template and fill in the PDF
        try (PDPageContentStream contentStream = new PDPageContentStream(filledDoc, page)) {

            StringWriter writer = new StringWriter();
            template.process(dataModel, writer);
            String filledTemplate = writer.toString();

            // Add the filled template to PDF
            contentStream.beginText();
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 18);
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText(filledTemplate);
            contentStream.endText();

        }

        // Save the filled-in PDF document
        String fromAccountReceiptDirectory = ensureReceiptDirectoryExists(fromAccount);
        String toAccountReceiptDirectory = ensureReceiptDirectoryExists(toAccount);

        String receiptFileName = "e-gringotts_" + transaction.getTransactionId() + "_" + fromAccount.getAccountID() + "_" + toAccount.getAccountID() + ".pdf";

        filledDoc.save(fromAccountReceiptDirectory + "/" + receiptFileName);
        filledDoc.save(toAccountReceiptDirectory + "/" + receiptFileName);

        // Close the filled in document
        filledDoc.close();

        // Close the template document
        templateDocument.close();

        // Return the receipt's file name
        return receiptFileName;

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