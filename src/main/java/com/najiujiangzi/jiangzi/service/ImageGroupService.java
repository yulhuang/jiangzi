package com.najiujiangzi.jiangzi.service;

import com.najiujiangzi.jiangzi.dto.ImageGroupDTO;
import com.najiujiangzi.jiangzi.mappers.ImageGroupMapper;
import com.najiujiangzi.jiangzi.model.ImageGroup;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageGroupService {
    @Autowired
    private ImageGroupMapper image_groupMapper;

    public List<ImageGroup> find(ImageGroupDTO dto, Page page) {
        return image_groupMapper.find(dto, page);
    }

    public ImageGroup findOne() {
        return this.find(new ImageGroupDTO(), null).get(0);
    }

    public int insert(ImageGroupDTO dto) {
        return image_groupMapper.insert(dto);
    }

    public int update(ImageGroupDTO dto) {
        return image_groupMapper.update(dto);
    }
}
