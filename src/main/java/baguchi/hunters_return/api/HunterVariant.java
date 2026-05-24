package baguchi.hunters_return.api;

import baguchi.hunters_return.data.resources.registries.HunterVariants;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.world.level.biome.Biome;

import java.util.Objects;
import java.util.Optional;

public class HunterVariant {
    public static final Codec<HunterVariant> DIRECT_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Identifier.CODEC.fieldOf("texture").forGetter(p_335261_ -> p_335261_.texture),
                            Identifier.CODEC.optionalFieldOf("texture_old").forGetter(p_335261_ -> p_335261_.textureOld),
                            RegistryCodecs.homogeneousList(Registries.BIOME).fieldOf("biomes").forGetter(HunterVariant::biomes)
                    )
                    .apply(instance, HunterVariant::new)
    );
    public static final Codec<Holder<HunterVariant>> CODEC = RegistryFileCodec.create(HunterVariants.HUNTER_VARIANT_KEY, DIRECT_CODEC);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<HunterVariant>> STREAM_CODEC = ByteBufCodecs.holderRegistry(HunterVariants.HUNTER_VARIANT_KEY);
    private final Identifier texture;
    private final Identifier textureFull;
    private final Optional<Identifier> textureOld;
    private final Optional<Identifier> textureOldFull;
    private final HolderSet<Biome> biomes;

    public HunterVariant(Identifier p_332712_, Optional<Identifier> oldTexture, HolderSet<Biome> p_332717_) {
        this.texture = p_332712_;
        this.textureFull = fullTextureId(p_332712_);
        this.textureOld = oldTexture;
        this.textureOldFull = oldTexture.isPresent() ? Optional.of(fullTextureId(oldTexture.get())) : Optional.empty();
        this.biomes = p_332717_;
    }

    private static Identifier fullTextureId(Identifier p_336042_) {
        return p_336042_.withPath(p_335262_ -> "textures/" + p_335262_ + ".png");
    }

    public Identifier texture() {
        return this.textureFull;
    }

    public Optional<Identifier> textureOld() {
        return this.textureOldFull;
    }

    public HolderSet<Biome> biomes() {
        return this.biomes;
    }

    @Override
    public boolean equals(Object p_332811_) {
        if (p_332811_ == this) {
            return true;
        } else {
            return !(p_332811_ instanceof HunterVariant hunterVariant)
                    ? false
                    : Objects.equals(this.texture, hunterVariant.texture)
                    && Objects.equals(this.biomes, hunterVariant.biomes);
        }
    }

    @Override
    public int hashCode() {
        int i = 1;
        i = 31 * i + this.texture.hashCode();
        i = 31 * i + this.textureOld.hashCode();
        return 31 * i + this.biomes.hashCode();
    }
}