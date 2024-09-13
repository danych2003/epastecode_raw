package ee.epastecode.controller;

import ee.epastecode.api.request.EPasteCodeRequest;
import ee.epastecode.api.response.EPasteCodeResponse;
import ee.epastecode.api.response.EPasteCodeUrlResponse;
import ee.epastecode.service.EPasteCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController()
@RequiredArgsConstructor
public class EPasteCodeController {

    private final EPasteCodeService pasteCodeService;

    @GetMapping("/")
    public Collection<EPasteCodeResponse> getPublicPasteList() {
        return pasteCodeService.getFirstPublicEPasteCode();
    }

    @GetMapping("/{hash}")
    public EPasteCodeResponse getByHash(@PathVariable String hash) {
        return pasteCodeService.getByHash(hash);
    }

    @PostMapping("/")
    public EPasteCodeUrlResponse addPaste(@RequestBody EPasteCodeRequest request) {
        return pasteCodeService.create(request);
    }
}
