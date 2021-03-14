package com.dscumbajin.demo.srv;

import com.dscumbajin.demo.dao.RubroDao;
import com.dscumbajin.demo.entity.Rubro;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RubroSrv {

    private final RubroDao rubroDao;

    public RubroSrv(RubroDao rubroDao) {
        this.rubroDao = rubroDao;
    }

    public List<Rubro> findAll(){
        return rubroDao.findAll();
    }

    public List<Rubro> findByCoderub(Integer coderub){
        return rubroDao.findByCoderub(coderub);
    }

    public List<Rubro> findByCoderub(List<Integer> coderub){
        return rubroDao.findByCoderubIn(coderub);
    }
}
