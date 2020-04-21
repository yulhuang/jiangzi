package com.najiujiangzi.jiangzi.service;

import com.najiujiangzi.jiangzi.dto.EnshrineImageDTO;
import com.najiujiangzi.jiangzi.mappers.EnshrineImageMapper;
import com.najiujiangzi.jiangzi.model.EnshrineImage;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnshrineImageService {
    @Autowired
    private EnshrineImageMapper enshrineImageMapper;

    public List<EnshrineImage> find(EnshrineImageDTO dto, Page page) {
        return enshrineImageMapper.find(dto, page);
    }

    public EnshrineImage findOne() {
        return this.find(new EnshrineImageDTO(), null).get(0);
    }

    public int insert(EnshrineImageDTO dto) {
        return enshrineImageMapper.insert(dto);
    }

    public int update(EnshrineImageDTO dto) {
        return enshrineImageMapper.update(dto);
    }
}
