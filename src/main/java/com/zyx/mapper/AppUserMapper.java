package com.zyx.mapper;

import com.zyx.dto.AppUserListDto;
import com.zyx.dto.OfficialUserListDto;
import com.zyx.model.AppUser;
import com.zyx.parm.QueryAppUserParam;
import com.zyx.parm.appUser.AppUserCreateParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository("appUserMapper")
public interface AppUserMapper extends Mapper<AppUser> {
    int delByPrimaryKey(Integer id);

    int unDelByPrimaryKey(Integer id);

    int maskByPrimaryKey(Integer id);

    int unMaskByPrimaryKey(Integer id);

    List<String> queryAppUserByName(List<Integer> userId);

    List<AppUserListDto> queryAppUserList(QueryAppUserParam param);

    int selectAppUserListCount(QueryAppUserParam param);

    List<OfficialUserListDto> queryOfficialAccountList();

    int updateAppUserByPrimaryKey(AppUserCreateParam param);

    // =============认证相关==============
    int selectAuthCount(Integer user_id);

    int insertAuthInfo(AppUserCreateParam param);

    int updateAuthInfo(AppUserCreateParam param);

    int authAppUserByPrimaryKey(Integer id, int i);

    int resetAppUser(AppUserCreateParam param);
}