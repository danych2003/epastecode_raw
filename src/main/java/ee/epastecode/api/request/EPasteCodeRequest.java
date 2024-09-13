package ee.epastecode.api.request;

import lombok.Data;

@Data
public class EPasteCodeRequest {
    private String data;
    private long expirationTimeSeconds;
    private EPublicStatus publicStatus;
}
