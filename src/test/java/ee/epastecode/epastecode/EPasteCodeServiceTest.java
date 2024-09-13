package ee.epastecode.epastecode;

import ee.epastecode.api.domain.EPasteCodeEntity;
import ee.epastecode.api.response.EPasteCodeResponse;
import ee.epastecode.exception.NotFoundEntityException;
import ee.epastecode.repository.EPasteCodeRepository;
import ee.epastecode.service.EPasteCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EPasteCodeServiceTest {
    @Autowired
    private EPasteCodeService pasteCodeService;

    @MockBean
    private EPasteCodeRepository pasteCodeRepository;

    @Test
    public void notExistHash() {
        when(pasteCodeRepository.getByHash(anyString())).thenThrow(NotFoundEntityException.class);
        assertThrows(NotFoundEntityException.class, () -> pasteCodeService.getByHash("TestWrongHash"));
    }

    @Test
    public void getExistHash() {
        EPasteCodeEntity entity = new EPasteCodeEntity();
        entity.setHash("1");
        entity.setData("11");
        entity.setPublic(true);

        when(pasteCodeRepository.getByHash("1")).thenReturn(entity);

        EPasteCodeResponse expected = new EPasteCodeResponse("11", true);
        EPasteCodeResponse actual = pasteCodeService.getByHash("1");

        assertEquals(expected, actual);

    }


}
