package top.minepay.example;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import top.minepay.api.MinePayApi;
import top.minepay.bean.RankItem;
import top.minepay.bean.TradeInfo;
import top.minepay.enums.PaymentType;
import top.minepay.enums.TradeType;
import top.minepay.example.api.MinePayListener;

import java.util.List;

public final class MinePayApiDemo extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        // 注册 MinePay 交易事件监听
        MinePayListener listener = new MinePayListener();
        Bukkit.getPluginManager().registerEvents(listener, this);
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

    @Override
    public boolean onCommand(CommandSender sender,
                             Command cmd,
                             String label,
                             String[] args) {
        if (args.length >= 1) {
            if (args[0].equals("start")) {
                TradeInfo info = TradeInfo.create(
                        "订单号",
                        "礼包名字",
                        "Peter1303",
                        100, // 价格的单位是分
                        TradeType.POINT,
                        PaymentType.WECHAT // 支付方式 - 微信
                );
                MinePayApi.TradeController.start(info);
            }
        }
        return true;
    }
}
