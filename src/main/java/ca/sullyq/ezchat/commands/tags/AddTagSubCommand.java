package ca.sullyq.ezchat.commands.tags;

import ca.sullyq.ezchat.config.TagConfig;
import ca.sullyq.ezchat.ui.CreateNewTagUI;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.Config;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;


public class AddTagSubCommand extends AbstractPlayerCommand {

    public AddTagSubCommand() {
        super("add", "Opens the gui to create a new tag");
    }

    @Override
    protected boolean canGeneratePermission() {
        return true;
    }

    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        try {
            Player player = store.getComponent(ref, Player.getComponentType());
            if (player == null) {
                commandContext.sendMessage(Message.raw("Error: Could not get Player component."));
                return;
            }

            CreateNewTagUI createNewTagPage = new CreateNewTagUI(playerRef);
            player.getPageManager().openCustomPage(ref, store, createNewTagPage);
        } catch (Exception e) {
            commandContext.sendMessage(Message.raw("Error opening Create New Tag Page: " + e.getMessage()));
        }
    }
}
