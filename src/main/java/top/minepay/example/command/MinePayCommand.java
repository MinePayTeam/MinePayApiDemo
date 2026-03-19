package top.minepay.example.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import top.minepay.api.MinePayApiService;
import top.minepay.api.builder.KitBuilder;
import top.minepay.api.builder.PointBuilder;
import top.minepay.bean.RankItem;
import top.minepay.bean.RechargeLogItem;
import top.minepay.common.enums.PaymentType;

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

        // 以下指令不能在控制台输入
        if (!(sender instanceof Player)) {
            sender.sendMessage("该指令不能在控制台输入");
            return true;
        }
        Player player = (Player) sender;

        if (args.length >= 1) {

            RegisteredServiceProvider<MinePayApiService> provider = Bukkit
                    .getServicesManager()
                    .getRegistration(MinePayApiService.class);
            MinePayApiService minePayApiService = provider.getProvider();

            if (args[0].equals("kit")) {
                // 构建礼包订单
                KitBuilder kitBuilder = minePayApiService.kit()
                        .remark("订单备注")
                        .paymentType(PaymentType.WECHAT)
                        .kitName("测试金币")
                        .player(player);
                // 发起订单
                minePayApiService.order()
                        // 支持异步
                        .createAsync(kitBuilder)
                        .whenComplete(($, throwable) -> {
                            if (throwable != null) {
                                Bukkit.getConsoleSender()
                                        .sendMessage("订单创建失败: " + throwable.getMessage());
                            }
                        });
                return true;
            }
            if (args[0].equals("point")) {
                // 构建点券订单
                PointBuilder pointBuilder = minePayApiService.point()
                        .remark("订单备注")
                        .paymentType(PaymentType.ALIPAY)
                        .player(player)
                        .quantity(100); // 金额 （单位：分）
                // 发起订单
                minePayApiService.order()
                        // 同步发起
                        .create(pointBuilder);
                return true;
            }
            if (args[0].equals("rank")) {
                // 获取充值排名
                minePayApiService.rank()
                        // 异步获取 10 个排行榜的数据
                        .getMaxAsync(10)
                        .whenComplete((rankItems, throwable) -> {
                            for (RankItem item : rankItems) {
                                player.sendMessage(item.getPlayerName() + ": " + item.getValue());
                            }
                        });
            }
            if (args[0].equals("consume")) {
                minePayApiService.consume()
                        // 异步获取累充数据
                        .getConsumeAsync(player)
                        .whenComplete((consume, throwable) ->
                                player.sendMessage(player.getName() + ":" + consume.toString()));

            }
            if (args[0].equals("log")) {
                minePayApiService.rechargeLog()
                        // 异步获取充值数据
                        .getAsync(player)
                        .whenComplete((logs, throwable) -> {
                            for (RechargeLogItem log : logs) {
                                player.sendMessage(log.toString());
                            }
                        });
            }
        }
        return true;
    }
}
