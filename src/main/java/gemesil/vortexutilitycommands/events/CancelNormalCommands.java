package gemesil.vortexutilitycommands.events;

import gemesil.vortexutilitycommands.VortexUtilityCommands;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CancelNormalCommands implements Listener {

    /*
    Cancels any command that tries to bypass the default usage.
    For example -> minecraft:op Gemesil (This will be blocked by the eventHandler!)
    For example -> vortexcustomitems:vitem get (This will be blocked by the eventHandler!)
     */

    @EventHandler
    public void onPlayerCommand (PlayerCommandPreprocessEvent e) {

        if (e.getMessage().contains(":")) {
            e.setCancelled(true);

            String commandMsg = e.getMessage().substring(0, e.getMessage().indexOf(':') + 1);

            VortexUtilityCommands.getVortexLogger().sendChat(e.getPlayer(),
                    "Sorry! Try using /" + e.getMessage().replace(commandMsg, "") + " instead!",
                    true);
        }
    }
}
