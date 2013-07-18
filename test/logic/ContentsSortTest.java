/**
 * 
 */
package logic;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import valueobject.FileContent;

/**
 * @author machupicchubeta
 */
public class ContentsSortTest {
    private FileContent fileContent;
    private static final String TEST_CONTENT_1 = "ああ\t男性\t1";
    private static final String TEST_CONTENT_2 = "ああ\t女性\t1";
    private static final String TEST_CONTENT_3 = "ああ\t男性\t2";
    private static final String TEST_CONTENT_4 = "ああ\t女性\t2";
    private static final String TEST_CONTENT_5 = "あい\t男性\t1";
    private static final String TEST_CONTENT_6 = "あい\t女性\t1";
    private static final String TEST_CONTENT_7 = "あ い\t男性\t1";
    private static final String TEST_CONTENT_8 = "あ い\t女性\t1";
    @Before
    public final void setUp() throws Exception {
        fileContent = new FileContent();
        int rowNumber = 0;
        fileContent.addContent(++rowNumber, TEST_CONTENT_1);
        fileContent.addContent(++rowNumber, TEST_CONTENT_2);
        fileContent.addContent(++rowNumber, TEST_CONTENT_3);
        fileContent.addContent(++rowNumber, TEST_CONTENT_4);
        fileContent.addContent(++rowNumber, TEST_CONTENT_5);
        fileContent.addContent(++rowNumber, TEST_CONTENT_6);
        fileContent.addContent(++rowNumber, TEST_CONTENT_7);
        fileContent.addContent(++rowNumber, TEST_CONTENT_8);
    }
    /**
     * Test method for {@link logic.ContentsSort#sort()}.
     */
    @Test
    public final void testSort() throws Exception {
        // pattern 1
        final FileContent actual1 = new ContentsSort(fileContent, ContentsSort.SortColumn.FIRST, ContentsSort.SortOrder.ASC).sort();
        final Collection<String> actualContent1 = actual1.getFileContent().values();
        String[] actualContentArray1 = new String[10];
        actualContentArray1 = actualContent1.toArray(actualContentArray1);
        assertThat(actualContentArray1[0], is(TEST_CONTENT_7));
        assertThat(actualContentArray1[1], is(TEST_CONTENT_8));
        assertThat(actualContentArray1[2], is(TEST_CONTENT_1));
        assertThat(actualContentArray1[3], is(TEST_CONTENT_2));
        assertThat(actualContentArray1[4], is(TEST_CONTENT_3));
        assertThat(actualContentArray1[5], is(TEST_CONTENT_4));
        assertThat(actualContentArray1[6], is(TEST_CONTENT_5));
        assertThat(actualContentArray1[7], is(TEST_CONTENT_6));
        final FileContent actual2 = new ContentsSort(fileContent, ContentsSort.SortColumn.FIRST, ContentsSort.SortOrder.DESC).sort();
        final Collection<String> actualContent2 = actual2.getFileContent().values();
        String[] actualContentArray2 = new String[10];
        actualContentArray2 = actualContent2.toArray(actualContentArray2);
        assertThat(actualContentArray2[0], is(TEST_CONTENT_5));
        assertThat(actualContentArray2[1], is(TEST_CONTENT_6));
        assertThat(actualContentArray2[2], is(TEST_CONTENT_1));
        assertThat(actualContentArray2[3], is(TEST_CONTENT_2));
        assertThat(actualContentArray2[4], is(TEST_CONTENT_3));
        assertThat(actualContentArray2[5], is(TEST_CONTENT_4));
        assertThat(actualContentArray2[6], is(TEST_CONTENT_7));
        assertThat(actualContentArray2[7], is(TEST_CONTENT_8));
        // pattern 3
        final FileContent actual3 = new ContentsSort(fileContent, ContentsSort.SortColumn.SECOND, ContentsSort.SortOrder.ASC).sort();
        final Collection<String> actualContent3 = actual3.getFileContent().values();
        String[] actualContentArray3 = new String[10];
        actualContentArray3 = actualContent3.toArray(actualContentArray3);
        assertThat(actualContentArray3[0], is(TEST_CONTENT_1));
        assertThat(actualContentArray3[1], is(TEST_CONTENT_3));
        assertThat(actualContentArray3[2], is(TEST_CONTENT_5));
        assertThat(actualContentArray3[3], is(TEST_CONTENT_7));
        assertThat(actualContentArray3[4], is(TEST_CONTENT_2));
        assertThat(actualContentArray3[5], is(TEST_CONTENT_4));
        assertThat(actualContentArray3[6], is(TEST_CONTENT_6));
        assertThat(actualContentArray3[7], is(TEST_CONTENT_8));
        // pattern 4
        final FileContent actual4 = new ContentsSort(fileContent, ContentsSort.SortColumn.SECOND, ContentsSort.SortOrder.DESC).sort();
        final Collection<String> actualContent4 = actual4.getFileContent().values();
        String[] actualContentArray4 = new String[10];
        actualContentArray4 = actualContent4.toArray(actualContentArray4);
        assertThat(actualContentArray4[0], is(TEST_CONTENT_2));
        assertThat(actualContentArray4[1], is(TEST_CONTENT_4));
        assertThat(actualContentArray4[2], is(TEST_CONTENT_6));
        assertThat(actualContentArray4[3], is(TEST_CONTENT_8));
        assertThat(actualContentArray4[4], is(TEST_CONTENT_1));
        assertThat(actualContentArray4[5], is(TEST_CONTENT_3));
        assertThat(actualContentArray4[6], is(TEST_CONTENT_5));
        assertThat(actualContentArray4[7], is(TEST_CONTENT_7));
        // pattern 5
        final FileContent actual5 = new ContentsSort(fileContent, ContentsSort.SortColumn.THIRD, ContentsSort.SortOrder.ASC).sort();
        final Collection<String> actualContent5 = actual5.getFileContent().values();
        String[] actualContentArray5 = new String[10];
        actualContentArray5 = actualContent5.toArray(actualContentArray5);
        assertThat(actualContentArray5[0], is(TEST_CONTENT_1));
        assertThat(actualContentArray5[1], is(TEST_CONTENT_2));
        assertThat(actualContentArray5[2], is(TEST_CONTENT_5));
        assertThat(actualContentArray5[3], is(TEST_CONTENT_6));
        assertThat(actualContentArray5[4], is(TEST_CONTENT_7));
        assertThat(actualContentArray5[5], is(TEST_CONTENT_8));
        assertThat(actualContentArray5[6], is(TEST_CONTENT_3));
        assertThat(actualContentArray5[7], is(TEST_CONTENT_4));
        // pattern 6
        final FileContent actual6 = new ContentsSort(fileContent, ContentsSort.SortColumn.THIRD, ContentsSort.SortOrder.DESC).sort();
        final Collection<String> actualContent6 = actual6.getFileContent().values();
        String[] actualContentArray6 = new String[10];
        actualContentArray6 = actualContent6.toArray(actualContentArray6);
        assertThat(actualContentArray6[0], is(TEST_CONTENT_3));
        assertThat(actualContentArray6[1], is(TEST_CONTENT_4));
        assertThat(actualContentArray6[2], is(TEST_CONTENT_1));
        assertThat(actualContentArray6[3], is(TEST_CONTENT_2));
        assertThat(actualContentArray6[4], is(TEST_CONTENT_5));
        assertThat(actualContentArray6[5], is(TEST_CONTENT_6));
        assertThat(actualContentArray6[6], is(TEST_CONTENT_7));
        assertThat(actualContentArray6[7], is(TEST_CONTENT_8));
    }
}
