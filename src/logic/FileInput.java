/**
 * 
 */
package logic;

import valueobject.FileContent;

/**
 * @author machupicchubeta
 */
public class FileInput {
    private final String filename;
    public FileInput(final String filename) {
        this.filename = filename;
    }
    public FileContent readFile() {
        return FileContent.create(filename);
    }
}
