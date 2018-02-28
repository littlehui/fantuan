package com.littlehui.fantuan.model.exchange;

import com.littlehui.fantuan.services.service.PriceService;
import com.littlehui.fantuan.services.spring.ApplicationContextUtil;
import com.littlehui.fantuan.services.vbean.UserPriceVB;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by littlehui on 2016/10/6 0006.
 */
public class ExchangeViewModel implements Serializable {

    private UserPriceVB userPriceVB;

    private PriceService priceService = ApplicationContextUtil.getBean(PriceService.class);

    public UserPriceVB getUserPriceVB() {
        return userPriceVB;
    }

    public void setUserPriceVB(UserPriceVB userPriceVB) {
        this.userPriceVB = userPriceVB;
    }

    private Map<String, Object> args = new HashMap();

    @Init
    public void init() {
        args = (Map<String, Object>) Executions.getCurrent().getArg();
        userPriceVB = (UserPriceVB) args.get("userPriceVB");
    }
}
