import bo.request.DeleteRecordingRequest;
import bo.request.EndMeetingRequest;
import bo.request.GetMeetingInfoRequest;
import bo.request.GetRecordingsRequest;
import bo.request.IsMeetingRunningRequest;
import bo.request.JoinMeetingRequest;
import bo.request.create.CreateMeetingRequest;
import bo.response.CreateMeetingResponse;
import bo.response.EndMeetingResponse;
import bo.response.GetMeetingsResponse;
import bo.response.IsMeetingRunningResponse;
import bo.response.meetinginfo.GetMeetingInfoResponse;
import bo.response.recording.delete.DeleteRecordingResponse;
import bo.response.recording.get.GetRecordingsResponse;
import bo.service.BBBClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
* This controller to help us in debugging BBB integration issues.
* */
@Slf4j
@RestController
@RequestMapping("/api/v1/bbb")
public class BBBController {

    private final BBBClient bbbClient;
    public BBBController(BBBClient bbbClient) {
        this.bbbClient = bbbClient;
    }

    @PostMapping("/meetings/create")
    public ResponseEntity<CreateMeetingResponse> createMeeting(@RequestBody CreateMeetingRequest request) {
        return new ResponseEntity<>(bbbClient.sendCreateMeetingRequest(request), HttpStatus.OK);
    }

    @PostMapping("/meetings/create-with-slides")
    public ResponseEntity<CreateMeetingResponse> createMeetingHavingPreUploadedSlides(@RequestBody CreateMeetingRequest request) {
        return new ResponseEntity<>(bbbClient.sendCreateMeetingRequest(request, request.getSlides()), HttpStatus.OK);
    }

    @PostMapping("/meetings/join")
    @ResponseBody
    public String joinMeeting(@RequestBody JoinMeetingRequest request) {
        return  bbbClient.sendJoinMeetingRequest(request);
    }

    @GetMapping("/meetings/is-running/{meetingId}")
    public ResponseEntity<IsMeetingRunningResponse> isMeetingRunning(@PathVariable String meetingId)  {
        return new ResponseEntity<>(bbbClient.sendIsMeetingRunningRequest(IsMeetingRunningRequest.builder().meetingId(meetingId).build()),
                HttpStatus.OK);
    }

    @GetMapping("/meetings/info/{meetingId}")
    public ResponseEntity<GetMeetingInfoResponse> getMeetingInfo(@PathVariable String meetingId) {
        return new ResponseEntity<>(bbbClient.sendGetMeetingInfoRequest(GetMeetingInfoRequest.builder().meetingId(meetingId).build()), HttpStatus.OK);
    }

    @GetMapping("/meetings")
    public ResponseEntity<GetMeetingsResponse> getMeetings() {
        return new ResponseEntity<>(bbbClient.sendGetMeetings(), HttpStatus.OK);
    }

    @PostMapping("/meetings/end")
    public ResponseEntity<EndMeetingResponse> endMeeting(@RequestBody EndMeetingRequest request) {
        return new ResponseEntity<>(bbbClient.sendEndMeetingRequest(request), HttpStatus.OK);
    }

    @GetMapping("/recordings")
    public ResponseEntity<GetRecordingsResponse> getRecordings(@RequestBody GetRecordingsRequest request) {
        return new ResponseEntity<>(bbbClient.getRecordings(request), HttpStatus.OK);
    }

    @DeleteMapping("/recordings/{internalMeetingId}")
    public ResponseEntity<DeleteRecordingResponse>  deleteRecording(@PathVariable String internalMeetingId) {
        return new ResponseEntity<>(bbbClient.deleteRecording(DeleteRecordingRequest.builder().recordId(internalMeetingId).build()), HttpStatus.OK);
    }
}
