package Hardware.Store.Example.Hardware.Store.Ltd;

import static org.assertj.core.api.Assertions.assertThat;  

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import Hardware.Store.Example.Hardware.Store.Ltd.webcontrollers.EProductController;
import Hardware.Store.Example.Hardware.Store.Ltd.webcontrollers.UserController;
import Hardware.Store.Example.Hardware.Store.Ltd.webcontrollers.UserDetailServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationTests {

	
	// SMOKE TESTS TO see if there are actually contents / stuff inside each of the controllers. Leaving out Web Layer / HttpRequests ...
	// ... since there are no such prints here and the port is determined by a working software.
	
	@Test
	void contextLoadsOther() {
	}
	
    @Autowired
    private EProductController eController;
    @Autowired
    private UserController usController;
    @Autowired
    private UserDetailServiceImpl udsi;

    @Test
    public void contexLoads0() throws Exception {
        assertThat(eController).isNotNull();
    }
    
    @Test
    public void contexLoads1() throws Exception {
        assertThat(usController).isNotNull();
    }
    
    @Test
    public void contexLoads2() throws Exception {
        assertThat(udsi).isNotNull();
    }

}
