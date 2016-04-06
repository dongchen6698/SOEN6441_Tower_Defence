package TD.model;

import java.io.File;

/**
 * This is model for Map Chooser Module.
 * @author peilin
 */
public class FileChooser_Model {
    private String[] FileList;
    
    /**
      This is constructor method for Map Chooser. It will call Map Folder reader.
     */
    public FileChooser_Model(String path){
        listFilesForFolder(new File(path));
    }
    
    /**
     * This method will read all files form Folder and save it in String array
     * @param folder folder location.
     */
    public void listFilesForFolder(final File folder) {
        FileList = new String[folder.listFiles().length];
        int i=0;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
            } else {
                FileList[i] = fileEntry.getName();
                i++;
            }
        }
    }
    
    /**
     * this method will return Map File array.
     * @return map file array
     */
    public String[] getMapFileList(){
        return FileList;
    }
}
