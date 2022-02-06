package eu.martinaitis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String... args) throws IOException {
        if (args.length != 2) {
            throw new RuntimeException("Bad input. Please provide in and out file paths");
        }

        final Path inputPath = Path.of(args[0]);
        if (!inputPath.toFile().exists()) {
            throw new RuntimeException("No such file under path: " + args[0]);
        }

        final Path outputPath = Path.of(args[1]);
        final File outFile = outputPath.toFile();
        if (!outFile.exists() && !outFile.createNewFile()) {
            throw new RuntimeException("No such file under path: " + args[1]);
        }


        final SwedParser swedParser = new SwedParser();
        final List<YnabRecord> ynabStrings = swedParser.toYnabFormat(inputPath);


        final YnabWriter ynabWriter = new YnabWriter();
        ynabWriter.write(ynabStrings, outFile);

        ynabStrings.forEach(System.out::println);
    }

}
