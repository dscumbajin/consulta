package com.dscumbajin.demo.ctrl;

import com.dscumbajin.demo.srv.ProcesoSrv;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ProcesoCtrl {

    private final ProcesoSrv procesoSrv;

    public ProcesoCtrl(ProcesoSrv procesoSrv) {
        this.procesoSrv = procesoSrv;
    }

    @GetMapping(value = "/api/export", params = "cod")
    public ResponseEntity<byte[]> exportExcel(@RequestParam String cod) throws IOException {
        return procesoSrv.proceso(cod);
    }

}
