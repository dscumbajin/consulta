package com.dscumbajin.demo.srv;

import com.dscumbajin.demo.dao.ErubroDao;
import com.dscumbajin.demo.dao.RubroDao;
import com.dscumbajin.demo.entity.Erubro;
import com.dscumbajin.demo.entity.Rubro;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErubroSrv {

    private final ErubroDao erubroDao;

    public ErubroSrv(ErubroDao erubroDao) {
        this.erubroDao = erubroDao;
    }

    public List<Erubro> findAll(){
        return erubroDao.findAll();
    }

    public List<Erubro> findByCodpro(String codpro){
        return erubroDao.findByCodpro(codpro);
    }

    public List<Erubro> findByCodpro(List<String> codpro){
        return erubroDao.findByCodproIn(codpro);
    }

}
