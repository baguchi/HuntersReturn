package baguchan.hunters_return.api;

import baguchan.hunters_return.data.resources.registries.HunterVariants;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.Objects;
import java.util.Optional;

public class HunterVariant {
    public static final Codec<HunterVariant> DIRECT_CODEC = RecordCodecBuilder.create(
            p_332779_ -> p_332779_.group(
                            ResourceLocation.CODEC.fieldOf("texture").forGetter(p_335261_ -> p_335261_.texture),
                            ResourceLocation.CODEC.optionalFieldOf("texture_old").forGetter(p_335261_ -> p_335261_.textureOld),
                            RegistryCodecs.homogeneousList(Registries.BIOME).fieldOf("biomes").forGetter(HunterVariant::biomes)
                    )
                    .apply(p_332779_, HunterVariant::new)
    );
    public static final Codec<Holder<HunterVariant>> CODEC = RegistryFileCodec.create(HunterVariants.HUNTER_VARIANT_KEY, DIRECT_CODEC);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<HunterVariant>> STREAM_CODEC = ByteBufCodecs.holderRegistry(HunterVariants.HUNTER_VARIANT_KEY);
    private final ResourceLocation texture;
    private final ResourceLocation textureFull;
    private final Optional<ResourceLocation> textureOld;
    private final Optional<ResourceLocation> textureOldFull;
    private final HolderSet<Biome> biomes;

    public HunterVariant(ResourceLocation p_332712_, Optional<ResourceLocation> oldTexture, HolderSet<Biome> p_332717_) {
        this.texture = p_332712_;
        this.textureFull = fullTextureId(p_332712_);
        this.textureOld = oldTexture;
        this.textureOldFull = oldTexture.isPresent() ? Optional.of(fullTextureId(oldTexture.get())) : Optional.empty();
        this.biomes = p_332717_;
    }

    private static ResourceLocation fullTextureId(ResourceLocation p_336042_) {
        return p_336042_.withPath(p_335262_ -> "textures/" + p_335262_ + ".png");
    }

    public ResourceLocation texture() {
        return this.textureFull;
    }

    public Optional<ResourceLocation> textureOld() {
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