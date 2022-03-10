package top.minepay.example;

import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import top.minepay.api.MinePayApi;
import top.minepay.api.events.*;
import top.minepay.bean.RankItem;
import top.minepay.example.api.APIImplementation;

import java.util.List;

public final class MinePayApiDemo extends JavaPlugin {

    @Override
    public void onEnable() {
        // 注册 MinePay 交易事件监听
        APIImplementation implementation = new APIImplementation();
        // 开始交易
        Bukkit.getServicesManager().register(
                MinePayTradingEvent.class,
                implementation,
                this,
                ServicePriority.Normal);
        // 延迟取消
        Bukkit.getServicesManager().register(
                MinePayPreCancelEvent.class,
                implementation,
                this,
                ServicePriority.Normal);
        // 取消订单
        Bukkit.getServicesManager().register(
                MinePayCancelledEvent.class,
                implementation,
                this,
                ServicePriority.Normal);
        Bukkit.getServicesManager().register(
                MinePayTradeOutDatedEvent.class,
                implementation,
                this,
                ServicePriority.Normal);
        // 完成交易
        Bukkit.getServicesManager().register(
                MinePaySuccessEvent.class,
                implementation,
                this,
                ServicePriority.Normal);
        // 获取充值排名
        List<RankItem> rankingList = MinePayApi.Info.getRankingList();
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onDisable() {
    }
}
