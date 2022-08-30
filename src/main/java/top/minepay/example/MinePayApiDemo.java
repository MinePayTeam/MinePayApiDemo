package top.minepay.example;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import top.minepay.example.command.MinePayCommand;
import top.minepay.example.event.MinePayListener;

/**
 * 插件初始化
 *
 * @author Ranica
 * @date Created in 2022/8/19 20:00
 */
public final class MinePayApiDemo extends JavaPlugin {

    @Override
    public void onEnable() {
        // 注册 MinePay 交易事件监听
        MinePayListener listener = new MinePayListener();
        Bukkit.getPluginManager().registerEvents(listener, this);
        // 注册命令
        getCommand("mpdm").setExecutor(new MinePayCommand());
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onDisable() {
    }
}
