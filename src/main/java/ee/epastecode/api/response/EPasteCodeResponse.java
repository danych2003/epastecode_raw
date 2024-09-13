package ee.epastecode.api.response;

import ee.epastecode.api.request.EPublicStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EPasteCodeResponse {
    private final String data;
    private final boolean isPublic;
}
