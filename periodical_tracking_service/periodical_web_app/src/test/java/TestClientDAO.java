import app.DAOs.ClientDAO;
import app.entities.Client;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestClientDAO  {
    Client testClient = new Client();
    @BeforeEach
    public void createClient() throws ClassNotFoundException {
        testClient = new Client(Integer.MAX_VALUE,"John Lock");
        ClientDAO.addClient(testClient,"test", "test");
    }
    @Test
    public void validateTest() throws ClassNotFoundException {
        assertTrue(ClientDAO.validate("test","test"));
        assertFalse(ClientDAO.validate("wrong", "wrong"));
    }
    @Test
    public void getUserInfoTest() throws ClassNotFoundException{
        Client actualUser = ClientDAO.getUserInfo("test");
        assertEquals(actualUser, testClient);
    }
    @AfterEach
    public void deleteTest() throws ClassNotFoundException {
        ClientDAO.deleteClient(testClient.getId());
    }
}
