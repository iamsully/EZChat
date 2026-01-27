package ca.sullyq.ezchat.config;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Getter
@Setter
public class PlayerConfig implements Component<EntityStore> {

    private String tag;

    public PlayerConfig() {
        this.tag = "";
    }

    public static final BuilderCodec<PlayerConfig> CODEC = BuilderCodec.builder(
                    PlayerConfig.class,
                    PlayerConfig::new
            )
            .append(new KeyedCodec<>("Tag", Codec.STRING),
                    (data, value) -> data.tag = value, data -> data.tag)
            .add()
            .build();

    @NullableDecl
    @Override
    public Component<EntityStore> clone() {
        PlayerConfig copy = new PlayerConfig();
        copy.tag = this.tag;
        return copy;
    }

}
