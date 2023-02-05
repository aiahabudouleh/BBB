package bo.request.create;

import bo.enums.GuestPolicy;
import bo.enums.MeetingLayout;
import bo.request.BBBCreateRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateMeetingRequest implements BBBCreateRequest {

    private String name;

    private String meetingId;

    private String recordId;

    private String attendeePW;

    private String moderatorPW;

    private String welcome;

    private String dialNumber;

    private String voiceBridge;

    private Integer maxParticipants;

    private String logoutURL;

    private Boolean record;

    private Long duration = 9 * 60L;

    private Boolean isBreakout;

    //required for breakout room
    private String parentMeetingID;

    //required for breakout room
    private Long sequence;

    //Allow free join for breakout rooms
    private Boolean freeJoin = true;

    //Optional for breakout room
    private Boolean breakoutRoomsEnabled = true;

    //Optional for breakout room
    private Boolean breakoutRoomsPrivateChatEnabled = true;

    //Optional for breakout room
    private Boolean breakoutRoomsRecord = false;

    private Long metaWorkshopId;

    private Long metaInstructorId;

    private Long metaTopicId;

    private String metaEdCallbackUrl;

    //URLEncoded
    private String metabbbRecordingReadyUrl;

    private String moderatorOnlyMessage;

    private Boolean autoStartRecording = false;

    private Boolean allowStartStopRecording;

    private Boolean webcamsOnlyForModerator = false;

    private String logo;

    private String bannerText;

    private String bannerColor;

    private String copyright;

    private Boolean muteOnStart = false;

    private Boolean allowModsToUnmuteUsers;

    private Boolean lockSettingsDisableCam;

    private Boolean lockSettingsDisableMic;

    private Boolean lockSettingsDisablePrivateChat = false;

    private Boolean lockSettingsDisablePublicChat = false;

    private Boolean lockSettingsDisableNote = false;

    private Boolean lockSettingsLockedLayout;

    private Boolean lockSettingsLockOnJoin;

    private Boolean lockSettingsLockOnJoinConfigurable;

    private GuestPolicy guestPolicy = GuestPolicy.ASK_MODERATOR;

    private Boolean meetingKeepEvents;

    private Boolean endWhenNoModerator = false;

    private Integer endWhenNoModeratorDelayInMinutes;

    private MeetingLayout meetingLayout;

    private Boolean learningDashboardEnabled;

    private Integer learningDashboardCleanupDelayInMinutes;

    private Boolean allowModsToEjectCameras;

    private String recordingUrl;

    /** Request body **/
    List<PreUploadedSlide> slides;

    @Override
    public SortedMap<String, Object> getRequestUrlAsMap() {
        SortedMap<String, Object> mp = new TreeMap<>();
        if (breakoutRoomsEnabled != null && breakoutRoomsEnabled) {
            setBreakOutRequiredFields();
        }
        setRecordingIdIfRecordingEnabled();
        putIfNotNull(mp, "name", encodeValue(name));
        putIfNotNull(mp, "meetingID", meetingId);
        putIfNotNull(mp, "recordID", recordId);
        putIfNotNull(mp, "attendeePW", attendeePW);
        putIfNotNull(mp, "moderatorPW", moderatorPW);
        putIfNotNull(mp, "welcome", encodeValue(welcome));
        putIfNotNull(mp, "dialNumber", dialNumber);
        putIfNotNull(mp, "voiceBridge", voiceBridge);
        putIfNotNull(mp, "maxParticipants", maxParticipants);
        putIfNotNull(mp, "logoutURL", encodeValue(logoutURL));
        putIfNotNull(mp, "record", record);
        putIfNotNull(mp, "duration", duration);
        putIfNotNull(mp, "isBreakout", isBreakout);
        putIfNotNull(mp, "parentMeetingID", parentMeetingID);
        putIfNotNull(mp, "sequence", sequence);
        putIfNotNull(mp, "freeJoin", freeJoin);
        putIfNotNull(mp, "breakoutRoomsEnabled", breakoutRoomsEnabled);
        putIfNotNull(mp, "breakoutRoomsPrivateChatEnabled", breakoutRoomsPrivateChatEnabled);
        putIfNotNull(mp, "breakoutRoomsRecord", breakoutRoomsRecord);
        putIfNotNull(mp, "meta_WorkshopId", encodeValue(metaWorkshopId));
        putIfNotNull(mp, "meta_InstructorId", encodeValue(metaInstructorId));
        putIfNotNull(mp, "meta_EdCallbackUrl", encodeValue(metaEdCallbackUrl));
        putIfNotNull(mp, "meta_bbbRecordingReadyUrl", encodeValue(metabbbRecordingReadyUrl));
        putIfNotNull(mp, "meta_TopicId", encodeValue(metaTopicId));
        putIfNotNull(mp, "moderatorOnlyMessage", encodeValue(moderatorOnlyMessage));
        putIfNotNull(mp, "autoStartRecording", autoStartRecording);
        putIfNotNull(mp, "allowStartStopRecording", allowStartStopRecording);
        putIfNotNull(mp, "webcamsOnlyForModerator", webcamsOnlyForModerator);
        putIfNotNull(mp, "logo", encodeValue(logo));
        putIfNotNull(mp, "bannerText", encodeValue(bannerText));
        putIfNotNull(mp, "bannerColor", encodeValue(bannerColor));
        putIfNotNull(mp, "copyright", encodeValue(copyright));
        putIfNotNull(mp, "muteOnStart", muteOnStart);
        putIfNotNull(mp, "allowModsToUnmuteUsers", allowModsToUnmuteUsers);
        putIfNotNull(mp, "lockSettingsDisableCam", lockSettingsDisableCam);
        putIfNotNull(mp, "lockSettingsDisableMic", lockSettingsDisableMic);
        putIfNotNull(mp, "lockSettingsDisablePrivateChat", lockSettingsDisablePrivateChat);
        putIfNotNull(mp, "lockSettingsDisablePublicChat", lockSettingsDisablePublicChat);
        putIfNotNull(mp, "lockSettingsDisableNote", lockSettingsDisableNote);
        putIfNotNull(mp, "lockSettingsLockedLayout", lockSettingsLockedLayout);
        putIfNotNull(mp, "lockSettingsLockOnJoin", lockSettingsLockOnJoin);
        putIfNotNull(mp, "lockSettingsLockOnJoinConfigurable", lockSettingsLockOnJoinConfigurable);
        putIfNotNull(mp, "guestPolicy", guestPolicy);
        putIfNotNull(mp, "meetingKeepEvents", meetingKeepEvents);
        putIfNotNull(mp, "endWhenNoModerator", endWhenNoModerator);
        putIfNotNull(mp, "endWhenNoModeratorDelayInMinutes", endWhenNoModeratorDelayInMinutes);
        putIfNotNull(mp, "meetingLayout", meetingLayout == null ? null : meetingLayout.name());
        putIfNotNull(mp, "learningDashboardEnabled", learningDashboardEnabled);
        putIfNotNull(mp, "learningDashboardCleanupDelayInMinutes", learningDashboardCleanupDelayInMinutes);
        putIfNotNull(mp, "allowModsToEjectCameras", allowModsToEjectCameras);
        putIfNotNull(mp, "meta_bbb-recording-ready-url", encodeValue(recordingUrl));
        return mp;
    }

    private void setBreakOutRequiredFields() {
        parentMeetingID = parentMeetingID == null ? this.meetingId : parentMeetingID;
        sequence = sequence == null ? 1 : sequence + 1;
    }

    private void setRecordingIdIfRecordingEnabled() {
        if (record == true && recordId == null) {
            recordId = meetingId;
        }
    }
}
