package bo.service;

import bo.request.create.PreUploadedSlide;
import exceptions.PreUploadedSlideProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

@Slf4j
@Service
public class PreUploadedSlideServiceImpl implements PreUploadedSlideService {

    private final DocumentBuilderFactory factory;
    public PreUploadedSlideServiceImpl() {
        this.factory =  DocumentBuilderFactory.newInstance();
    }

    public String buildPreUploadedSlidesXml(List<PreUploadedSlide> slides) {
        StringBuilder payLoad = new StringBuilder();
        payLoad.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<modules>\n"
                + " <module name=\"presentation\">");

        for (PreUploadedSlide slide : slides) {
            payLoad.append(" <document url=\""+ slide.getFileUrl() + "\" filename=\""+ slide.getFileName() + "\"/>\n" +
                    "      <document name=\"" + slide.getFileName() +"\">" +  encodeFileToBase64Binary(slide.getFilePath()) + "\n" +
                    "      </document> \n");
        }
        payLoad.append("   </module>\n" +
                "</modules>");
        log.info("Create meeting request bode: " + payLoad);
        return payLoad.toString();
    }

    private String encodeFileToBase64Binary(String filePath) {
        File file = new File(filePath);
        confirmFileCriteria(file);
        try {
            byte[] encoded = Base64.encodeBase64(Files.readAllBytes(file.toPath()));
            return new String(encoded, StandardCharsets.US_ASCII);
        } catch (IOException e) {
            throw new PreUploadedSlideProcessingException("Failed to encode file content duo to IOException. \n" + e.getMessage());
        }
    }

    private void confirmFileCriteria(File file) {
        if (!file.exists()) {
            throw new PreUploadedSlideProcessingException("Failed to find file " + file.getName());
        }
        if (file.length() >= 2000000) {
            throw new MaxUploadSizeExceededException(2000000);
        }
    }

}
