package ee.epastecode.service;

import ee.epastecode.api.request.EPasteCodeRequest;
import ee.epastecode.api.response.EPasteCodeUrlResponse;
import ee.epastecode.api.response.EPasteCodeResponse;

import java.util.List;

public interface EPasteCodeService {
    EPasteCodeResponse getByHash(String hash);
    List<EPasteCodeResponse> getFirstPublicEPasteCode();
    EPasteCodeUrlResponse create(EPasteCodeRequest request);
}
