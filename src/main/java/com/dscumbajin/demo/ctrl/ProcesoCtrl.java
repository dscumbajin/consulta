package com.dscumbajin.demo.ctrl;

import com.dscumbajin.demo.srv.ProcesoSrv;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@RestController
public class ProcesoCtrl {

    private final ProcesoSrv procesoSrv;

    public ProcesoCtrl(ProcesoSrv procesoSrv) {
        this.procesoSrv = procesoSrv;
    }

    @GetMapping(value = "/api/export", params = "data")
    public ResponseEntity<byte[]> export(@RequestParam String data) throws IOException{
        return (ResponseEntity<byte[]>) procesoSrv.dowload(data);
    }



}
