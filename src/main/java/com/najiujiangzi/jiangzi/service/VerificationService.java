package com.najiujiangzi.jiangzi.service;

import com.najiujiangzi.jiangzi.dto.VerificationDTO;
import com.najiujiangzi.jiangzi.mappers.VerificationMapper;
import com.najiujiangzi.jiangzi.model.Verification;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationService {
    @Autowired
    private VerificationMapper verificationMapper;

    public List<Verification> find(VerificationDTO dto, Page page) {
        return verificationMapper.find(dto, page);
    }

    public Verification findOne() {
        return this.find(new VerificationDTO(), null).get(0);
    }

    public int insert(VerificationDTO dto) {
        return verificationMapper.insert(dto);
    }

    public int update(VerificationDTO dto) {
        return verificationMapper.update(dto);
    }

    public VerificationDTO findByCodeAndEmail(String code, String email) {
        return verificationMapper.findByCodeAndEmail(code, email);
    }

    public int deleteById(Long id) {
        return verificationMapper.deleteById(id);
    }
}
