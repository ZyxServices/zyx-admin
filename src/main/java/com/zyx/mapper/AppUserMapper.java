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

    int authAppUserByPrimaryKey(Integer id, int i);

    int insertAuthInfo(AppUserCreateParam param);

    List<AppUserListDto> queryAppUserList(QueryAppUserParam param);

    int selectAppUserListCount(QueryAppUserParam param);

    List<OfficialUserListDto> queryOfficialAccountList();


}