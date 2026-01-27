package ca.sullyq.ezchat.config;

import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.codecs.map.MapCodec;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
public class PlayerConfig {
    private Map<String, String> playerTags = new HashMap<>();

    public PlayerConfig() {
    }

    public static final BuilderCodec<PlayerConfig> CODEC =
            BuilderCodec.<PlayerConfig>builder(PlayerConfig.class, PlayerConfig::new)

                    .append(new KeyedCodec<>("PlayerTags", new MapCodec<>(BuilderCodec.STRING, HashMap::new, false)),
                            (data, value) -> data.playerTags = value,
                            data -> data.playerTags)
                    .add()
                    .build();
}
