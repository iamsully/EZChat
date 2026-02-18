package ca.sullyq.ezchat.ui;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public class DashboardUI extends InteractiveCustomUIPage<DashboardUI.UIEventData> {
    private final HytaleLogger logger = HytaleLogger.forEnclosingClass();

    // Path relative to Common/UI/Custom/
    public static final String LAYOUT = "Pages/Dashboard/Dashboard.ui";
    public static final String TAG_ENTRY_LAYOUT = "Pages/Dashboard/DashboardEntry.ui";

    private final PlayerRef playerRef;

    public DashboardUI(@Nonnull PlayerRef playerRef, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, UIEventData.CODEC);
        this.playerRef = playerRef;
    }

    @Override
    public void build(
            @Nonnull Ref<EntityStore> ref,
            @Nonnull UICommandBuilder uiCommandBuilder,
            @Nonnull UIEventBuilder uiEventBuilder,
            @Nonnull Store<EntityStore> store
    ) {
        // Load base layout
        uiCommandBuilder.append(LAYOUT);

        uiCommandBuilder.appendInline("#DashboardCards", "Group { LayoutMode: Left; Anchor: (Bottom: 0); }");

        // Show All Tags
        uiCommandBuilder.append("#DashboardCards[0]", TAG_ENTRY_LAYOUT);
        uiCommandBuilder.set("#DashboardCards[0] #DashboardEntry.Text", "Create New Tag");
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating,
                "#DashboardCards[0] #TheButton",
                new EventData().append("Button", "CreateTag"),
                false);

        // Add Tag Button
        uiCommandBuilder.appendInline("#DashboardCards", "Group { LayoutMode: Left; Anchor: (Bottom: 0); }");
        uiCommandBuilder.append("#DashboardCards[1]", TAG_ENTRY_LAYOUT);
        uiCommandBuilder.set("#DashboardCards[1] #DashboardEntry.Text", "Coming soon...");
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating,
                "#DashboardCards[1] #TheButton",
                new EventData().append("Button", "ShowTags"),
                false);

        // Give tag button
        uiCommandBuilder.appendInline("#DashboardCards", "Group { LayoutMode: Left; Anchor: (Bottom: 0); }");
        uiCommandBuilder.append("#DashboardCards[2]", TAG_ENTRY_LAYOUT);
        uiCommandBuilder.set("#DashboardCards[2] #DashboardEntry.Text", "Coming soon...");
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating,
                "#DashboardCards[2] #TheButton",
                new EventData().append("Button", "GiveTag"),
                false);


    }

    @Override
    public void handleDataEvent(
            @Nonnull Ref<EntityStore> ref,
            @Nonnull Store<EntityStore> store,
            @Nonnull UIEventData data
    ) {
        var player = store.getComponent(ref, Player.getComponentType());
        if (player == null) return;
        switch (data.button) {
            case "ShowTags":
                break;
            case "CreateTag":
                player.getPageManager().openCustomPage(ref, store, new CreateNewTagUI(playerRef));
                break;
            case "GiveTag":
                break;
        }

        this.sendUpdate();
    }

    public static class UIEventData {
        static final String KEY_BUTTON = "Button";

        public static final BuilderCodec<UIEventData> CODEC = BuilderCodec.builder(
                        UIEventData.class, UIEventData::new
                )
                .append(new KeyedCodec<>(KEY_BUTTON, Codec.STRING), (e, v) -> e.button = v, e -> e.button)
                .add()
                .build();

        private String button;

        public UIEventData() {
        }
    }
}