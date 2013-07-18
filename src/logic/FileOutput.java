/**
 * 
 */
package logic;

import valueobject.FileContent;

/**
 * @author machupicchubeta
 */
public class FileOutput {
    private final String filename;
    private final FileContent fileContent;
    public FileOutput(final String filename, final FileContent fileContent) {
        this.filename = filename;
        this.fileContent = fileContent;
    }
    public void writeFile() {
        fileContent.output(filename);
    }
}
