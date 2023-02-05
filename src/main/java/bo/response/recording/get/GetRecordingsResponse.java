package bo.response.recording.get;

import bo.response.BaseBBBResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetRecordingsResponse implements BaseBBBResponse {

    List<Recording> recordings;

    private String returncode;

    private String message;

    private String messageKey;
}
