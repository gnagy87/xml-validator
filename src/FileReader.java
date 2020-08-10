import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReader {
    public File fileReader(String path, String fileName) throws Exception {
        try {
            return new File(path + fileName);
        } catch (Exception exc) {
            throw new Exception(exc.getMessage());
        }
    }

    public List<File> xmlCollector(String path) {
        File folder = new File(path);
        List<File> xmlFiles = xsdRemover(Arrays.asList(folder.listFiles()));
        return xmlFiles;
    }

    private List<File> xsdRemover(List<File> filesFromFolder) {
        List<File> xmlFiles = new ArrayList<>();
        for (int i = 0; i < filesFromFolder.size(); i++) {
            if (filesFromFolder.get(i).getName().endsWith(".xml")) {
               xmlFiles.add(filesFromFolder.get(i));
            }
        }
        return xmlFiles;
    }
}
