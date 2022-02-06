package eu.martinaitis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

class MainTest {

    private Path inPath;
    private Path outPath;

    private static final String EXPECTED_CONTENTS = "Date,Payee,Category,Memo,Outflow,Inflow\r\n" +
            "04/01/2021,Woltė 00180 Helsinki,,Memo 1,26.90,\r\n" +
            "04/01/2021,Some Membership,,Memo 2,6.05,\r\n" +
            "05/01/2021,,,Memo 3,0.70,\r\n" +
            "05/01/2021,Wolt 00180 Helsinki,,Memo 4,38.43,\r\n";

    @BeforeEach
    public void setup() throws URISyntaxException, IOException {
        inPath = Path.of(getClass().getResource("swed_2020.csv").toURI());
        outPath = Path.of(getClass().getResource("").toURI()).resolve(Path.of("out.csv"));

        if (outPath.toFile().exists()) {
            Files.delete(outPath);
        }
    }


    @Test
    public void testBadInputPath() {
        Assertions.assertThrows(RuntimeException.class, () -> Main.main("asfasfaf", "XXX"));
    }

    @Test
    public void testBadOutputPath() {
        Assertions.assertThrows(RuntimeException.class, () -> Main.main(inPath.toAbsolutePath().toString(), "////"));
    }

    @Test
    public void testMain() throws IOException {
        Main.main(inPath.toAbsolutePath().toString(), outPath.toAbsolutePath().toString());

        Assertions.assertEquals(EXPECTED_CONTENTS, Files.readString(outPath));
    }


}