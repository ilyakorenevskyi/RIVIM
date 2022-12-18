import app.DAOs.PeriodicalsDAO;
import app.entities.Periodical;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestPeriodicalDAO {
    private final Periodical testPeriodical= new Periodical(Integer.MAX_VALUE, "Test", -100);


    @Test
    @Order(1)
    public void addPeriodicalByIdTest() throws ClassNotFoundException {
        assertTrue(PeriodicalsDAO.addPeriodical(testPeriodical));
        assertEquals(testPeriodical, PeriodicalsDAO.getPeriodicalById(testPeriodical.getId()));
    }

    @Test
    @Order(2)
    public void editPeriodicalTest() throws ClassNotFoundException{
        testPeriodical.setName("new test");
        testPeriodical.setPrice(-101);
        PeriodicalsDAO.editPeriodical(testPeriodical);
        assertEquals(testPeriodical, PeriodicalsDAO.getPeriodicalById(testPeriodical.getId()));
    }


    @Test
    @Order(3)
    public void deletePeriodicalTest() throws ClassNotFoundException {
        PeriodicalsDAO.deletePeriodical(testPeriodical.getId());
        assertNull(PeriodicalsDAO.getPeriodicalById(testPeriodical.getId()));
    }
}
