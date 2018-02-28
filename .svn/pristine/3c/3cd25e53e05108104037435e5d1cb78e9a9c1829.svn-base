package com.littlehui.fantuan.services.manager;

import com.cyou.fz.commons.mybatis.selecterplus.mybatis.bean.Query;
import com.littlehui.fantuan.services.bean.UserLeader;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by littlehui on 2016/10/30 0030.
 */
@Repository
public class UserLeaderManager extends BaseManager<UserLeader> {

    public List<UserLeader> getUserLeader(Long timeMills) {
        Query query = Query.build(UserLeader.class);
        query.addLt("leaderStartTime", timeMills);
        query.addGt("leaderEndTime", timeMills);
        return findByQuery(query);
    }

    public void deleteUserLeader(Long timeMills) {
        Query query = Query.build(UserLeader.class);
        query.addGt("leaderStartTime", timeMills);
        query.addLt("leaderEndTime", timeMills);
        this.removeByQuery(query);
    }

    public void deleteUserLeader(Long startMills, Long endMills) {
        Query query = Query.build(UserLeader.class);
        query.addEq("leaderStartTime", startMills);
        query.addEq("leaderEndTime", endMills);
        this.removeByQuery(query);
    }
}
