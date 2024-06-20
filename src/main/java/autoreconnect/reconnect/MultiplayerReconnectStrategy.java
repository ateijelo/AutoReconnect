package autoreconnect.reconnect;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.network.CookieStorage;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;

public class MultiplayerReconnectStrategy extends ReconnectStrategy {
    private final ServerInfo serverInfo;
    private final CookieStorage cookieStorage;

    public MultiplayerReconnectStrategy(ServerInfo serverInfo, CookieStorage cookieStorage) {
        this.serverInfo = serverInfo;
        this.cookieStorage = cookieStorage;
    }

    @Override
    public String getName() {
        return serverInfo.name;
    }

    /**
     * @see net.minecraft.client.QuickPlay#startMultiplayer(MinecraftClient, String)
     */
    @Override
    public void reconnect() {
        ConnectScreen.connect(
                new MultiplayerScreen(new TitleScreen()),
                MinecraftClient.getInstance(),
                ServerAddress.parse(serverInfo.address),
                serverInfo,
                false,
                cookieStorage);
    }
}
