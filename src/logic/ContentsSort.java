/**
 * 
 */
package logic;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import valueobject.ColumnContent;
import valueobject.FileContent;

/**
 * @author machupicchubeta
 */
public class ContentsSort {
    public enum SortColumn {
        FIRST(1),
        SECOND(2),
        THIRD(3);
        private Integer value;
        private SortColumn(final int value) {
            this.value = new Integer(value);
        }
        public Integer getValue() {
            return value;
        }
    }
    public enum SortOrder {
        ASC,
        DESC;
        public String toLowerString() {
            return name().toLowerCase();
        }
    }
    private final FileContent fileContent;
    private final SortOrder sortOrder;
    private final SortColumn sortColumn;
    public ContentsSort(final FileContent fileContent, final SortColumn sortColumn, final SortOrder sortOrder) {
        this.fileContent = fileContent;
        this.sortColumn = sortColumn;
        this.sortOrder = sortOrder;
    }
    public FileContent sort() {
        // sort contents by sort order at sort column number
        final List<Map.Entry<Integer, String>> contents = new LinkedList<Map.Entry<Integer, String>>(fileContent
                .getColumnContent(sortColumn)
                .getColumnContent()
                .entrySet());
        Collections.sort(contents, new Comparator() {
            @Override
            public int compare(final Object arg0, final Object arg1) {
                final Map.Entry<Integer, String> entry0 = (Map.Entry<Integer, String>) arg0;
                final Map.Entry<Integer, String> entry1 = (Map.Entry<Integer, String>) arg1;
                switch (sortColumn) {
                case FIRST:
                    // string characters
                    if (SortOrder.DESC == sortOrder) {
                        return entry1.getValue().compareTo(entry0.getValue());
                    } else {
                        return entry0.getValue().compareTo(entry1.getValue());
                    }
                case SECOND:
                    // limited string characters
                    if (entry0.getValue().equals(entry1.getValue())) {
                        return 0;
                    } else if ("男性".equals(entry0.getValue()) && "女性".equals(entry1.getValue())) {
                        if (SortOrder.DESC == sortOrder) {
                            return 1;
                        } else {
                            return -1;
                        }
                    } else if ("女性".equals(entry0.getValue()) && "男性".equals(entry1.getValue())) {
                        if (SortOrder.DESC == sortOrder) {
                            return -1;
                        } else {
                            return 1;
                        }
                    } else {
                        return 0;
                    }
                case THIRD:
                    // positive number
                    if (SortOrder.DESC == sortOrder) {
                        return new Integer(entry1.getValue()).compareTo(new Integer(entry0.getValue()));
                    } else {
                        return new Integer(entry0.getValue()).compareTo(new Integer(entry1.getValue()));
                    }
                default:
                    return 0;
                }
            }
        });
        final FileContent sortedFileContent = new FileContent();
        final ColumnContent firstColumnContent = fileContent.getColumnContent(SortColumn.FIRST);
        final ColumnContent secondColumnContent = fileContent.getColumnContent(SortColumn.SECOND);
        final ColumnContent thirdColumnContent = fileContent.getColumnContent(SortColumn.THIRD);
        final StringBuilder stringBuilder = new StringBuilder();
        int sortedRowNumber = 0;
        for (final Map.Entry<Integer, String> sortedColumnContent : contents) {
            final Integer originalRowNumber = sortedColumnContent.getKey();
            switch (sortColumn) {
            case FIRST:
                stringBuilder
                        .append(sortedColumnContent.getValue())
                        .append(FileContent.DELIMITER)
                        .append(secondColumnContent.getRowContent(originalRowNumber))
                        .append(FileContent.DELIMITER)
                        .append(thirdColumnContent.getRowContent(originalRowNumber));
                break;
            case SECOND:
                stringBuilder
                        .append(firstColumnContent.getRowContent(originalRowNumber))
                        .append(FileContent.DELIMITER)
                        .append(sortedColumnContent.getValue())
                        .append(FileContent.DELIMITER)
                        .append(thirdColumnContent.getRowContent(originalRowNumber));
                break;
            case THIRD:
                stringBuilder
                        .append(firstColumnContent.getRowContent(originalRowNumber))
                        .append(FileContent.DELIMITER)
                        .append(secondColumnContent.getRowContent(originalRowNumber))
                        .append(FileContent.DELIMITER)
                        .append(sortedColumnContent.getValue());
                break;
            }
            sortedFileContent.addContent(new Integer(++sortedRowNumber), stringBuilder.toString());
            stringBuilder.setLength(0);
        }
        return sortedFileContent;
    }
}
