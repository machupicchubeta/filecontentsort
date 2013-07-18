/**
 * 
 */
package logic;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import logic.ContentsSort.SortColumn;
import org.junit.Before;
import org.junit.Test;
import valueobject.FileContent;

/**
 * @author machupicchubeta
 */
public class FileInputTest {
    private FileInput fileInput;
    private static final String TEST_INPUT_FILE_NAME = "/Users/machupicchubeta/dev/Labo/FileContentsSort/test/logic/InputTestFile";
    @Before
    public void setUp() throws Exception {
        fileInput = new FileInput(TEST_INPUT_FILE_NAME);
    }
    /**
     * Test method for {@link logic.FileInput#readFile()}.
     */
    @Test
    public final void testReadFile() throws Exception {
        final FileContent actual = fileInput.readFile();
        assertThat(actual, notNullValue());
        assertThat(actual.getFileContent().get(new Integer(1)), is("山田太郎\t男性\t21"));
        assertThat(actual.getFileContent().get(new Integer(2)), is("山田 花子\t女性\t75"));
        assertThat(actual.getFileContent().get(new Integer(3)), is("Smith, John\t男性\t38"));
        assertThat(actual.getFileContent().get(new Integer(4)), is("てすと1\t男性\t10\t"));
        assertThat(actual.getFileContent().get(new Integer(5)), is("てすと2\t男性\t11 "));
        assertThat(actual.getFileContent().get(new Integer(6)), is("てすと3\t男性\t12\t "));
        assertThat(actual.getColumnContent(SortColumn.FIRST).getRowContent(4), is("てすと1"));
        assertThat(actual.getColumnContent(SortColumn.SECOND).getRowContent(4), is("男性"));
        assertThat(actual.getColumnContent(SortColumn.THIRD).getRowContent(4), is("10"));
        assertThat(actual.getColumnContent(SortColumn.FIRST).getRowContent(5), is("てすと2"));
        assertThat(actual.getColumnContent(SortColumn.SECOND).getRowContent(5), is("男性"));
        assertThat(actual.getColumnContent(SortColumn.THIRD).getRowContent(5), is("11"));
        assertThat(actual.getColumnContent(SortColumn.FIRST).getRowContent(6), is("てすと3"));
        assertThat(actual.getColumnContent(SortColumn.SECOND).getRowContent(6), is("男性"));
        assertThat(actual.getColumnContent(SortColumn.THIRD).getRowContent(6), is("12"));
    }
}
