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
public class TagConfig {

    private Map<String, String> tags = new HashMap<>();

    public TagConfig() {
//        this.playerTags.put("[Admin]", "#f84848");
    }

    public static final BuilderCodec<TagConfig> CODEC =
            BuilderCodec.<TagConfig>builder(TagConfig.class, TagConfig::new)

                    .append(new KeyedCodec<>("Tags", new MapCodec<>(BuilderCodec.STRING, HashMap::new, false)),
                            (data, value) -> data.tags = value,
                            data -> data.tags)
                    .add()
                    .build();

}
