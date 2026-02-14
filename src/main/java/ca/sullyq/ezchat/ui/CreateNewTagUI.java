package ca.sullyq.ezchat.ui;

import ca.sullyq.ezchat.helpers.MessageHelper;
import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.protocol.packets.interface_.NotificationStyle;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.NotificationUtil;

import javax.annotation.Nonnull;
import java.util.logging.Level;

public class CreateNewTagUI extends InteractiveCustomUIPage<CreateNewTagUI.UIEventData> {
    // Path relative to Common/UI/Custom/
    public static final String LAYOUT = "ezchat/CreateNewTag.ui";

    private final HytaleLogger logger = HytaleLogger.forEnclosingClass();
    private final PlayerRef playerRef;

    private final String startingColorTag = "<color:%s>";
    private final String endingColorTag = "</color>";
    private final String startingBoldTag = "<b>";
    private final String endingBoldTag = "</b>";
    private final String startingItalicTag = "<i>";
    private final String endingItalicTag = "</i>";
    private final String startingMonospaceTag = "<mono>";
    private final String endingMonospaceTag = "</mono>";


    private String tagName;
    private String tagColor;
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
            @Nonnull UICommandBuilder uiCommandBuilder,
            @Nonnull UIEventBuilder eventBuilder,
            @Nonnull Store<EntityStore> store
    ) {
        // Load base layout
        uiCommandBuilder.append(LAYOUT);

        uiCommandBuilder.set("#TagColorPicker.Value", String.format("#%06X", (0xFF0000)));

        // Bind refresh button
        eventBuilder.addEventBinding(
                CustomUIEventBindingType.Activating,
                "#SaveButton",
                new EventData().append("Action", "save"),
                false
        );

        // Bind text fields
        eventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged,
                "#TagNameInput",
                EventData.of("@TagName", "#TagNameInput.Value"),
                false);

        eventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged,
                "#TagColorPicker",
                EventData.of("@TagColorPicker", "#TagColorPicker.Value"),
                false);


        // Bind checkboxes
        eventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged,
                "#TagBoldCheckBox #CheckBox",
                new EventData().append("CheckBox", "Bold"),
                false
        );

        eventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged,
                "#TagItalicCheckBox #CheckBox",
                new EventData().append("CheckBox", "Italic"),
                false
        );

        eventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged,
                "#TagMonospaceCheckBox #CheckBox",
                new EventData().append("CheckBox", "Monospace"),
                false
        );

        // Bind close button
        eventBuilder.addEventBinding(
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

        if (data.tagColorPicker != null) {
            logger.at(Level.INFO).log(data.tagColorPicker);
        }

        if (data.tagName != null) {
            this.tagName = data.tagName;
        }

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
        }

        this.sendUpdate();

        if (data.action == null) return;

        switch (data.action) {
            case "save":
                createTag();
                break;
            case "close":
                this.close();
                break;

        }
        this.sendUpdate();
    }

    private void createTag() {
        if (this.tagName == null || this.tagName.isEmpty()) {
            NotificationUtil.sendNotification(playerRef.getPacketHandler(), "The tag name cannot be empty.", NotificationStyle.Danger);
            return;
        }

        String tag = this.tagName;
        StringBuilder tagStringBuilder = new StringBuilder(tag);

        if (isBold) {
            tagStringBuilder.insert(0, startingBoldTag);
            tagStringBuilder.insert(tag.length() + startingBoldTag.length(), endingBoldTag);
        }

        if (isItalic) {
            tagStringBuilder.insert(0, startingItalicTag);
            tagStringBuilder.insert(tagStringBuilder.length(), endingItalicTag);
        }

        if (isMonospace) {
            tagStringBuilder.insert(0, startingMonospaceTag);
            tagStringBuilder.insert(tagStringBuilder.length(), endingMonospaceTag);
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
                .append(new KeyedCodec<>("@TagName", Codec.STRING), (e, v) -> e.tagName = v, e -> e.tagName)
                .add()
                .append(new KeyedCodec<>("@TagColorPicker", Codec.STRING), (e, v) -> e.tagColorPicker = v, e -> e.tagColorPicker)
                .add()
                .build();

        private String action;
        private String checkbox;
        private String tagName;
        private String tagColorPicker;

        public UIEventData() {
        }
    }
}