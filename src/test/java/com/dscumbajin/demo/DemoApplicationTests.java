package com.dscumbajin.demo;

import com.dscumbajin.demo.srv.ErubroSrv;
import com.dscumbajin.demo.srv.ProcesoSrv;
import com.dscumbajin.demo.srv.RubroSrv;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    RubroSrv rubroSrv;
    @Autowired
    ErubroSrv erubroSrv;
    @Autowired
    ProcesoSrv procesoSrv;

    @Test
    void contextLoads() {
        procesoSrv.proceso("201BATEECUE3-30H-FEI");
    }

}
