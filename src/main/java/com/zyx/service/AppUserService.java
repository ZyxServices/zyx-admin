package com.zyx.service;

import com.zyx.constants.Constants;
import com.zyx.mapper.AppUserMapper;
import com.zyx.model.AppUser;
import com.zyx.parm.QueryAppUserParam;
import com.zyx.utils.MapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * Created by wms on 2016/7/15.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title AppUserService.java
 */
@Service
public class AppUserService {

    @Autowired
    AppUserMapper appUserMapper;

    public Map<String, Object> queryList(QueryAppUserParam param) {
        Map<String, Object> map = new HashedMap();

        try {
            RowBounds row = new RowBounds((param.getPageNumber() - 1) * param.getPageSize(), param.getPageSize());
            Example example = new Example(AppUser.class);
            Example.Criteria criteria = example.createCriteria();
            if (param.getSearchText() != null) {
                criteria.andLike("nickname", "%" + param.getSearchText() + "%");
            }
            if (param.getAuthenticate() != null) {
                criteria.andEqualTo("authenticate", param.getAuthenticate());
            }
            Example.Criteria criteria2 = example.or();
            if (param.getSearchText() != null) {
                criteria2.andLike("phone", "%" + param.getSearchText() + "%");
            }
            if (param.getAuthenticate() != null) {
                criteria2.andEqualTo("authenticate", param.getAuthenticate());
            }
            List<AppUser> ss = appUserMapper.selectByExampleAndRowBounds(example, row);
            int count = appUserMapper.selectCountByExample(example);
            map.put("rows", ss);
            map.put("total", count);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public Map<String, Object> del(Integer id) {
        try {
            if (appUserMapper.delByPrimaryKey(id) > 0) {
                return Constants.MAP_BASE_SUCCESS;
            } else {
                return Constants.MAP_DEL_ERROR;
            }
        } catch (Exception e) {
            return Constants.MAP_500;
        }

    }

    public Map<String, Object> unDel(Integer id) {

        try {
            if (appUserMapper.unDelByPrimaryKey(id) > 0) {
                return Constants.MAP_BASE_SUCCESS;
            } else {
                return Constants.MAP_UN_DEL_ERROR;
            }
        } catch (Exception e) {
            return Constants.MAP_500;
        }
    }

    public Map<String, Object> mask(Integer id) {
        try {
            if (appUserMapper.maskByPrimaryKey(id) > 0) {
                return Constants.MAP_BASE_SUCCESS;
            } else {
                return Constants.MAP_MASK_ERROR;
            }
        } catch (Exception e) {
            return Constants.MAP_500;
        }

    }


    public Map<String, Object> unMask(Integer id) {
        try {
            if (appUserMapper.unMaskByPrimaryKey(id) > 0) {
                return Constants.MAP_BASE_SUCCESS;
            } else {
                return Constants.MAP_UN_MASK_ERROR;
            }
        } catch (Exception e) {
            return Constants.MAP_500;
        }

    }

    public Map<String,Object> authAppUser(Integer id, int i) {
        try {
            if (appUserMapper.authAppUserByPrimaryKey(id, i) > 0) {
                return Constants.MAP_BASE_SUCCESS;
            } else {
                return Constants.MAP_UN_MASK_ERROR;
            }
        } catch (Exception e) {
            return Constants.MAP_500;
        }
    }
}
