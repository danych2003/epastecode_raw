package ee.epastecode.api.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EPasteCodeEntity {
    private int id;
    private String data;
    private String hash;
    private LocalDateTime lifetime;
    private boolean isPublic;
}
