import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XMLValidator {
    public FileReader fileReader;
    public XMLParser xmlParser;
    public XSDParser xsdParser;
    public ValidationResultWriter validationResultWriter;
    public ParamValidator paramValidator;

    public XMLValidator() {
        this.fileReader = new FileReader();
        this.xmlParser = new XMLParser();
        this.xsdParser = new XSDParser();
        this.validationResultWriter = new ValidationResultWriter();
        this.paramValidator = new ParamValidator();
    }

    public static void main(String[] args) {
        XMLValidator xmlValidator = new XMLValidator();
        if (xmlValidator.argsValidator(args)) {
            try {
                String path = xmlValidator.directoryParamChecker(args[0]);
                String xsdFileName = args[1];
                HashMap<String, Document> xmlFiles = xmlValidator.getXMLFiles(path);
                Validator validator = xmlValidator.getValidatorFromXSD(path, xsdFileName);
                List<String> validationResult = xmlValidator.xmlValidator(xmlFiles, validator, path);
                xmlValidator.resultProducer(path, validationResult);
            } catch (Exception exc) {
                System.out.println(exc.getMessage());
            }
        } else {
            System.out.println("Incorrect params! Correct usage: java XMLValidator \"/xml-validator/xml-files/\" \"schema.xsd\" ");
        }
    }

    public List<String> xmlValidator(HashMap<String, Document> xmlFiles, Validator validator, String path) {
        List<String> validationResult = new ArrayList<>();
        for(String fileName : xmlFiles.keySet()) {
            try {
                validator.validate(new DOMSource(xmlFiles.get(fileName)));
                validationResult.add(fileName + "  ||  " + "XML file meets the criteria!");
            } catch (SAXException|IOException exc) {
                validationResult.add(fileName + "  ||  " + exc.getMessage());
            }
        }
        return validationResult;
    }

    public void resultProducer(String path, List<String> validationResult) throws Exception {
        validationResultWriter.validationLogProvider(path, validationResult);
    }

    public HashMap<String, Document> getXMLFiles(String path) throws Exception {
        HashMap<String, Document> xmlFiles = new HashMap<>();
        List<File> xmlList = fileReader.xmlCollector(path);
        for (int i = 0; i < xmlList.size(); i++) {
            try {
                Document xmlFile = xmlParser.xmlDocumentMaker(xmlList.get(i));
                xmlFiles.put(xmlList.get(i).getName(), xmlFile);
            } catch(Exception exc) {
                throw new Exception(exc.getMessage());
            }
        }
        return xmlFiles;
    }

    public Validator getValidatorFromXSD(String path, String fileName) throws Exception {
        Schema xsdFile = xsdParser.xsdSchemaMaker(fileReader.fileReader(path, fileName));
        Validator validator = xsdFile.newValidator();
        return validator;
    }

    public boolean argsValidator(String[] args) {
        if (args.length == 2 && paramValidator.xsdParamValidator(args[1])) {
            return true;
        }
        return false;
    }

    public String directoryParamChecker(String directory) {
        return paramValidator.directoryParamValidator(directory);
    }
}
