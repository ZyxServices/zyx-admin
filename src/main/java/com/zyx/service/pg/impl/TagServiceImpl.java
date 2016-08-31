package com.zyx.service.pg.impl;

import com.zyx.constants.PgConstants;
import com.zyx.mapper.TagMapper;
import com.zyx.model.Tag;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.pg.TagService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.service.pg.impl
 * Create by XiaoWei on 2016/8/2
 */
@Service
public class TagServiceImpl extends BaseServiceImpl<Tag> implements TagService {
    @Resource
    private TagMapper tagMapper;

    public TagServiceImpl() {
        super(Tag.class);
    }

    @Override
    public Map<String, Object> findAll() {
        List<Tag> tags = tagMapper.findAll();
        return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_34000_MSG, tags);
    }

    @Override
    public Map<String, Object> createTag(String tagName) {
        if (Objects.equals(tagName, null) || Objects.equals(tagName, "")) {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30026, PgConstants.PG_ERROR_CODE_30026_MSG);
        }
        Tag t = new Tag();
        t.setState(0);
        t.setTagName(tagName);
        Integer result = tagMapper.insert(t);
        if (result > 0) {
            return MapUtils.buildErrorMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_33000_MSG);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }

    @Override
    public Map<String, Object> setState(Integer id, Integer state) {
        if (Objects.equals(state, null)) {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30020, PgConstants.PG_ERROR_CODE_30020_MSG);
        }
        if (!Objects.equals(id, null)) {
            Integer result = tagMapper.setState(id, state);
            if (result > 0) {
                return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_39000_MSG, null);
            }
        } else {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30027, PgConstants.PG_ERROR_CODE_30027_MSG);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }
}
