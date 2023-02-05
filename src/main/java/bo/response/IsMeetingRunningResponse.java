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
public class IsMeetingRunningResponse implements BaseBBBResponse {

    private String returncode;

    private Boolean running;

    private String message;

    private String messageKey;

    private List<BBBErrorResponse> errors;

}
