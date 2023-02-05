package bo.service;

import bo.request.create.PreUploadedSlide;

import java.util.List;

public interface PreUploadedSlideService {

    public String buildPreUploadedSlidesXml(List<PreUploadedSlide> slides);
}
