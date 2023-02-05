package bo.response.meetinginfo;

import bo.enums.BBBRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Attendee {

    private String userID;

    private String fullName;

    private BBBRole role;

    private Boolean isPresenter;

    private Boolean isListeningOnly;

    private Boolean hasJoinedVoice;

    private Boolean hasVideo;

    private String clientType;

    private String customdata;

}
