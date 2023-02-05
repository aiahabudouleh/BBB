package bo.response;

import bo.response.meetinginfo.GetMeetingInfoResponse;
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
public class GetMeetingsResponse implements BaseBBBResponse {

    private String returncode;

    private String message;

    private String messageKey;

    private List<GetMeetingInfoResponse> meetings;

    private List<BBBErrorResponse> errors;

}
