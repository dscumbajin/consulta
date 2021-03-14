package com.dscumbajin.demo;

import com.dscumbajin.demo.srv.ErubroSrv;
import com.dscumbajin.demo.srv.RubroSrv;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    RubroSrv rubroSrv;
    @Autowired
    ErubroSrv erubroSrv;

    @Test
    void contextLoads() {
        List<String> codpros= new ArrayList<>();
        codpros.add("101MPFBCAJ0066");
        codpros.add("101MPFBIFB1335");
        codpros.add("101MPFBIFB1330");
        codpros.add("106PLACNEG0010");
        codpros.add("106PLACPOS0019");
        codpros.add("REFINAP0001");
        codpros.add("101MPFBSEP0001");
        codpros.add("101MPFBTAP0334");
        var list  = erubroSrv.findByCodpro(codpros);
        System.out.println(list);
    }

}
