package com.dscumbajin.demo;

import com.dscumbajin.demo.srv.ProcesoSrv;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    ProcesoSrv procesoSrv;

    @Test
    void contextLoads() {

    }

}
