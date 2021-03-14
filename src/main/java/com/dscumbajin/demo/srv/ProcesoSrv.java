package com.dscumbajin.demo.srv;

import com.dscumbajin.demo.entity.Erubro;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcesoSrv {

    private final RubroSrv rubroSrv;
    private final ErubroSrv erubroSrv;

    public ProcesoSrv(RubroSrv rubroSrv, ErubroSrv erubroSrv) {
        this.rubroSrv = rubroSrv;
        this.erubroSrv = erubroSrv;
    }

    public void proceso(String cod){
        File export = new File("Export-"+cod);
        List<Erubro> primer = erubroSrv.findByCodpro(cod);
        if(primer.size()>0){
            List<Integer> codigos = primer.stream().map(Erubro::getClass).collect(Collectors.toList());
        }
    }
}
