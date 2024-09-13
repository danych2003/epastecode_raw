package ee.epastecode.repository;

import ee.epastecode.api.domain.EPasteCodeEntity;
import ee.epastecode.exception.NotFoundEntityException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class EPastCodeRepositoryMap implements EPasteCodeRepository{

    private final Map<String, EPasteCodeEntity> storage = new ConcurrentHashMap<>();

    @Override
    public EPasteCodeEntity getByHash(String hash) {
        EPasteCodeEntity ePasteCodeEntity = storage.get(hash);

        if(ePasteCodeEntity == null) {
            throw new NotFoundEntityException("EPasteCode not found with hash=" + hash);
        }
        return ePasteCodeEntity;
    }

    @Override
    public List<EPasteCodeEntity> getListOfPublicAndAlive(int amount) {
        LocalDateTime now = LocalDateTime.now();
        return storage.values().stream()
                .filter(EPasteCodeEntity::isPublic)
                .filter(pasteCodeEntity -> pasteCodeEntity.getLifetime().isAfter(now))
                .sorted(Comparator.comparing(EPasteCodeEntity::getId).reversed())
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    public void add(EPasteCodeEntity pasteCodeEntity) {

        storage.put(pasteCodeEntity.getHash(), pasteCodeEntity);
    }
}
