package com.najiujiangzi.jiangzi.service;

import com.najiujiangzi.jiangzi.dto.EnshrineGroupDTO;
import com.najiujiangzi.jiangzi.mappers.EnshrineGroupMapper;
import com.najiujiangzi.jiangzi.model.EnshrineGroup;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnshrineGroupService {
    @Autowired
    private EnshrineGroupMapper enshrineGroupMapper;

    public List<EnshrineGroup> find(EnshrineGroupDTO dto, Page page) {
        return enshrineGroupMapper.find(dto, page);
    }

    public EnshrineGroup findOne() {
        return this.find(new EnshrineGroupDTO(), null).get(0);
    }

    public Boolean insert(EnshrineGroupDTO dto) {
        return enshrineGroupMapper.insert(dto);
    }

    public Boolean update(EnshrineGroupDTO dto) {
        return enshrineGroupMapper.update(dto);
    }
}
