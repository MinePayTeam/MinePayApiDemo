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
import top.minepay.example.api.MinePayListener;

import java.util.List;

public final class MinePayApiDemo extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        // 注册 MinePay 交易事件监听
        MinePayListener listener = new MinePayListener();
        Bukkit.getPluginManager().registerEvents(listener, this);
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
            if (args[0].equals("kit")) {
                TradeInfo info = TradeInfo.createKit(
                        "订单号",
                        "礼包名字",
                        "Peter1303",
                        100, // 价格的单位是分
                        PaymentType.WECHAT // 支付方式 - 微信
                );
                MinePayApi.TradeController.start(info);
                return true;
            }
            if (args[0].equals("point")) {
                TradeInfo info = TradeInfo.createPoint(
                        "订单号",
                        "点券名字",
                        "Peter1303",
                        1, // 点券数量
                        PaymentType.WECHAT // 支付方式 - 微信
                );
                MinePayApi.TradeController.start(info);
                return true;
            }
            if (args[0].equals("rank")) {
                // 获取充值排名
                List<RankItem> rankingList = MinePayApi.Info.getRankingList();
                for (RankItem item : rankingList) {
                    Bukkit.getConsoleSender()
                            .sendMessage(item.getPlayerName() + ": " + item.getValue());
                }
            }
        }
        return true;
    }
}
