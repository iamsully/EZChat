package ca.sullyq.ezchat.ui;

import ca.sullyq.ezchat.EZChat;
import ca.sullyq.ezchat.config.TagConfig;
import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.protocol.packets.interface_.NotificationStyle;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.Config;
import com.hypixel.hytale.server.core.util.NotificationUtil;

import javax.annotation.Nonnull;
import java.util.logging.Level;

public class CreateNewTagUI extends InteractiveCustomUIPage<CreateNewTagUI.UIEventData> {
    // Path relative to Common/UI/Custom/
    public static final String LAYOUT = "Pages/Tags/CreateNewTag.ui";

    private final Config<TagConfig> tagConfig = EZChat.getInstance().getTagConfig();
    private final HytaleLogger logger = HytaleLogger.forEnclosingClass();
    private final PlayerRef playerRef;

    private final int MAX_TAG_ID_LENGTH = 10;
    private final int MAX_TAG_NAME_LENGTH = 20;

    private String startingColorTag = "<color:%s>";
    private final String endingColorTag = "</color>";
    private final String startingUnderlineTag = "<u>";
    private final String endingUnderlineTag = "</u>";
    private final String startingBoldTag = "<b>";
    private final String endingBoldTag = "</b>";
    private final String startingItalicTag = "<i>";
    private final String endingItalicTag = "</i>";
    private final String startingMonospaceTag = "<mono>";
    private final String endingMonospaceTag = "</mono>";

    private String tagId;
    private String tagName;
    private String tagColor;
    private boolean isBold;
    private boolean isUnderline;
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
            @Nonnull UIEventBuilder uiEventBuilder,
            @Nonnull Store<EntityStore> store
    ) {
        // Load base layout
        uiCommandBuilder.append(LAYOUT);

        uiCommandBuilder.set("#TagColorPicker.Value", String.format("#%06X", (0xFF0000)));

        tagColor = String.format("#%06X", (0xFF0000));

        uiEventBuilder.addEventBinding(
                CustomUIEventBindingType.Activating,
                "#BackButton",
                new EventData().append("Action", "BackButton"),
                false);

        // Bind save button
        uiEventBuilder.addEventBinding(
                CustomUIEventBindingType.Activating,
                "#SaveButton",
                new EventData().append("Action", "save"),
                false
        );

        // Bind text fields
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged,
                "#TagNameInput",
                EventData.of("@TagName", "#TagNameInput.Value"),
                false);

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged,
                "#TagIdInput",
                EventData.of("@TagId", "#TagIdInput.Value"),
                false);


        uiEventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged,
                "#TagColorPicker",
                EventData.of("@TagColorPicker", "#TagColorPicker.Value"),
                false);


        // Bind checkboxes
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged,
                "#TagBoldCheckBox #CheckBox",
                new EventData().append("CheckBox", "Bold"),
                false
        );

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged,
                "#TagItalicCheckBox #CheckBox",
                new EventData().append("CheckBox", "Italic"),
                false
        );

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged,
                "#TagMonospaceCheckBox #CheckBox",
                new EventData().append("CheckBox", "Monospace"),
                false
        );

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged,
                "#TagUnderlineCheckBox #CheckBox",
                new EventData().append("CheckBox", "Underline"),
                false
        );

        // Bind close button
        uiEventBuilder.addEventBinding(
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
            tagColor = data.tagColorPicker;

            logger.at(Level.INFO).log(startingColorTag);
        }

        if (data.tagName != null) {
            this.tagName = data.tagName;
        }

        if (data.tagId != null) {
            this.tagId = data.tagId;
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
                case "Underline":
                    this.isUnderline = !this.isUnderline;
                    break;
            }
        }

        if (data.action == null) return;

        var player = store.getComponent(ref, Player.getComponentType());
        if (player == null) return;

        switch (data.action) {
            case "save":
                createTag();
                break;
            case "close":
                this.close();
                break;
            case "BackButton":
                player.getPageManager().openCustomPage(ref, store, new DashboardUI(playerRef, CustomPageLifetime.CanDismiss));
                break;

        }
        this.sendUpdate();
    }

    private void createTag() {
        if (tagName == null || tagName.isEmpty()) {
            NotificationUtil.sendNotification(playerRef.getPacketHandler(),
                    "The tag cannot be empty.",
                    NotificationStyle.Danger);
            return;
        }

        if (tagName.contains("<") || tagName.contains(">")) {
            NotificationUtil.sendNotification(playerRef.getPacketHandler(),
                    "The tag cannot contain < angle brackets >",
                    NotificationStyle.Danger);
            return;
        }

        if (tagName.length() > MAX_TAG_NAME_LENGTH) {
            NotificationUtil.sendNotification(playerRef.getPacketHandler(),
                    "The tag cannot be longer than " + MAX_TAG_NAME_LENGTH + " characters",
                    NotificationStyle.Danger);
            return;
        }

        if (tagId == null || tagId.isEmpty()) {
            NotificationUtil.sendNotification(playerRef.getPacketHandler(),
                    "The tag name / id cannot be empty.",
                    NotificationStyle.Danger);
            return;
        }

        if (!tagId.matches("[a-zA-Z]+")) {
            NotificationUtil.sendNotification(playerRef.getPacketHandler(),
                    "The tag name / id can only contain characters",
                    NotificationStyle.Danger);
            return;
        }

        if (tagId.length() > MAX_TAG_ID_LENGTH) {
            NotificationUtil.sendNotification(playerRef.getPacketHandler(),
                    "The tag id cannot be longer than " + MAX_TAG_ID_LENGTH + " characters",
                    NotificationStyle.Danger);
            return;
        }

        if (tagColor.isEmpty()) {
            NotificationUtil.sendNotification(playerRef.getPacketHandler(),
                    "Don't forget to pick a color!",
                    NotificationStyle.Danger);
            return;
        }

        StringBuilder tagStringBuilder = new StringBuilder(tagName);

        if (isBold) {
            tagStringBuilder.insert(0, startingBoldTag);
            tagStringBuilder.insert(tagStringBuilder.length(), endingBoldTag);
        }

        if (isItalic) {
            tagStringBuilder.insert(0, startingItalicTag);
            tagStringBuilder.insert(tagStringBuilder.length(), endingItalicTag);
        }

        if (isMonospace) {
            tagStringBuilder.insert(0, startingMonospaceTag);
            tagStringBuilder.insert(tagStringBuilder.length(), endingMonospaceTag);
        }

        if (isUnderline) {
            tagStringBuilder.insert(0, startingUnderlineTag);
            tagStringBuilder.insert(tagStringBuilder.length(), endingUnderlineTag);
        }

        startingColorTag = startingColorTag.formatted(tagColor.substring(0, 7));

        tagStringBuilder.insert(0, startingColorTag);
        tagStringBuilder.insert(tagStringBuilder.length(), endingColorTag);

        saveTagToConfig(tagStringBuilder.toString());
    }

    private void saveTagToConfig(String newTag) {
        TagConfig cfg = this.tagConfig.get();
        if (cfg == null) {
            NotificationUtil.sendNotification(playerRef.getPacketHandler(),
                    "Couldn't find the tag config file. Please try again",
                    NotificationStyle.Danger);
            return;
        }

        if (cfg.getTags().containsKey(tagId)) {
            NotificationUtil.sendNotification(playerRef.getPacketHandler(),
                    "There is already a tag with this ID. Please try again",
                    NotificationStyle.Danger);
            return;
        }

        cfg.getTags().put(tagId, newTag);
        this.tagConfig.save();

        NotificationUtil.sendNotification(playerRef.getPacketHandler(),
                "Successfully created the " + tagId + " tag and reloaded the tag config",
                NotificationStyle.Success);

        this.close();
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
                .append(new KeyedCodec<>("@TagId", Codec.STRING), (e, v) -> e.tagId = v, e -> e.tagId)
                .add()
                .append(new KeyedCodec<>("@TagColorPicker", Codec.STRING), (e, v) -> e.tagColorPicker = v, e -> e.tagColorPicker)
                .add()
                .build();

        private String action;
        private String checkbox;
        private String tagName;
        private String tagId;
        private String tagColorPicker;

        public UIEventData() {
        }
    }
}