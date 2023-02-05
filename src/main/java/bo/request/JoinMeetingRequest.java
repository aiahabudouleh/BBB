package bo.request;

import bo.enums.BBBRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.SortedMap;
import java.util.TreeMap;

import static bo.enums.BBBRole.VIEWER;


@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JoinMeetingRequest implements BBBCreateRequest {

    //required
    private String fullName;

    //required
    private String meetingId;

    //required
    private String password;

    private String createTime;

    private String userId;

    private String webVoiceConf;

    private String configToken;

    private String defaultLayout;

    private String avatarURL;

    private String redirect;

    private String clientURL;

    private String guest;

    private BBBRole bbbRole = VIEWER;

    private String excludeFromDashboard;

    //Style customization params
    private String cssUrlStyle;

    private Boolean askForFeedBack = true;

    private String title = "Tantetobi";

    @Override
    public SortedMap<String, Object> getRequestUrlAsMap() {
        SortedMap<String, Object> mp = new TreeMap<>();
        putIfNotNull(mp, "fullName", encodeValue(fullName));
        putIfNotNull(mp, "meetingID", meetingId);
        putIfNotNull(mp, "password", password);
        putIfNotNull(mp, "createTime", createTime);
        putIfNotNull(mp, "userID", userId);
        putIfNotNull(mp, "webVoiceConf", webVoiceConf);
        putIfNotNull(mp, "configToken", configToken);
        putIfNotNull(mp, "defaultLayout", defaultLayout);
        putIfNotNull(mp, "avatarURL", encodeValue(avatarURL));
        putIfNotNull(mp, "redirect", redirect);
        putIfNotNull(mp, "clientURL", encodeValue(clientURL));
        putIfNotNull(mp, "guest", guest);
        putIfNotNull(mp, "role", bbbRole.getName());
        putIfNotNull(mp, "userdata-bbb_custom_style_url", encodeValue(cssUrlStyle));
        putIfNotNull(mp, "userdata-bbb_ask_for_feedback_on_logout", askForFeedBack);
        putIfNotNull(mp, "userdata-bbb_client_title", askForFeedBack);

        return mp;
    }
}
