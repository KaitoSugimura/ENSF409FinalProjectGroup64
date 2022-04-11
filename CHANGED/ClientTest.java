import org.junit.*;
import static org.junit.Assert.*;


public class ClientTest {


    /* CLIENT TESTS */

    // Client(ClientType) is called
    // getType() returns the correct ClientType
    @Test
    public void testGetType() {
        Client client = new Client(ClientType.ADULT_MALE);
        ClientType actualType = client.getType();
        assertEquals("getType() did not return the correct type",ClientType.ADULT_MALE, actualType);
    }
    
}
