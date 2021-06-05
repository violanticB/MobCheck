package net.violantic.mob;

import net.violantic.mob.listener.PlayerListener;
import net.violantic.mob.util.EntityUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class MobHandler {

    private JavaPlugin plugin;

    /*
     * Map of player (uuid) to target entity's id
     */
    private final Map<UUID, Integer> playerTargetMap;

    public MobHandler(JavaPlugin plugin) {
        this.plugin = plugin;
        this.playerTargetMap = new HashMap<>();

        registerListeners();
    }

    /**
     * Register listeners.
     */
    public void registerListeners() {
        plugin.getServer().getPluginManager().registerEvents(
                new PlayerListener(this), plugin
        );
    }

    /**
     * Set the observers target in the playerTargetMap
     * @param observer Player observing
     * @param target Entity that is being targeted
     */
    public void setTarget(Player observer, Entity target) {
        playerTargetMap.put(observer.getUniqueId(), target.getEntityId());
    }

    /**
     * Prints the name of the target entity.
     * @param player Observing player
     */
    public void checkTarget(Player player) {
        UUID uuid = player.getUniqueId();
        Optional<Entity> target = EntityUtil.getTargetEntity(player, 50);

        if(target.isPresent()) {
            Entity entity = target.get();

            if(!playerTargetMap.containsKey(uuid) || playerTargetMap.get(uuid) != entity.getEntityId()) {
                player.sendMessage(ChatColor.GRAY + "You are looking at a(n): " + EntityUtil.getTag(target.get()));
                setTarget(player, entity);
            }
        }

    }

}
