package com.zyx.model.vo;

import com.zyx.dto.AppUserAuthDto;
import com.zyx.model.Concern;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.model.vo
 * Create by XiaoWei on 2016/7/28
 */
public class ConcernVo extends Concern {
    private Integer zanCounts;
    private Integer commentCounts;
    private UserVo userVo;

    AppUserAuthDto appUserAuthDto;


    public Integer getZanCounts() {
        return zanCounts;
    }

    public void setZanCounts(Integer zanCounts) {
        this.zanCounts = zanCounts;
    }

    public Integer getCommentCounts() {
        return commentCounts;
    }

    public void setCommentCounts(Integer commentCounts) {
        this.commentCounts = commentCounts;
    }


    public AppUserAuthDto getAppUserAuthDto() {
        return appUserAuthDto;
    }

    public void setAppUserAuthDto(AppUserAuthDto appUserAuthDto) {
        this.appUserAuthDto = appUserAuthDto;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }
}
