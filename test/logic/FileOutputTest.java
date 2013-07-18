/**
 * 
 */
package logic;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import valueobject.FileContent;

/**
 * @author machupicchubeta
 */
public class FileOutputTest {
    private FileOutput fileOutput;
    private static final String TEST_OUTPUT_FILE_NAME = "/Users/machupicchubeta/dev/Labo/FileContentsSort/test/logic/OutputTestFile";
    private FileContent fileContent;
    private static final String TEST_CONTENT_1 = "テスト1\t男性\t1";
    private static final String TEST_CONTENT_2 = "テスト2\t女性\t1";
    private static final String TEST_CONTENT_3 = "テスト3\t男性\t2";
    @Before
    public void setUp() throws Exception {
        fileContent = new FileContent();
        int rowNumber = 0;
        fileContent.addContent(++rowNumber, TEST_CONTENT_1);
        fileContent.addContent(++rowNumber, TEST_CONTENT_2);
        fileContent.addContent(++rowNumber, TEST_CONTENT_3);
        fileOutput = new FileOutput(TEST_OUTPUT_FILE_NAME, fileContent);
    }
    /**
     * Test method for {@link logic.FileOutput#writeFile()}.
     */
    @Test
    public final void testWriteFile() {
        fileOutput.writeFile();
        final FileInput fileInput = new FileInput(TEST_OUTPUT_FILE_NAME);
        final FileContent actual = fileInput.readFile();
        assertThat(actual.getFileContent().get(new Integer(1)), is(TEST_CONTENT_1));
        assertThat(actual.getFileContent().get(new Integer(2)), is(TEST_CONTENT_2));
        assertThat(actual.getFileContent().get(new Integer(3)), is(TEST_CONTENT_3));
    }
}
