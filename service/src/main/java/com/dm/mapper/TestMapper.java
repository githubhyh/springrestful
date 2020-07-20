package com.dm.mapper;

import com.dm.domain.Test;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TestMapper {
    public int insert(Test test);

    List<Test> find(Test test);
}
