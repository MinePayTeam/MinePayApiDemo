package top.minepay.example.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import top.minepay.api.MinePayApi;
import top.minepay.bean.RankItem;
import top.minepay.bean.TradeInfo;
import top.minepay.common.enums.PaymentType;

import java.util.List;

/**
 * 指令执行
 *
 * @author Ranica
 * @date Created in 2022/8/19 20:01
 */
public class MinePayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender,
                             Command cmd,
                             String label,
                             String[] args) {
        if (args.length >= 1) {
            if (args[0].equals("kit")) {
                TradeInfo info = TradeInfo.createKit(
                        "订单号",
                        "测试金币",
                        "玩家名字",
                        PaymentType.WECHAT // 支付方式 - 微信
                );
                MinePayApi.TradeController.start(info);
                return true;
            }
            if (args[0].equals("point")) {
                TradeInfo info = TradeInfo.createPoint(
                        "订单号",
                        "点券名字",
                        "玩家名字",
                        100, // 点券数量
                        PaymentType.ALIPAY // 支付方式 - 微信
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
