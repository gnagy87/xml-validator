import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLParser {
    public Document xmlDocumentMaker(File xmlFile) throws Exception {
        try {
            DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = parser.parse(xmlFile);
            return document;
        } catch (SAXException exc) {
            throw new Exception(exc.getMessage());
        }
    }
}
