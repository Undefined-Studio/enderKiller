package com.udstu.enderkiller;

import com.udstu.enderkiller.command.CommandEk;
import com.udstu.enderkiller.enumeration.RoomStatus;
import com.udstu.enderkiller.listener.*;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by yst on 2016/7/31.
 * EnderKiller main class
 */
public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getCommand("ek").setExecutor(new CommandEk());
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerPortalListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new DragonDeathListener(), this);

        //传入主类(指本类)引用至Config,并执行一次配置文件载入
        Config.setMainClass(this);
        Config.load();
    }

    @Override
    public void onDisable() {
        for (Room room : Lobby.getRoomList()) {
            if (room.getRoomStatus() == RoomStatus.inGame) {
                room.getGame().over();
            }
        }
    }
}
