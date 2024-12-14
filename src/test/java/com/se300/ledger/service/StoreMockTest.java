package com.se300.ledger.service;

import com.se300.ledger.TestSmartStoreApplication;
import com.se300.ledger.model.Store;
import com.se300.ledger.model.StoreModelException;
import com.se300.ledger.repository.StoreRepository;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = { TestSmartStoreApplication.class })
public class StoreMockTest {

    @MockBean
    @Qualifier("StoreRepository")
    private StoreRepository storeRepository;

    @Autowired
    private StoreModelService storeService;

    @PostConstruct
    public void prepare() {
        this.storeService.storeRepository = storeRepository;
    }

    @Test
    public void testSaveStore() throws LedgerException, StoreModelException {

        Store store = new Store(Long.parseLong("2"), "75 Forbes", "My First Store");

        when(storeRepository.save(any())).thenReturn(store);
        Store copyStore = storeService.provisionStore(Long.parseLong("2"), "75 Forbes", "My First Store", null);
        verify(storeRepository, times(1)).save(any());

        assertAll("Verify Account properties",
                () -> assertEquals(copyStore.getId(), store.getId()),
                () -> assertEquals(copyStore.getAddress(), store.getAddress()),
                () -> assertEquals(copyStore.getDescription(), store.getDescription()));
    }
}
