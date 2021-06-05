package net.violantic.mob.listener;

import net.violantic.mob.MobHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {

    private final MobHandler mobHandler;
    public PlayerListener(MobHandler mobHandler) {
        this.mobHandler = mobHandler;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        mobHandler.checkTarget(event.getPlayer());
    }

}
