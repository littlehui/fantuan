package com.littlehui.fantuan.model.consume;

import com.littlehui.fantuan.services.bean.Consume;
import com.littlehui.fantuan.services.bean.User;
import com.littlehui.fantuan.services.service.PriceService;
import com.littlehui.fantuan.services.spring.ApplicationContextUtil;
import com.littlehui.fantuan.services.vbean.UserPriceVB;
import com.sun.javafx.event.EventUtil;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.impl.EventProcessor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by littlehui on 2016/10/5 0005.
 */
public class ConsumeViewModel implements Serializable {

    private User user;

    private UserPriceVB userPriceVB;

    private PriceService priceService = ApplicationContextUtil.getBean(PriceService.class);

    private Consume consume = new Consume();

    public Consume getConsume() {
        return consume;
    }

    public void setConsume(Consume consume) {
        this.consume = consume;
    }

    public UserPriceVB getUserPriceVB() {
        return userPriceVB;
    }

    public void setUserPriceVB(UserPriceVB userPriceVB) {
        this.userPriceVB = userPriceVB;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Map<String, Object> args = new HashMap();

    @Init
    public void init() {
        args = (Map<String, Object>) Executions.getCurrent().getArg();
        userPriceVB = (UserPriceVB) args.get("userPriceVB");
    }

    @Command
    public void consumeSubmit(@BindingParam("consumeDetail") String consumeDetail, @BindingParam("consumePrice") BigDecimal consumePrice) {
        consume.setUserCode(userPriceVB.getUserCode());
        consume.setConsumePrice(consumePrice);
        consume.setConsumeDetail(consumeDetail);
        priceService.transConsume(consume);
    }
}
