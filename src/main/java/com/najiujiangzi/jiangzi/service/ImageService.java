package com.najiujiangzi.jiangzi.service;

import com.najiujiangzi.jiangzi.dto.ImageDTO;
import com.najiujiangzi.jiangzi.mappers.ImageMapper;
import com.najiujiangzi.jiangzi.model.Image;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageMapper imageMapper;

    public List<Image> find(ImageDTO dto, Page page) {
        return imageMapper.find(dto, page);
    }

    public Image findOne() {
        return this.find(new ImageDTO(), null).get(0);
    }

    public Boolean insert(ImageDTO dto) {
        return imageMapper.insert(dto);
    }

    public Boolean update(ImageDTO dto) {
        return imageMapper.update(dto);
    }
}
