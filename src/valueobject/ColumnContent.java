/**
 * 
 */
package valueobject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author machupicchubeta
 */
public class ColumnContent {
    private final Map<Integer, String> columnContents;
    public ColumnContent(final int columnNumber) {
        columnContents = new LinkedHashMap<Integer, String>();
    }
    public ColumnContent addContent(final int rowNumber, final String content) {
        columnContents.put(new Integer(rowNumber), content);
        return this;
    }
    public Map<Integer, String> getColumnContent() {
        return columnContents;
    }
    public String getRowContent(final int rowNumber) {
        return columnContents.get(new Integer(rowNumber));
    }
}
