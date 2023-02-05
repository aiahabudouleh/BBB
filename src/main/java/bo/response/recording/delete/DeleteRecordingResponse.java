package bo.response.recording.delete;

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
public class DeleteRecordingResponse implements BaseBBBResponse {

    private Response response;

    private String returncode;

    private String message;

    private String messageKey;

    private String deleted;
}
