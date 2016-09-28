package com.zyx.mapper;

import com.zyx.model.Devaluation;
import com.zyx.parm.deva.DevaParam;
import com.zyx.vo.deva.DevaVo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository("devaluationMapper")
public interface DevaMapper extends Mapper<Devaluation> {

    public List<DevaVo> selectDevaList(DevaParam devaParam);
    public List<Integer> selectUsedSequence(Integer model, Integer area);
    /**
     * 查询相关类型的所有首推ids
     * @param param
     * @return
     */
    List<Integer> queryModelIds(DevaParam param);

    public void cascadeDelete(DevaParam param);
}