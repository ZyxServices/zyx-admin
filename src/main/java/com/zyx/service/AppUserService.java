package com.zyx.service;

import com.zyx.constants.AppUserConstants;
import com.zyx.constants.Constants;
import com.zyx.dto.AppUserListDto;
import com.zyx.dto.OfficialUserListDto;
import com.zyx.mapper.AppUserMapper;
import com.zyx.model.AppUser;
import com.zyx.parm.QueryAppUserParam;
import com.zyx.parm.appUser.AppUserCreateParam;
import com.zyx.utils.MapUtils;
import org.apache.commons.collections.map.HashedMap;
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
public class AppUserService extends BaseServiceImpl<AppUser> {

    @Autowired
    AppUserMapper appUserMapper;

    public AppUserService() {
        super(AppUser.class);
    }

    public AppUser selectByPhone(String phone) {
        AppUser appUser = new AppUser();
        appUser.setPhone(phone);
        return appUserMapper.selectOne(appUser);
    }

    public Map<String, Object> queryList(QueryAppUserParam param) {
        Map<String, Object> map = new HashedMap();

        try {
            List<AppUserListDto> _list = appUserMapper.queryAppUserList(param);
            int count = appUserMapper.selectAppUserListCount(param);
            map.put("rows", _list);
            map.put("total", count);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public Map<String, Object> queryOfficialAccountList() {
        Map<String, Object> map = new HashedMap();
        try {
            Example example = new Example(AppUser.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("official", 1);
            List<OfficialUserListDto> _list = appUserMapper.queryOfficialAccountList();
            int count = appUserMapper.selectCountByExample(example);
            map.put("rows", _list);
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

    public Map<String, Object> authAppUser(Integer id, int i) {
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

    public Map<String, Object> insertAppUser(AppUserCreateParam param) {
        AppUser appUser = new AppUser();
        appUser.setPhone(param.getPhone());
        appUser.setPassword(param.getPassword());
        appUser.setAvatar(param.getAvatar());
        appUser.setCreateTime(System.currentTimeMillis());
        appUser.setNickname(param.getNickname());
        appUser.setAddress(param.getAddress());
        appUser.setSex(param.getSex());
        appUser.setOfficial(param.getOfficial());
        appUser.setMask(false);
        appUser.setDel(false);
        appUser.setAuthenticate(0);
        try {
            int result = appUserMapper.insert(appUser);
            if (result >= 1) {
                return MapUtils.buildSuccessMap(AppUserConstants.SUCCESS, AppUserConstants.MSG_SUCCESS, null);
            } else {
                return MapUtils.buildErrorMap(AppUserConstants.ERROR_APP_USER_5002, AppUserConstants.ERROR_APP_USER_5002_MSG);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AppUserConstants.MAP_500;
        }
    }

    public Map<String, Object> updateAppUser(AppUserCreateParam param) {
        try {
            int result = appUserMapper.updateAppUserByPrimaryKey(param);
            if (result >= 1) {
                appUserMapper.updateAuthInfo(param);
                return MapUtils.buildSuccessMap(AppUserConstants.SUCCESS, AppUserConstants.MSG_SUCCESS, null);
            } else {
                return MapUtils.buildErrorMap(AppUserConstants.ERROR_APP_USER_5003, AppUserConstants.ERROR_APP_USER_5003_MSG);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AppUserConstants.MAP_500;
        }
    }

    List<AppUserListDto> selectBaseInfo(List<Integer> ids) {
        return null;
    }

    public Map<String, Object> submitAppUserAuthInfo(AppUserCreateParam param) {
        try {
            int result = appUserMapper.authAppUserByPrimaryKey(param.getAppUserId(), 1);
            if (result == 1) {
                result = appUserMapper.selectAuthCount(param.getAppUserId());
                if (result != 0) {
                    result = appUserMapper.updateAuthInfo(param);
                } else {
                    result = appUserMapper.insertAuthInfo(param);
                }
            }
            if (result >= 1) {
                return MapUtils.buildSuccessMap(AppUserConstants.SUCCESS, AppUserConstants.MSG_SUCCESS, null);
            } else {
                return MapUtils.buildErrorMap(AppUserConstants.ERROR_APP_USER_5003, AppUserConstants.ERROR_APP_USER_5003_MSG);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AppUserConstants.MAP_500;
        }
    }

    public Map<String,Object> resetAppUser(AppUserCreateParam param) {
        try {
            int result = appUserMapper.resetAppUser(param);
            if (result >= 1) {
                return MapUtils.buildSuccessMap(AppUserConstants.SUCCESS, AppUserConstants.MSG_SUCCESS, null);
            } else {
                return MapUtils.buildErrorMap(AppUserConstants.ERROR_APP_USER_5003, AppUserConstants.ERROR_APP_USER_5003_MSG);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AppUserConstants.MAP_500;
        }
    }
}
