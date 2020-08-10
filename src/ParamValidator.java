public class ParamValidator {
    public boolean xsdParamValidator(String xsd) {
        if (xsd.endsWith(".xsd")) {
            return true;
        }
        return false;
    }

    public String directoryParamValidator(String directory) {
        if (directory.endsWith("/")) {
            return directory;
        }
        return directory + "/";
    }
}
