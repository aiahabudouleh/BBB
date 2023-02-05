package bo.service;

import bo.request.BBBCreateRequest;
import bo.request.DeleteRecordingRequest;
import bo.request.EndMeetingRequest;
import bo.request.GetMeetingInfoRequest;
import bo.request.GetRecordingsRequest;
import bo.request.IsMeetingRunningRequest;
import bo.request.JoinMeetingRequest;
import bo.request.create.CreateMeetingRequest;
import bo.request.create.PreUploadedSlide;
import bo.request.create.PublishRecordingRequest;
import bo.response.CreateMeetingResponse;
import bo.response.EndMeetingResponse;
import bo.response.GetMeetingsResponse;
import bo.response.IsMeetingRunningResponse;
import bo.response.meetinginfo.GetMeetingInfoResponse;
import bo.response.recording.PublishRecordingResponse;
import bo.response.recording.delete.DeleteRecordingResponse;
import bo.response.recording.get.GetRecordingsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import exceptions.BigBlueButtonException;
import exceptions.XmlProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BBBClientImpl implements BBBClient {

    private static final String CREATE_MEETING = "create";
    private static final String JOIN_MEETING = "join";
    private static final String END_MEETING = "end";
    private static final String MEETING_INFO = "getMeetingInfo";
    private static final String IS_MEETING_RUNNING = "isMeetingRunning";
    private static final String GET_MEETINGS = "getMeetings";
    private static final String GET_RECORDINGS = "getRecordings";
    private static final String PUBLISH_RECORDINGS = "publishRecordings";
    private static final String DELETE_RECORDINGS = "deleteRecordings";

    private final String secret;
    private final String bbbUrl;
    private final RestTemplate restTemplate;
    private final XmlMapper xmlMapper = new XmlMapper();
    private final PreUploadedSlideService preUploadedSlideService;
    public BBBClientImpl(String secret,
                         String bbbUrl,
                         RestTemplate restTemplate,
                         PreUploadedSlideService preUploadedSlideService) {
        this.secret = secret;
        this.bbbUrl = bbbUrl;
        this.restTemplate = restTemplate;
        this.preUploadedSlideService = preUploadedSlideService;
    }

    public CreateMeetingResponse sendCreateMeetingRequest(CreateMeetingRequest createMeetingRequest) {
        String param = getCreateParam(createMeetingRequest);
        String responseAsString = wrapGetForEntity_log(getRequestUrl(CREATE_MEETING, param), String.class).getBody();
        return wrapReadValue_log(responseAsString, CreateMeetingResponse.class);
    }

    public CreateMeetingResponse sendCreateMeetingRequest(CreateMeetingRequest createMeetingRequest, List<PreUploadedSlide> slides) {
        String param = getCreateParam(createMeetingRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_XML));

        String preUploadedSlide = preUploadedSlideService.buildPreUploadedSlidesXml(slides);
        HttpEntity<String > entity = new HttpEntity<String>(preUploadedSlide, headers);
        String responseAsString = wrapExchange_log(getRequestUrl(CREATE_MEETING, param),
                                                        HttpMethod.POST,
                                                        entity,
                                                        String.class).getBody();
        return wrapReadValue_log(responseAsString, CreateMeetingResponse.class);
    }

    public String sendJoinMeetingRequest(JoinMeetingRequest joinMeetingRequest) {
        String param = getCreateParam(joinMeetingRequest);
        return getRequestUrl(JOIN_MEETING, param).toString();
    }

    public IsMeetingRunningResponse sendIsMeetingRunningRequest(IsMeetingRunningRequest isMeetingRunningRequest) {
        String param = getCreateParam(isMeetingRunningRequest);
        String responseAsString = wrapGetForEntity_log(getRequestUrl(IS_MEETING_RUNNING, param), String.class).getBody();
        return wrapReadValue_log(responseAsString, IsMeetingRunningResponse.class);
    }

    public GetMeetingInfoResponse sendGetMeetingInfoRequest(GetMeetingInfoRequest getMeetingInfoRequest) {
        String param = getCreateParam(getMeetingInfoRequest);
        String responseAsString = wrapGetForEntity_log(getRequestUrl(MEETING_INFO, param), String.class).getBody();
        return wrapReadValue_log(responseAsString, GetMeetingInfoResponse.class);
    }

    public EndMeetingResponse sendEndMeetingRequest(EndMeetingRequest endMeetingRequest) {
        String param = getCreateParam(endMeetingRequest);
        String responseAsString = wrapGetForEntity_log(getRequestUrl(END_MEETING, param), String.class).getBody();
        return wrapReadValue_log(responseAsString, EndMeetingResponse.class);
    }

    public GetMeetingsResponse sendGetMeetings() {
        String responseAsString = wrapGetForEntity_log(getRequestUrl(GET_MEETINGS, ""), String.class).getBody();
        return wrapReadValue_log(responseAsString, GetMeetingsResponse.class);
    }

    public GetRecordingsResponse getRecordings(GetRecordingsRequest request) {
        String params = getCreateParam(request);
        String responseAsString = wrapGetForEntity_log(getRequestUrl(GET_RECORDINGS, params), String.class).getBody();
        return wrapReadValue_log(responseAsString, GetRecordingsResponse.class);
    }

    public DeleteRecordingResponse deleteRecording(DeleteRecordingRequest request) {
        String params = getCreateParam(request);
        String responseAsString = wrapGetForEntity_log(getRequestUrl(DELETE_RECORDINGS, params), String.class).getBody();
        return wrapReadValue_log(responseAsString, DeleteRecordingResponse.class);
    }

    public PublishRecordingResponse publishRecords(PublishRecordingRequest request) {
        String params = getCreateParam(request);
        String responseAsString = wrapGetForEntity_log(getRequestUrl(PUBLISH_RECORDINGS, params), String.class).getBody();
        return wrapReadValue_log(responseAsString, PublishRecordingResponse.class);
    }

    private URI getRequestUrl(String api, String param) {
        String requestUrl = api + "?" + param + "&checksum=" + calculateBBBChecksum(api + param + secret);
        log.info("sending request: " + bbbUrl + requestUrl);
        return URI.create(bbbUrl + requestUrl);
    }

    private String getCreateParam(BBBCreateRequest request) {
        return request.getRequestUrlAsMap().entrySet().stream()
                .map(Object::toString)
                .collect(Collectors.joining("&"));
    }

    //Remember to send the request as callName + queryString + sharedSecret
    private String calculateBBBChecksum(String request) {
        return DigestUtils.sha1Hex(request);
    }

    private <T> ResponseEntity<T> wrapExchange_log(URI url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, Class<T> responseType) {
        try {
            return restTemplate.exchange(url, method, requestEntity, responseType);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new BigBlueButtonException(ex.getLocalizedMessage());
        }
    }

    private  <T> ResponseEntity<T> wrapGetForEntity_log(URI url, Class<T> responseType){
        try {
            return restTemplate.getForEntity(url, responseType);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new BigBlueButtonException(ex.getLocalizedMessage());
        }
    }

    private <T> T wrapReadValue_log(String content, Class<T> valueType) {
        try {
            return xmlMapper.readValue(content, valueType);
        } catch (JsonProcessingException ex) {
            log.error("Processing exception while deserializing response of type {" +  valueType + "}. \n" + content);
            log.error(ex.getMessage(), ex);
            throw new XmlProcessingException(ex.getLocalizedMessage());
        }
    }
}
