package gemesil.vortexutilitycommands;

import gemesil.vortexlogger.VortexLogger;
import gemesil.vortexutilitycommands.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class VortexUtilityCommands extends JavaPlugin {

    // Import custom logger plugin reference
    private final VortexLogger vortexLogger = (VortexLogger) Bukkit.getServer().getPluginManager().getPlugin("VortexLogger");

    // Perms list
    // "vanish.see_vanished_players"
    // "vanish.use_command"
    // "mend.use_command"
    // "gms.use_command"
    // "gmc.use_command"
    // "fly.use_command"

    @Override
    public void onEnable() {

        // Specific class references
        Vanish vanishClass = new Vanish(this, vortexLogger);

        // Register commands to the plugin
        getCommand("god").setExecutor(new God(vortexLogger));
        getCommand("mend").setExecutor(new Mend(vortexLogger));
        getCommand("gmc").setExecutor(new Gmc(vortexLogger));
        getCommand("gms").setExecutor(new Gms(vortexLogger));
        getCommand("vanish").setExecutor(vanishClass);
        getCommand("fly").setExecutor(new Fly(vortexLogger));
        getCommand("inv").setExecutor(new Inv(vortexLogger));

        // Register events
        Bukkit.getServer().getPluginManager().registerEvents(vanishClass, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
