package bo.response.recording.get;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Meta {

    private Boolean isBreakout;

    private String meetingName;

    @JacksonXmlProperty(localName = "gl-listed")
    private Boolean gl_listed;

    private String meetingId;

    @JacksonXmlProperty(localName = "bbbrecordingreadyurl")
    private String bbbRecordingReadyUrl;

    @JacksonXmlProperty(localName = "bbb-recording-ready-url")
    private String bbb_recording_ready_url;
}
