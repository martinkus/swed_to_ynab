package eu.martinaitis;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SwedParser {

    public static final String TRANSACTION_ID = "20"; //Swed csv has an id for each line, 20 stands for transaction
    public static final CSVFormat SWED_FORMAT = CSVFormat.DEFAULT.builder()
                                                                 .setHeader()
                                                                 .setAllowMissingColumnNames(true)
                                                                 .build();

    public List<YnabRecord> toYnabFormat(Path path) {
        try {
            return CSVParser.parse(path, StandardCharsets.UTF_8, SWED_FORMAT)
                            .stream()
                            .filter(rec -> rec.get(1).equals(TRANSACTION_ID))
                            .map(this::csvToYnabRecord)
                            .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private YnabRecord csvToYnabRecord(CSVRecord rec) {
        final LocalDate date = LocalDate.parse(rec.get("Data"));
        final String payee = rec.get("Gavėjas");
        final String memo = rec.get("Paaiškinimai");
        final String amount = rec.get("Suma");
        final String inOutFlow = rec.get("D/K");
        final String inflow = inOutFlow.equals("K") ? amount : "";
        final String outflow = inOutFlow.equals("D") ? amount : "";
        return new YnabRecord(date, payee, memo, inflow, outflow);
    }


}

