package net.violantic.mob;

import org.bukkit.plugin.java.JavaPlugin;

public class MobPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new MobHandler(this);
    }

}
