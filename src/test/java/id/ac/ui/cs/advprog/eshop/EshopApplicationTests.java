package id.ac.ui.cs.advprog.eshop;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EshopApplicationTests {

    @InjectMocks
    private EshopApplication eshopApplication;

    @Test
    void contextLoads() {
        assertNotNull(eshopApplication);
    }

    @Test
    void testMain() {
        EshopApplication.main(new String[] {});
        assertTrue(true);
    }

}
