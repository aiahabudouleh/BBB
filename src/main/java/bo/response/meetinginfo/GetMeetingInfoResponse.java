package bo.response.meetinginfo;

import bo.response.BaseBBBResponse;
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
public class GetMeetingInfoResponse implements BaseBBBResponse {

    private String returncode;

    private String meetingName;

    private String meetingID;

    private String breakout;

    private String parentMeetingID;

    private String sequence;

    private Boolean freeJoin;

    private String internalMeetingID;

    private String createTime;

    private String createDate;

    private String voiceBridge;

    private String dialNumber;

    private String attendeePW;

    private String moderatorPW;

    private Boolean running;

    private Long duration;

    private Boolean hasUserJoined;

    private Boolean recording;

    private Boolean hasBeenForciblyEnded;

    private String startTime;

    private String endTime;

    private Integer participantCount;

    private Integer listenerCount;

    private Integer voiceParticipantCount;

    private Integer videoCount;

    private Integer maxUsers;

    private Integer moderatorCount;

    private List<Attendee> attendees;

    private List<String> breakoutRooms;

    private String message;

    private String messageKey;

    private String metadata;

    private Long metaWorkshopId;

    private Long metaInstructorId;

    private Long metaTopicId;

    private String metaEdCallbackUrl;

    //URLEncoded
    private String metabbbRecordingReadyUrl;

    private Boolean isBreakout;

}
