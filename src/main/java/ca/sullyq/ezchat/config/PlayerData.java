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
public class PlayerData implements Component<EntityStore> {

    private String tag;

    public PlayerData() {
        this.tag = "";
    }

    public static final BuilderCodec<PlayerData> CODEC = BuilderCodec.builder(
                    PlayerData.class,
                    PlayerData::new
            )
            .addField(new KeyedCodec<>("Tag", Codec.STRING),
                    (data, value) -> data.tag = value, data -> data.tag)
            .build();

    @NullableDecl
    @Override
    public Component<EntityStore> clone() {
        PlayerData copy = new PlayerData();
        copy.tag = this.tag;
        return copy;
    }

}
