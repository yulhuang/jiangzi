package com.najiujiangzi.jiangzi.service;

import com.najiujiangzi.jiangzi.dto.PersistentLoginsDTO;
import com.najiujiangzi.jiangzi.mappers.PersistentLoginsMapper;
import com.najiujiangzi.jiangzi.model.PersistentLogins;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistentLoginsService {
    @Autowired
    private PersistentLoginsMapper persistentLoginsMapper;

    public List<PersistentLogins> find(PersistentLoginsDTO dto, Page page) {
        return persistentLoginsMapper.find(dto, page);
    }

    public PersistentLogins findOne() {
        return this.find(new PersistentLoginsDTO(), null).get(0);
    }

    public int insert(PersistentLoginsDTO dto) {
        return persistentLoginsMapper.insert(dto);
    }

    public int update(PersistentLoginsDTO dto) {
        return persistentLoginsMapper.update(dto);
    }
}
