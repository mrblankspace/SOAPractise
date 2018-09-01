package cn.blankspace.taotao.service;

import cn.blankspace.taotao.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements  TestService{
    @Autowired
    private TestMapper mapper;
    @Override
    public String queryNow() {
        return mapper.queryNow();
    }
}
