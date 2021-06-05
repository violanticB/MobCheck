package net.violantic.mob.util;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Optional;

public class EntityUtil {

    /**
     * Get the entity a player is looking at within r blocks.
     *
     * @param spectator Observing player
     * @param r Distance
     * @return Target entity
     */
    public static Optional<Entity> getTargetEntity(Player spectator, double r) {
        Location from = spectator.getEyeLocation();

        Entity target = null;
        for (Entity entity : spectator.getNearbyEntities(r, r, r)) {
            Location to = entity instanceof LivingEntity
                    ? ((LivingEntity) entity).getEyeLocation() : entity.getLocation();

            Vector v = to.toVector().subtract(from.toVector()).normalize();

            // Player is looking very close to the entity's position
            if(v.dot(from.getDirection()) > 0.9)
                target = entity;
        }

        return Optional.ofNullable(target);
    }

    public static String getTag(Entity e) {
        return ChatColor.YELLOW + e.getType().name().toLowerCase();
    }

}
