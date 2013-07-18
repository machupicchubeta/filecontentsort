/**
 * 
 */
package valueobject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import logic.ContentsSort.SortColumn;
import exception.FileContentException;

/**
 * @author machupicchubeta
 */
public class FileContent {
    public static FileContent create(final String filename) throws FileContentException {
        final FileContent fileContent = new FileContent();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String line;
        int rowNumber = 0;
        try {
            fileReader = new FileReader(filename);
            bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.addContent(++rowNumber, line);
            }
        } catch (final FileNotFoundException exception) {
            throw new FileContentException("file not found error", exception);
        } catch (final IOException exception) {
            throw new FileContentException("io error", exception);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (final IOException exception) {
                    // do nothing
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (final IOException exception) {
                    // do nothing
                }
            }
        }
        return fileContent;
    }
    private final Map<Integer, String> fileContents;
    private final Map<Integer, ColumnContent> columnContents;
    public static final String DELIMITER = "\t";
    public FileContent() {
        fileContents = new LinkedHashMap<Integer, String>();
        columnContents = new HashMap<Integer, ColumnContent>();
        columnContents.put(SortColumn.FIRST.getValue(), new ColumnContent(SortColumn.FIRST.getValue().intValue()));
        columnContents.put(SortColumn.SECOND.getValue(), new ColumnContent(SortColumn.SECOND.getValue().intValue()));
        columnContents.put(SortColumn.THIRD.getValue(), new ColumnContent(SortColumn.THIRD.getValue().intValue()));
    }
    public FileContent addContent(final int rowNumber, final String lineContent) {
        fileContents.put(new Integer(rowNumber), lineContent);
        String[] contentsByEachColumn;
        contentsByEachColumn = lineContent.split("\\t");
        getColumnContent(SortColumn.FIRST).addContent(rowNumber, contentsByEachColumn[0]);
        getColumnContent(SortColumn.SECOND).addContent(rowNumber, contentsByEachColumn[1]);
        getColumnContent(SortColumn.THIRD).addContent(rowNumber, contentsByEachColumn[2].trim());
        return this;
    }
    public ColumnContent getColumnContent(final SortColumn sortColumn) throws FileContentException {
        return columnContents.get(sortColumn.getValue());
    }
    public Map<Integer, String> getFileContent() {
        return fileContents;
    }
    public String getRowContent(final int rowNumber) {
        return fileContents.get(new Integer(rowNumber));
    }
    public void output(final String filename) throws FileContentException {
        final File outputFile = new File(filename);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        PrintWriter printWriter = null;
        if (outputFile.exists() && (!outputFile.isFile() && !outputFile.canWrite())) {
            throw new FileContentException("file write error (file already exists and can not write, permission error)");
        }
        try {
            fileWriter = new FileWriter(outputFile);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
            for (final String lineContent : fileContents.values()) {
                printWriter.println(lineContent);
            }
            printWriter.flush();
        } catch (final IOException exception) {
            throw new FileContentException("file write error (can not flush buffer)");
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (final IOException exception) {
                    // do nothing
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (final IOException exception) {
                    // do nothing
                }
            }
        }
    }
}
