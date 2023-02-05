package bo.request.create;

import bo.request.BBBCreateRequest;
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
public class PublishRecordingRequest implements BBBCreateRequest {

    private String recordId;

    private Boolean publish;

    @Override
    public SortedMap<String, Object> getRequestUrlAsMap() {
        SortedMap<String, Object> mp = new TreeMap<>();
        putIfNotNull(mp, "recordID", recordId);
        putIfNotNull(mp, "publish", publish);
        return mp;
    }
}
