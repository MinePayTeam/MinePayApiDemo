package top.minepay.example.api;

import org.bukkit.Bukkit;
import top.minepay.api.MinePayApi;
import top.minepay.api.events.*;
import top.minepay.bean.TradeInfo;

/**
 * 交易控制
 *
 * @author Peter1303
 * @date Created in 2022/3/8 21:09
 */
public class APIImplementation
        implements MinePayTradingEvent,
        MinePayPreCancelEvent,
        MinePayCancelledEvent,
        MinePayTradeOutDatedEvent,
        MinePaySuccessEvent {
    @Override
    public boolean trading(TradeInfo tradeInfo) {
        Bukkit.getConsoleSender().sendMessage("trading");
        // 返回 false 那么 MinePay 订单处理将不会执行
        return false;
    }

    @Override
    public void preCancel(TradeInfo tradeInfo) {
        Bukkit.getConsoleSender().sendMessage("preCancel");
        TradeInfo info = new TradeInfo();
        info.setOrder("订单号");
        info.setName("礼包名字");
        info.setPlayerName("Peter1303");
        // 价格的单位是分
        info.setPrice(1);
        // 微信 wechat | alipay 支付宝
        info.setType("wechat");
        MinePayApi.TradeController.start(info);
    }

    @Override
    public void cancelled(TradeInfo tradeInfo) {
        Bukkit.getConsoleSender().sendMessage("cancelled");
    }

    @Override
    public void success(TradeInfo tradeInfo) {
        Bukkit.getConsoleSender().sendMessage("success");
    }

    @Override
    public void outdated(TradeInfo tradeInfo) {
        Bukkit.getConsoleSender().sendMessage("outdated");
    }
}
