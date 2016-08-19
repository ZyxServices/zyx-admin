package com.zyx.mapper;

import com.zyx.model.Devaluation;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository("devaluationMapper")
public interface DevaMapper extends Mapper<Devaluation> {
}