[![Maven Package](https://github.com/Gemesil/VortexUtilityCommands/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/Gemesil/VortexUtilityCommands/actions/workflows/maven-publish.yml)

# What is it?
Minecraft plugin that adds a bunch of useful admin commands.

## Commands
#### /god
Toggle god mode
- Permission: god.use_command

#### /mend
Fully heal and sate your hunger
- Permission: mend.use_command

#### /gmc
Change game mode to creative
- Permission: gmc.use_command

#### /gms
Change game mode to survival
- Permission: gms.use_command

#### /vanish
Hide yourself from other players.
Can also do "/vanish info" to check if you're in vanish.
- Permission: vanish.use_command

#### /fly
Toggle flight in survival mode
- Permission: fly.use_command

#### /inv [player name]:
Peak inside another player's inventory
- Permission: inv.use_command

#### /broadcast [message]:
Send a message to the entire server
- Permission: broadcast.use_command

#### /msg [player name] [message]:
Send a private message to another player
- Permission: msg.use_command

#### /op [player name]:
Give a player operator permissions. **This command only works from console! (to prevent abuse)**
- Permission: op.use_command

## Dependencies
- VortexLogger - Handles plugin-to-player messages like hotbar pop ups or chat notifications. [You can download VortexLogger here](https://github.com/Gemesil/VortexLogger/releases/tag/v1.0.0).
