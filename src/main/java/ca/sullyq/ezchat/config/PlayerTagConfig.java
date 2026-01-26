package ca.sullyq.ezchat.config;

import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerTagConfig {

    public static final BuilderCodec<PlayerTagConfig> CODEC =
            BuilderCodec.<PlayerTagConfig>builder(PlayerTagConfig.class, PlayerTagConfig::new)
                    .append(new KeyedCodec<>("PlayerTags", BuilderCodec.STRING_ARRAY),
                            ((playerTagConfig, customTags) -> playerTagConfig.customTags = customTags),
                            (PlayerTagConfig::getCustomTags))
                    .add()
                    .build();

    private String[] customTags = {"[Superman]", "[Amazing]"};
}
