package com.zyx.mapper;

import com.zyx.model.Devaluation;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository("devaluationMapper")
public interface DevaMapper extends Mapper<Devaluation> {
    public List<Integer> selectUsedSequence(Integer model, Integer area);
}