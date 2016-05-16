package com.spider.manager.service;

import com.spider.domain.UpdateHdcOdds;
import com.spider.domain.UpdateHiloOdds;
import com.spider.domain.UpdateScoreAndHalf;
import com.spider.manager.model.JsonResult;

/**
 * Created by wsy on 2015/10/21.
 */
public interface SbcService {


    /**
     * 目前只有两个type选项可以使用
     * 如果type是null，全量同步到sbc，同步内容包括比赛进程信息和两家博彩公司的赔率
     * 否则，只同步比赛进程信息
     *
     * @param uniqueId not null 需求由原来的match_code改为unique_id，2016-05-05
     * @param type
     * @return
     * @see UpdateScoreAndHalf
     * @see UpdateHiloOdds
     * @see UpdateHdcOdds
     */
    JsonResult sync(String uniqueId, Integer type);

    /**
     * 根据company_odds表id同步相应赔率
     *
     * @param id 赔率id
     * @return
     */
    JsonResult syncOdds(String id);
}
