package eu.martinaitis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;

class SwedParserTest {

    private SwedParser swedParser;
    private Path path;

    @BeforeEach
    public void setup() throws URISyntaxException {
        path = Path.of(getClass().getResource("swed_2020.csv").toURI());
        swedParser = new SwedParser();
    }

    @Test
    public void testToYnabFormat() {
        Assertions.assertEquals(4, swedParser.toYnabFormat(path).size());
    }
}