package ca.sullyq.ezchat.commands.subcommands;


import ca.sullyq.ezchat.ui.CreateNewTagUI;
import ca.sullyq.ezchat.ui.DashboardUI;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;


import javax.annotation.Nonnull;

public class UISubCommand extends AbstractPlayerCommand {

    public UISubCommand() {
        super("ui", "Open the plugin dashboard");
    }

    @Override
    protected boolean canGeneratePermission() {
        return true;
    }

    @Override
    protected void execute(
            @Nonnull CommandContext commandContext,
            @Nonnull Store<EntityStore> store,
            @Nonnull Ref<EntityStore> ref,
            @Nonnull PlayerRef playerRef,
            @Nonnull World world
    ) {
        try {
            Player player = store.getComponent(ref, Player.getComponentType());
            if (player == null) {
                commandContext.sendMessage(Message.raw("Error: Could not get Player component."));
                return;
            }

            DashboardUI dashboardPage = new DashboardUI(playerRef);
            player.getPageManager().openCustomPage(ref, store, dashboardPage);
        } catch (Exception e) {
            commandContext.sendMessage(Message.raw("Error opening Create New Tag Page: " + e.getMessage()));
        }
    }
}