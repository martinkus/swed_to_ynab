package eu.martinaitis;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class YnabWriter {

    private static final List<String> YNAB_HEADERS = List.of("Date", "Payee", "Category", "Memo", "Outflow", "Inflow");

    public void write(List<YnabRecord> records, File outputFile) {
        try (final CSVPrinter printer = new CSVPrinter(new FileWriter(outputFile), CSVFormat.DEFAULT)) {
            printer.printRecord(YNAB_HEADERS);
            for (YnabRecord record : records) {
                final String date = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(record.date());
                printer.printRecord(date, record.payee(), "", record.memo(), record.outflow(), record.inflow());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
