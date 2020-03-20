package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import static com.itextpdf.text.FontFactory.getFont;

public class PDFGenerator {
	public void generatePDF(Loan loan) {
		try {
			createPdf(loan);
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    private void createPdf(Loan loan) throws IOException, DocumentException {
        Document document = new Document();
        String loanType = loan.getLoanType().toString().toLowerCase();
        PdfWriter.getInstance(document, new FileOutputStream("/Users/csperansky/Development/code/chiljasStuff/resources/"
                + loanType + "_calculation.pdf"));

        document.open();

        Font font = getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk(loanType + " calculation", font);
        document.add(chunk);

        PdfPTable table = new PdfPTable(5);
        addTableHeader(table);
        AtomicInteger counter = new AtomicInteger(1);
        Arrays.stream(loan.getRates()).forEach(rate -> {
            addRows(table, rate, counter.getAndIncrement());
        });
        document.add(table);

        document.close();
	}

    private void addTableHeader(PdfPTable table) {
        Stream.of("Month", "Total", "Monthly Rate", "Interest", "New Total")
              .forEach(columnTitle -> {
                  PdfPCell header = new PdfPCell();
                  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                  header.setBorderWidth(2);
                  header.setPhrase(new Phrase(columnTitle));
                  table.addCell(header);
              });
    }

    private void addRows(PdfPTable table, Rate rate, int counter) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

	    table.addCell(new Phrase(String.valueOf(counter)));
	    table.addCell(new Phrase(decimalFormat.format(rate.getRestBefore())));
	    table.addCell(new Phrase(decimalFormat.format(rate.getRate())));
	    table.addCell(new Phrase(decimalFormat.format(rate.getInterest())));
	    table.addCell(new Phrase(decimalFormat.format(rate.getRestAfter())));
    }
}
