package ee.epastecode.repository;

import ee.epastecode.api.domain.EPasteCodeEntity;

import java.util.List;

public interface EPasteCodeRepository {
    EPasteCodeEntity getByHash(String hash);
    List<EPasteCodeEntity> getListOfPublicAndAlive(int amount);
    void add(EPasteCodeEntity pasteCodeEntity);
}
