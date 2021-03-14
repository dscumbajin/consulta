package com.dscumbajin.demo.srv;

import com.dscumbajin.demo.entity.Erubro;
import com.dscumbajin.demo.entity.Rubro;
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
            List<Integer> coderubs = primer.stream().map(Erubro::getCoderub).collect(Collectors.toList());
            List<Rubro> rubros = rubroSrv.findByCoderub(coderubs);
            if(rubros.size()>0){
                for (Rubro rubro : rubros) {
                    System.out.println(rubro); //mandar al excel
                }
            }
            while (rubros.size()>0){
                recusrsiveProcees(rubros);
            }
        }
    }

    public void recusrsiveProcees(List<Rubro> rubros){
        List<String> codpros = rubros.stream().filter(rubro -> !rubro.getCodpro().equals("0")).map(Rubro::getCodpro).collect(Collectors.toList());
        List<Erubro> erubros = erubroSrv.findByCodpro(codpros);
        List<Integer> coderubs = erubros.stream().map(Erubro::getCoderub).collect(Collectors.toList());
        rubros = rubroSrv.findByCoderub(coderubs);
        if(rubros.size()>0){
            for (Rubro rubro : rubros) {
                System.out.println(rubro);//excel
                if(!rubro.getCodpro().equals("0")){
                List<Erubro> erubrosTmp = erubroSrv.findByCodpro(rubro.getCodpro());
                List<Integer> coderubsTmp = erubrosTmp.stream().map(Erubro::getCoderub).collect(Collectors.toList());
                List<Rubro> rubrosTmp = rubroSrv.findByCoderub(coderubsTmp);
                recusrsiveProcees(rubrosTmp);}
            }
        }
    }
}
