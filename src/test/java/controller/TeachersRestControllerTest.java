package controller;

import org.junit.Test;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@Transactional
public class TeachersRestControllerTest extends AbstractTest {

//    @Autowired
//    TeachersService teachersService;

    @Test
    public void foo(){
        assertEquals(11, 5+6);
    }


}