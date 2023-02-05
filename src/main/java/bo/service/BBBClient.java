package bo.service;

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

import java.util.List;

public interface BBBClient {

    CreateMeetingResponse sendCreateMeetingRequest(CreateMeetingRequest createMeetingRequest);

    CreateMeetingResponse sendCreateMeetingRequest(CreateMeetingRequest createMeetingRequest, List<PreUploadedSlide> slides);

    String sendJoinMeetingRequest(JoinMeetingRequest joinMeetingRequest);

    IsMeetingRunningResponse sendIsMeetingRunningRequest(IsMeetingRunningRequest isMeetingRunningRequest);

    GetMeetingInfoResponse sendGetMeetingInfoRequest(GetMeetingInfoRequest getMeetingInfoRequest);

    EndMeetingResponse sendEndMeetingRequest(EndMeetingRequest endMeetingRequest);

    GetMeetingsResponse sendGetMeetings();

    GetRecordingsResponse getRecordings(GetRecordingsRequest request);

    DeleteRecordingResponse deleteRecording(DeleteRecordingRequest request);

    PublishRecordingResponse publishRecords(PublishRecordingRequest request);
}
