package com.najiujiangzi.jiangzi.service;

import com.najiujiangzi.jiangzi.dto.ThumbsImageDTO;
import com.najiujiangzi.jiangzi.mappers.ThumbsImageMapper;
import com.najiujiangzi.jiangzi.model.ThumbsImage;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThumbsImageService {
    @Autowired
    private ThumbsImageMapper thumbsImageMapper;

    public List<ThumbsImage> find(ThumbsImageDTO dto, Page page) {
        return thumbsImageMapper.find(dto, page);
    }

    public ThumbsImage findOne() {
        return this.find(new ThumbsImageDTO(), null).get(0);
    }

    public int insert(ThumbsImageDTO dto) {
        return thumbsImageMapper.insert(dto);
    }

    public int update(ThumbsImageDTO dto) {
        return thumbsImageMapper.update(dto);
    }
}
