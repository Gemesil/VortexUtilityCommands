package gemesil.vortexutilitycommands;

import gemesil.vortexlogger.VortexLogger;
import gemesil.vortexutilitycommands.commands.*;
import gemesil.vortexutilitycommands.events.CancelNormalCommands;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class VortexUtilityCommands extends JavaPlugin {

    private static VortexUtilityCommands plugin;

    // Import custom logger plugin reference
    private static final VortexLogger vortexLogger = (VortexLogger) Bukkit.getServer().getPluginManager().getPlugin("VortexLogger");

    // Perms list
    // "vanish.see_vanished_players"
    // "vanish.use_command"
    // "mend.use_command"
    // "gms.use_command"
    // "gmc.use_command"
    // "fly.use_command"
    // "inv.use_command"

    @Override
    public void onEnable() {

        // Get plugin reference
        plugin = this;

        // Specific class references
        Vanish vanishClass = new Vanish();

        // Register commands to the plugin
        getCommand("god").setExecutor(new God());
        getCommand("mend").setExecutor(new Mend());
        getCommand("gmc").setExecutor(new Gmc());
        getCommand("gms").setExecutor(new Gms());
        getCommand("vanish").setExecutor(vanishClass);
        getCommand("fly").setExecutor(new Fly());
        getCommand("inv").setExecutor(new Inv());
        getCommand("msg").setExecutor(new Msg());
        getCommand("broadcast").setExecutor(new Broadcast());
        getCommand("op").setExecutor(new Op());

        // Register events
        Bukkit.getServer().getPluginManager().registerEvents(vanishClass, this);
        Bukkit.getServer().getPluginManager().registerEvents(new CancelNormalCommands(), this);
    }

    public static VortexUtilityCommands getPlugin() {
        return plugin;
    }

    public static VortexLogger getVortexLogger() {
        return vortexLogger;
    }
}
