package pl.pjwstk.lab06.accestodatabase.Files;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import pl.pjwstk.lab06.accestodatabase.Repositories.InstalmentRepository;
import pl.pjwstk.lab06.accestodatabase.Timetable.Installment;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Component
public class Pdf {
    public static void getFile(HttpServletResponse response, int id, InstalmentRepository instalmentRepository) throws IOException, IOException {
        com.lowagie.text.Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Number", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Capital", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Interest", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Fixed Rate", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Capital to pay", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Amount", font));
        table.addCell(cell);

        List<Installment> installmentList = instalmentRepository.findById(id).stream().toList();
        for (Installment installment : installmentList) {
            table.addCell(String.valueOf(installment.getId()));
            table.addCell(String.valueOf(installment.getCapital()));
            table.addCell(String.valueOf(installment.getInterest()));
            table.addCell(String.valueOf(installment.getFixedFee()));
            table.addCell(String.valueOf(installment.getCapitalToPay()));
            table.addCell(String.valueOf(installment.getAmount()));
        }

        document.add(table);

        document.close();
    }
}
