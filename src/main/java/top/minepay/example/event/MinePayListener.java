package top.minepay.example.event;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import top.minepay.api.events.*;
import top.minepay.bean.TradeInfo;
import top.minepay.common.enums.PaymentType;
import top.minepay.common.enums.TradeType;

/**
 * 监听事件
 *
 * @author Peter1303
 * @author Ranica
 * @date Created in 2022/3/8 21:09
 */
public class MinePayListener implements Listener {
    /**
     * 开始交易
     *
     * @param event 事件
     */
    @EventHandler
    public void onMinePayPreTradingEvent(MinePayPreTradingEvent event) {
        Bukkit.getConsoleSender().sendMessage("preTrading");
        // 获取订单信息
        TradeInfo info = event.getTradeInfo();
        // 获取订单价格（单位：分）
        int price = info.getPrice();
        // 允许修改订单金额（单位：分）
        info.setPrice(100);
        // 获取下单玩家名字
        String playerName = info.getPlayerName();
        // 获取订单类型（点券或礼包）
        TradeType tradeType = info.getTradeType();
        // 获取付款方式（微信或支付宝）
        PaymentType paymentType = info.getType();
        // 设置 true 将取消订单
        event.setCancelled(false);
    }

    /**
     * 交易处理
     *
     * @param event 事件
     */
    @EventHandler
    public void onMinePayTradingEvent(MinePayTradingEvent event) {
        Bukkit.getConsoleSender().sendMessage("trading");
        // 设置 false 那么 MinePay 将不处理订单，如二维码地图生成等
        event.setHandled(true);
        TradeInfo info = event.getTradeInfo();
        // 获取用于生成二维码的文本内容
        String qrCodeContent = info.getQrcodeContent();
    }

    /**
     * 订单开始取消
     *
     * @param event 事件
     */
    @EventHandler
    public void onMinePayPreCancelEvent(MinePayPreCancelEvent event) {
        Bukkit.getConsoleSender().sendMessage("preCancel");
    }

    /**
     * 订单已取消
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
