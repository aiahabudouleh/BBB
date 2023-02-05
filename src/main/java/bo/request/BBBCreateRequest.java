package bo.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;

public interface BBBCreateRequest {

    SortedMap<String, Object> getRequestUrlAsMap();

    default void putIfNotNull (SortedMap<String, Object> mp, String filed, Object val) {
        if(val != null) {
            mp.put(filed, val);
        }
    }

    default String encodeValue(Object value) {
        try {
            return value == null || value.toString().isEmpty() ? null : URLEncoder.encode(value.toString(), StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
    }
}
