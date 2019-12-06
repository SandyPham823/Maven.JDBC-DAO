package daos;

import models.DTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DAOtest {

    DAO controller = new DAO();
    DTO user;

    @Before
    public void setUpTest() {
        user = new DTO();
    }

    @Test
    public void findByIdTest(){
        user = controller.findById(1);
        String actualName = "Yolande";

        Assert.assertEquals(user.getFirst_name(), actualName);
    }

    @Test
    public void findAllTest(){
        List allUsers = controller.findAll();

        Assert.assertEquals(6,allUsers.size());
    }

    @Test
    public void updateTest(){
        user = controller.findById(5);
        user.setFirst_name("Valentin");
        controller.update(user);
        String actualUpdate = controller.findById(5).getFirst_name();

        Assert.assertEquals("Valentin", actualUpdate);

    }

    @Test
    public void createTest(){
        DTO newUser = new DTO(7, "ValentinTwo", null, null, null, null);
        controller.create(newUser);

        Assert.assertEquals(7, controller.findById(7).getId());
    }

    @Test
    public void deleteTest(){
       // added new user
        DTO newUser = new DTO(8, "ValentinThree", null, null, null, null);
        controller.create(newUser);

        // check v3 exists
        DTO expectedUser = controller.findById(8);
        Assert.assertNotNull(expectedUser);

        // delete the added user
        controller.delete(8);

        // check v3 doesn't exist
        DTO actualUser = controller.findById(8);
        Assert.assertNull(actualUser);
    }
}
