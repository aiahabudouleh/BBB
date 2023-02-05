package bo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateMeetingResponse implements BaseBBBResponse {

    private String modules;

    private String returncode;

    private String message;

    private String messageKey;

    private String meetingID;

    private String internalMeetingID;

    private String parentMeetingID;

    private String attendeePW;

    private String moderatorPW;

    private String createTime;

    private String voiceBridge;

    private String dialNumber;

    private String createDate;

    private Boolean hasUserJoined;

    private Long duration;

    private Boolean hasBeenForciblyEnded;

    private List<BBBErrorResponse> errors;
}
