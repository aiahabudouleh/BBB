package bo.request;

import bo.enums.RecordingState;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.SortedMap;
import java.util.TreeMap;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetRecordingsRequest implements BBBCreateRequest {

    private String meetingId;

    private String recordId;

    private RecordingState state;

    //filter the recordings returned
    private Long metaWorkshopId;

    private Long metaInstructorId;

    private Long metaTopicId;

    @Override
    public SortedMap<String, Object> getRequestUrlAsMap() {
        SortedMap<String, Object> mp = new TreeMap<>();
        putIfNotNull(mp, "meetingID", meetingId);
        putIfNotNull(mp, "recordID", recordId);
        putIfNotNull(mp, "state", state == null ? null : state.getState());
        putIfNotNull(mp, "meta_WorkshopId", metaWorkshopId);
        putIfNotNull(mp, "meta_InstructorId", metaInstructorId);
        putIfNotNull(mp, "meta_TopicId", metaTopicId);
        return mp;
    }
}
