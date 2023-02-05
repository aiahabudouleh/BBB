package bo.response.recording.get;

import bo.response.BaseBBBResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class Recording implements BaseBBBResponse {

    private String recordID;    //sending recordId will ignore the meetingId from BBB. I recommend sending meetingId only.

    private String meetingID;

    private String internalMeetingID;

    private String name;

    private Boolean isBreakout;

    private String published;

    private String state;

    private String startTime;

    private String endTime;

    private Integer participants;

    private Meta metadata;

    private Playback playback;

    private String rawSize;

    private String size;

    private String data;

}
