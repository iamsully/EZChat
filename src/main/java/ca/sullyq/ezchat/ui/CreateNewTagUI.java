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

public class CreateNewTagUI extends InteractiveCustomUIPage<CreateNewTagUI.UIEventData> {
    // Path relative to Common/UI/Custom/
    public static final String LAYOUT = "ezchat/CreateNewTag.ui";

    private final HytaleLogger logger = HytaleLogger.forEnclosingClass();
    private final PlayerRef playerRef;
    private final String startingBoldTag = "<b>";
    private final String endingBoldTag = "</b>";


    private boolean isBold;
    private boolean isItalic;
    private boolean isMonospace;


    public CreateNewTagUI(@Nonnull PlayerRef playerRef) {
        super(playerRef, CustomPageLifetime.CanDismiss, UIEventData.CODEC);
        this.playerRef = playerRef;
    }

    @Override
    public void build(
            @Nonnull Ref<EntityStore> ref,
            @Nonnull UICommandBuilder cmd,
            @Nonnull UIEventBuilder evt,
            @Nonnull Store<EntityStore> store
    ) {
        // Load base layout
        cmd.append(LAYOUT);

        // Bind refresh button
        evt.addEventBinding(
                CustomUIEventBindingType.Activating,
                "#SaveButton",
                new EventData().append("Action", "save"),
                false
        );

        evt.addEventBinding(CustomUIEventBindingType.ValueChanged,
                "#TagBoldCheckBox",
                new EventData().append("CheckBox", "Bold"),
                false
        );

        evt.addEventBinding(CustomUIEventBindingType.ValueChanged,
                "#TagItalicCheckBox",
                new EventData().append("CheckBox", "Italic"),
                false
        );

        evt.addEventBinding(CustomUIEventBindingType.ValueChanged,
                "#TagMonospaceCheckBox #CheckBox",
                new EventData().append("CheckBox", "Monospace"),
                false
        );

        // Bind close button
        evt.addEventBinding(
                CustomUIEventBindingType.Activating,
                "#CloseButton",
                new EventData().append("Action", "close"),
                false
        );
    }

    @Override
    public void handleDataEvent(
            @Nonnull Ref<EntityStore> ref,
            @Nonnull Store<EntityStore> store,
            @Nonnull UIEventData data
    ) {
        super.handleDataEvent(ref, store, data);

        if (data.checkbox != null) {
            switch (data.checkbox) {
                case "Bold":
                    this.isBold = !this.isBold;
                    break;
                case "Italic":
                    this.isItalic = !this.isItalic;
                    break;

                case "Monospace":
                    this.isMonospace = !this.isMonospace;
                    break;

            }
            this.sendUpdate();
        }

        if (data.action == null) return;

        switch (data.action) {
            case "save":
                logger.at(Level.INFO).log(this.isBold ? " Yes, its bold" : "Nope, not bold");
                logger.at(Level.INFO).log(this.isMonospace ? " Yes, its monospace" : "Nope, not monospace");
                logger.at(Level.INFO).log(this.isItalic ? " Yes, its italic" : "Nope, not italic");

                createTag(this.isBold, this.isItalic, this.isMonospace);
                break;
            case "close":
                this.close();
                break;

        }
        this.sendUpdate();
    }

    private void createTag(boolean isBold, boolean isItalic, boolean isMonospace) {
        String tag = "TagName";
        StringBuilder tagStringBuilder = new StringBuilder(tag);

        if (isBold) {
            tagStringBuilder.insert(0, startingBoldTag);
            tagStringBuilder.insert(tag.length() + startingBoldTag.length(), endingBoldTag);
        }

        logger.at(Level.INFO).log(tagStringBuilder.toString());

    }

    /**
     * Event data class with codec for handling UI events.
     */
    public static class UIEventData {
        public static final BuilderCodec<UIEventData> CODEC = BuilderCodec.builder(
                        UIEventData.class, UIEventData::new
                )
                .append(new KeyedCodec<>("Action", Codec.STRING), (e, v) -> e.action = v, e -> e.action)
                .add()
                .append(new KeyedCodec<>("CheckBox", Codec.STRING), (e, v) -> e.checkbox = v, e -> e.checkbox)
                .add()

                .build();

        private String action;
        private String checkbox;

        public UIEventData() {
        }
    }
}