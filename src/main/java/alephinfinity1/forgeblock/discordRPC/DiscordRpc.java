package alephinfinity1.forgeblock.discordRPC;

import alephinfinity1.forgeblock.ForgeBlock;
import club.minnced.discord.rpc.*;

public class DiscordRpc {

  private final DiscordRPC lib = DiscordRPC.INSTANCE;
  private static final DiscordRpc INSTANCE = new DiscordRpc();

  public static DiscordRpc getInstance() {
    return INSTANCE;
  }

  public void startRpc() {
    DiscordEventHandlers handlers = new DiscordEventHandlers();
    lib.Discord_Initialize("772459200496140318", handlers, true, null);
    DiscordRichPresence presence = new DiscordRichPresence();
    presence.startTimestamp = System.currentTimeMillis() / 1000; // epoch second
    presence.details = "Playing ForgeBlock " + ForgeBlock.VERSION;
    presence.largeImageKey = "fb_logo";

    lib.Discord_UpdatePresence(presence);
    new Thread(() -> {
      while (!Thread.currentThread().isInterrupted()) {
        lib.Discord_RunCallbacks();
        try {
          Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }
      }
    }, "FBDiscordRpc").start();
  }
}
