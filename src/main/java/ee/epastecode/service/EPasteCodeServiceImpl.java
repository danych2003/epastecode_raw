package ee.epastecode.service;

import ee.epastecode.api.domain.EPasteCodeEntity;
import ee.epastecode.api.request.EPasteCodeRequest;
import ee.epastecode.api.request.EPublicStatus;
import ee.epastecode.api.response.EPasteCodeResponse;
import ee.epastecode.api.response.EPasteCodeUrlResponse;
import ee.epastecode.repository.EPasteCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app")
public class EPasteCodeServiceImpl implements EPasteCodeService {

    private String host = "http://epastecode.danych.ee";
    private int publicListSize = 10;
    private final EPasteCodeRepository repository;

    private AtomicInteger idGenerator = new AtomicInteger(0);

    @Override
    public EPasteCodeResponse getByHash(String hash) {
        EPasteCodeEntity pasteCodeEntity = repository.getByHash(hash);
        return new EPasteCodeResponse(pasteCodeEntity.getData(), pasteCodeEntity.isPublic());
    }

    @Override
    public List<EPasteCodeResponse> getFirstPublicEPasteCode() {
        List<EPasteCodeEntity> list = repository.getListOfPublicAndAlive(publicListSize);

        return list.stream().map(pasteCodeEntity ->
                new EPasteCodeResponse(pasteCodeEntity.getData(), pasteCodeEntity.isPublic()))
                .collect(Collectors.toList());
    }

    @Override
    public EPasteCodeUrlResponse create(EPasteCodeRequest request) {

        int hash = generatedId();
        EPasteCodeEntity pasteCodeEntity = new EPasteCodeEntity();
        pasteCodeEntity.setData(request.getData());
        pasteCodeEntity.setId(hash);
        pasteCodeEntity.setHash(Integer.toHexString(hash));
        pasteCodeEntity.setPublic(request.getPublicStatus() == EPublicStatus.PUBLIC);
        pasteCodeEntity.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        repository.add(pasteCodeEntity);
        return new EPasteCodeUrlResponse(host + "/" + pasteCodeEntity.getHash());
    }

    private int generatedId() {
        return idGenerator.getAndIncrement();
    }
}
