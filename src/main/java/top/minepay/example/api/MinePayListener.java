package top.minepay.example.api;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import top.minepay.api.events.*;
import top.minepay.bean.TradeInfo;

/**
 * 交易控制
 *
 * @author Peter1303
 * @date Created in 2022/3/8 21:09
 */
public class MinePayListener implements Listener {
    /**
     * 开始交易
     *
     * @param event 事件
     */
    @EventHandler
    public void onMinePayTradingEvent(MinePayTradingEvent event) {
        Bukkit.getConsoleSender().sendMessage("trading");
        TradeInfo info = event.getTradeInfo();
        // 设置 false 那么 MinePay 订单处理将不会执行
        event.setHandled(false);
    }

    /**
     * 延迟取消
     *
     * @param event 事件
     */
    @EventHandler
    public void onMinePayPreCancelEvent(MinePayPreCancelEvent event) {
        Bukkit.getConsoleSender().sendMessage("preCancel");
    }

    /**
     * 取消订单
     *
     * @param event 事件
     */
    @EventHandler
    public void onMinePayCancelledEvent(MinePayCancelledEvent event) {
        Bukkit.getConsoleSender().sendMessage("cancelled");
    }

    /**
     * 完成交易
     *
     * @param event 事件
     */
    @EventHandler
    public void onMinePaySuccessEvent(MinePaySuccessEvent event) {
        Bukkit.getConsoleSender().sendMessage("success");
    }

    /**
     * 订单过期
     *
     * @param event 事件
     */
    @EventHandler
    public void onMinePayOutdatedEvent(MinePayTradeOutDatedEvent event) {
        Bukkit.getConsoleSender().sendMessage("outdated");
    }
}
