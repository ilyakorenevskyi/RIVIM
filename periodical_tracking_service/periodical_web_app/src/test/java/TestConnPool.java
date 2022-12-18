import app.ConnectionPool;
import app.entities.Client;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestConnPool {

    @Test
    public void testConnectionLimit(){
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        for(int i = 0;i<ConnectionPool.INITIAL_POOL_SIZE;i++) {
            assertNotNull(connectionPool.getConnection());
        }
        assertNull(connectionPool.getConnection());
    }
}
