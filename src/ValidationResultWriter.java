import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

public class ValidationResultWriter {
    public void validationLogProvider(String path, List<String> validationResult) throws Exception {
        try {
            PrintWriter writer = new PrintWriter(path + fileNameProvider(), "UTF-8");
            fileContentWriter(validationResult, writer);
        }
        catch (Exception exc){
            throw new Exception(exc.getMessage());
        }
    }

    private String fileNameProvider() {
        return (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() + ".txt");
    }

    private void fileContentWriter(List<String> validationResult, PrintWriter writer) {
        validationResult.forEach(
                s -> {
                    writer.println(s);
                }
        );
        writer.close();
    }
}
