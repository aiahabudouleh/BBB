package bo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.SortedMap;
import java.util.TreeMap;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EndMeetingRequest implements BBBCreateRequest {

    private String meetingId;

    private String password;

    @Override
    public SortedMap<String, Object> getRequestUrlAsMap() {
        SortedMap<String, Object> mp = new TreeMap<>();
        putIfNotNull(mp, "meetingID", meetingId);
        putIfNotNull(mp, "password", password);
        return mp;
    }
}
