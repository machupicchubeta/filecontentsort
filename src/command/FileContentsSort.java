/**
 * 
 */
package command;

import logic.ContentsSort;
import logic.FileInput;
import logic.FileOutput;
import valueobject.FileContent;
import exception.FileContentException;

/**
 * @author machupicchubeta
 */
public class FileContentsSort {
    static int createNumericByString(final String arg) throws IllegalArgumentException {
        if (arg == null) {
            return 1;
        }
        try {
            return Integer.parseInt(arg);
        } catch (final NumberFormatException exception) {
            throw new IllegalArgumentException(exception);
        }
    }
    static ContentsSort.SortColumn createSortColumnByString(final String arg) throws IllegalArgumentException {
        if (arg == null || "".equals(arg) || ContentsSort.SortColumn.FIRST.getValue().toString().equals(arg)) {
            return ContentsSort.SortColumn.FIRST;
        } else if (ContentsSort.SortColumn.SECOND.getValue().toString().equals(arg)) {
            return ContentsSort.SortColumn.SECOND;
        } else if (ContentsSort.SortColumn.THIRD.getValue().toString().equals(arg)) {
            return ContentsSort.SortColumn.THIRD;
        } else {
            throw new IllegalArgumentException("sort column must be positive number");
        }
    }
    static ContentsSort.SortOrder createSortOrderByString(final String arg) throws IllegalArgumentException {
        if (arg == null || "".equals(arg) || ContentsSort.SortOrder.ASC.toLowerString().equals(arg)) {
            return ContentsSort.SortOrder.ASC;
        } else if (ContentsSort.SortOrder.DESC.toLowerString().equals(arg)) {
            return ContentsSort.SortOrder.DESC;
        } else {
            throw new IllegalArgumentException("sort order must be asc or desc");
        }
    }
    /**
     * @param args
     */
    public static void main(final String... args) {
        // args0: (not null) input filename
        // args1: (not null) output filename
        if (args[0] == null || args[1] == null) {
            System.out.println(usage());
            System.out.println("input filename or output filename cannot null");
            System.exit(1);
        }
        final String inputFilename = args[0];
        final String outputFilename = args[1];
        // args2: sort column number
        ContentsSort.SortColumn sortColumn = null;
        try {
            if (args.length > 2) {
                sortColumn = createSortColumnByString(args[2]);
            } else {
                sortColumn = createSortColumnByString(null);
            }
        } catch (final IllegalArgumentException exception) {
            System.out.println(usage());
            System.out.println(exception.getMessage());
            System.exit(1);
        }
        // args3: sort order
        ContentsSort.SortOrder sortOrder = null;
        try {
            if (args.length > 3) {
                sortOrder = createSortOrderByString(args[3]);
            } else {
                sortOrder = createSortOrderByString(null);
            }
        } catch (final IllegalArgumentException exception) {
            System.out.println(usage());
            System.out.println(exception.getMessage());
            System.exit(1);
        }
        try {
            // 1. read input file
            final FileContent inputFileContent = new FileInput(inputFilename).readFile();
            // 2. sort file contents at sort column by sort order
            final FileContent outputFileContent = new ContentsSort(inputFileContent, sortColumn, sortOrder).sort();
            // 3. write output file
            new FileOutput(outputFilename, outputFileContent).writeFile();
        } catch (final FileContentException exception) {
            System.out.println(exception.getMessage());
            System.exit(1);
        }
        System.exit(0);
    }
    static String usage() {
        return "usage : java command.FileContentsSort 'input filename' 'output filename' 'sort column number' 'sort order (asc or desc)'";
    }
}
