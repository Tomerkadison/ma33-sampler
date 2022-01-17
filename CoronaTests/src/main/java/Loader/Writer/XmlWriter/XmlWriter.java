package Loader.Writer.XmlWriter;

import Loader.Writer.FileDataWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class XmlWriter extends FileDataWriter {
    private String recordName;
    private Document document;
    private Element root;

    public XmlWriter(String path, String recordName) {
        super(path);
        this.recordName = recordName;
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            this.document = documentBuilder.newDocument();
            this.root = this.document.createElement(this.recordName + 's');
            this.document.appendChild(this.root);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeRecords(List<HashMap<String, String>> records) {
        for (HashMap<String, String> record : records) {
            Element xmlRecord = this.document.createElement(this.recordName);
            this.root.appendChild(xmlRecord);
            Set<String> parameters = record.keySet();
            for (String parameter : parameters) {
                Element newParameter = this.document.createElement(parameter);
                newParameter.appendChild(document.createTextNode(record.get(parameter)));
                xmlRecord.appendChild(newParameter);
            }
        }
        writeToTheFile();
    }

    public void writeToTheFile() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(this.path));
            transformer.transform(domSource, streamResult);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String setType() {
        return "xml";
    }
}
