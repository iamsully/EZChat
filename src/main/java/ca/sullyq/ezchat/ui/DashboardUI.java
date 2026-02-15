package ca.sullyq.ezchat.ui;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import java.util.logging.Level;

public class DashboardUI extends InteractiveCustomUIPage<DashboardUI.UIEventData> {
    private final HytaleLogger logger = HytaleLogger.forEnclosingClass();

    // Path relative to Common/UI/Custom/
    public static final String LAYOUT = "ezchat/Dashboard.ui";
    public static final String TAG_ENTRY_LAYOUT = "ezchat/DashboardEntry.ui";

    private final PlayerRef playerRef;

    public DashboardUI(@Nonnull PlayerRef playerRef) {
        super(playerRef, CustomPageLifetime.CanDismiss, UIEventData.CODEC);
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

        uiCommandBuilder.appendInline("#TagCards", "Group { LayoutMode: Left; Anchor: (Bottom: 0); }");

        uiCommandBuilder.append("#TagCards[0]", TAG_ENTRY_LAYOUT);

        uiCommandBuilder.set("#TagCards[0] #TagName.Text", "Test");

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating,
                "#TagCards[0] #TheButton",
                new EventData().append("Button", "TestButton"),
                false);

    }

    @Override
    public void handleDataEvent(
            @Nonnull Ref<EntityStore> ref,
            @Nonnull Store<EntityStore> store,
            @Nonnull UIEventData data
    ) {

        if (data.button.equals("TestButton")) {
            logger.at(Level.INFO).log("Clicked test");
        }
        this.sendUpdate();
    }

    /**
     * Event data class with codec for handling UI events.
     */
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