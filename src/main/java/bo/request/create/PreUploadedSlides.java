package bo.request.create;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Optional;

@Slf4j
public class PreUploadedSlides {

    private final static String FILE_NAME = "filename.pdf";
    private final static String FILE_URL = "http://fileUrl.com/sample.pdf";

    private static final String preUploadedFormat = "<modules>\n" +
            "   <module name=\"presentation\">\n" +
            "      <document url=\"" + FILE_URL + "\" filename=\"" + FILE_NAME + "\"/>\n" +
            "      </document>\n" +
            "   </module>\n" +
            "</modules>";
    private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    public static Optional<Document> buildPreUploadedSlidesXml(String documentUrl, String documentName) {
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            preUploadedFormat.replaceFirst(FILE_URL, documentUrl);
            preUploadedFormat.replaceFirst(FILE_NAME, documentName);
            return Optional.ofNullable(builder.parse(preUploadedFormat));
        } catch (ParserConfigurationException e) {
            log.error("Could not create new document builder for pre-uploaded slides!\n" + e.getMessage());
        } catch (IOException e) {
            log.error("Could not read pre-uploaded slides to document builder!\n" + e.getMessage());
        } catch (SAXException e) {
            log.error("Could not parse pre-uploaded slides! \n" + e.getMessage());
        }
        return Optional.empty();
    }

}
