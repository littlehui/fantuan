package com.littlehui.fantuan.services.service;

import com.littlehui.fantuan.services.bean.*;
import com.littlehui.fantuan.services.constants.OperateEnum;
import com.littlehui.fantuan.services.manager.*;
import com.littlehui.fantuan.services.vbean.UserPriceVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
@Service
public class PriceService {

    @Autowired
    UserPriceManager userPriceManager;

    @Autowired
    UserPriceVBManager userPriceVBManager;

    @Autowired
    ConsumeManager consumeManager;

    @Autowired
    ExchangeManager exchangeManager;

    @Autowired
    RechargeManager rechargeManager;

    @Autowired
    OperateLogManager operateLogManager;

    public List<UserPrice> getUserPrices() {
        return userPriceManager.findAll();
    }

    public List<UserPriceVB> getUserPriceVBList() {
        return userPriceVBManager.query("");
    }

    public List<UserPriceVB> queryAllVBList() {
        return userPriceVBManager.queryAll();
    }

    public List<UserPriceVB> queryVBListExcGod() {
        return userPriceVBManager.queryVBListExcGod();
    }

    public List<UserPriceVB> queryByUserName(String userName) {
        return userPriceVBManager.queryByUserName(userName);
    }

    @Transactional(rollbackFor = Exception.class)
    public void transConsume(Consume consume) {
        String userCode = consume.getUserCode();
        UserPrice userPrice = userPriceManager.getUserPrice(userCode);
        BigDecimal remainPrice = userPrice.getRemainPrice().subtract(consume.getConsumePrice());
        BigDecimal consumeTotalPrice = userPrice.getConsumePrice().add(consume.getConsumePrice());
        userPrice.setConsumePrice(consumeTotalPrice);
        userPrice.setRemainPrice(remainPrice);
        userPriceManager.save(userPrice);
        consumeManager.save(consume);
    }

    @Transactional(rollbackFor = Exception.class)
    public void transExchange(Exchange exchange) {
        UserPrice sourceUserPrice = userPriceManager.getUserPrice(exchange.getSourceUserCode());
        UserPrice desUserPRice = userPriceManager.getUserPrice(exchange.getDesUserCode());
        BigDecimal sourceExchangeTotalPrice = sourceUserPrice.getExchangePrice().add(exchange.getExchangePrice());
        BigDecimal desRemainPrice = desUserPRice.getRemainPrice().add(exchange.getExchangePrice());
        BigDecimal sourceRemainPrice = sourceUserPrice.getRemainPrice().subtract(exchange.getExchangePrice());
        sourceUserPrice.setExchangePrice(sourceExchangeTotalPrice);
        sourceUserPrice.setRemainPrice(sourceRemainPrice);
        desUserPRice.setRemainPrice(desRemainPrice);
        userPriceManager.save(sourceUserPrice);
        userPriceManager.save(desUserPRice);
        exchangeManager.save(exchange);
    }

    @Transactional(rollbackFor = Exception.class)
    public void transRecharge(Recharge recharge) {
        UserPrice rechargeUserPrice = userPriceManager.getUserPrice(recharge.getUserCode());
        BigDecimal remainPrice = rechargeUserPrice.getRemainPrice().add(recharge.getRechargePrice());
        rechargeUserPrice.setRemainPrice(remainPrice);
        rechargeManager.save(recharge);
        userPriceManager.save(rechargeUserPrice);
    }

    @Transactional(rollbackFor = Exception.class)
    public void transUnConsume(Integer consumeId, String operateIp) {
        Consume consume = consumeManager.get(consumeId);
        String userCode = consume.getUserCode();
        UserPrice userPrice = userPriceManager.getUserPrice(userCode);
        BigDecimal remainUserPrice = userPrice.getRemainPrice().add(consume.getConsumePrice());
        BigDecimal consumePrice = userPrice.getConsumePrice().subtract(consume.getConsumePrice());

        userPrice.setRemainPrice(remainUserPrice);
        userPrice.setConsumePrice(consumePrice);

        OperateLog operateLog = new OperateLog();
        operateLog.setOperateIp(operateIp);
        operateLog.setOperateDetail(consume.toString());
        operateLog.setOperateType(OperateEnum.UN_CONSUME.name());
        userPriceManager.save(userPrice);
        consumeManager.delete(consume.getId());
        //保存日志
        operateLogManager.save(operateLog);
    }

    @Transactional(rollbackFor = Exception.class)
    public void transUnExchange(Integer exchangeId, String operateIp) {
        Exchange exchange = exchangeManager.get(exchangeId);
        String desUserCode = exchange.getDesUserCode();
        String srcUserCode = exchange.getSourceUserCode();
        UserPrice desUserPrice = userPriceManager.getUserPrice(desUserCode);
        UserPrice srcUserPrice = userPriceManager.getUserPrice(srcUserCode);
        BigDecimal desRemainPrice = desUserPrice.getRemainPrice().subtract(exchange.getExchangePrice());
        BigDecimal srcRemainPrice = srcUserPrice.getRemainPrice().add(exchange.getExchangePrice());
        BigDecimal srcExchangePrice = srcUserPrice.getExchangePrice().subtract(exchange.getExchangePrice());

        desUserPrice.setRemainPrice(desRemainPrice);
        srcUserPrice.setRemainPrice(srcRemainPrice);
        srcUserPrice.setExchangePrice(srcExchangePrice);

        userPriceManager.save(srcUserPrice);
        userPriceManager.save(desUserPrice);
        exchangeManager.delete(exchangeId);

        OperateLog operateLog = new OperateLog();
        operateLog.setOperateIp(operateIp);
        operateLog.setOperateDetail(exchange.toString());
        operateLog.setOperateType(OperateEnum.UN_EXCHANGE.name());
        operateLogManager.save(operateLog);

    }

    @Transactional(rollbackFor = Exception.class)
    public void transUnRecharge(Integer rechargeId, String operateIp) {
        Recharge recharge = rechargeManager.get(rechargeId);
        UserPrice recUserPrice = userPriceManager.getUserPrice(recharge.getUserCode());
        BigDecimal remainPrice = recUserPrice.getRemainPrice().subtract(recharge.getRechargePrice());

        OperateLog operateLog = new OperateLog();
        operateLog.setOperateIp(operateIp);
        operateLog.setOperateDetail(recharge.toString());
        operateLog.setOperateType(OperateEnum.UN_RECHARGE.name());

        recUserPrice.setRemainPrice(remainPrice);

        rechargeManager.delete(rechargeId);
        userPriceManager.save(recUserPrice);
        operateLogManager.save(operateLog);
    }

    public BigDecimal countTotalRemainPrice() {
        return userPriceManager.countTotalPrice();
    }

}
