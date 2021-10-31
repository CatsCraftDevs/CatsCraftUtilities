package utilities.catscraft;

import org.bukkit.plugin.java.JavaPlugin;
import utilities.catscraft.event.ArmorListener;
import utilities.catscraft.event.DispenserArmorListener;

public final class CatsCraftUtilities extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new ArmorListener(getConfig().getStringList("blocked")), this);
        getServer().getPluginManager().registerEvents(new DispenserArmorListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
