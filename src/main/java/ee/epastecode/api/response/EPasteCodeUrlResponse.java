package ee.epastecode.api.response;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EPasteCodeUrlResponse {
    private final String url;
}
