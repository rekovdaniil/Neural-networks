import org.junit.Test;
import utils.DataFileParser;

import static org.junit.Assert.*;

/**
 * Created by Daniil on 26.05.2018.
 */
public class DataFileParserTest {
    DataFileParser TestDataFileParser = new DataFileParser();

    @Test
    public void checkFileCSV() throws Exception {
        assertTrue(TestDataFileParser.checkFileCSV( "C:\\Users\\Daniil\\Desktop\\data.csv"));
    }

    @Test

    public void fileParseCSV() throws Exception {
        assertNotNull(TestDataFileParser.FileParseCSV( "C:\\Users\\Daniil\\Desktop\\data.csv"));
    }
}
