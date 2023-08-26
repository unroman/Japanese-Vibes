package com.dolthhaven.japanesevibes.core.data.server;


import com.dolthhaven.japanesevibes.core.JapaneseVibes;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.dolthhaven.japanesevibes.core.JVBlocks.*;

public class JVLoot extends LootTableProvider {
    private final Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet> blockTables = Pair.of(Blocks::new, LootContextParamSets.BLOCK);

    public JVLoot(GatherDataEvent event) {
        super(event.getGenerator());
    }

    @Override
    protected @NotNull List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(this.blockTables);
    }

    @Override
    protected void validate(@NotNull Map<ResourceLocation, LootTable> map, @NotNull ValidationContext validationtracker) { }

    private static <T> Iterable<T> getContent(IForgeRegistry<T> entry) {
        return entry.getValues().stream().filter(i -> entry.getKey(i) != null
                && JapaneseVibes.MOD_ID.equals(Objects.requireNonNull
                (entry.getKey(i))
                .getNamespace()))
                .collect(Collectors.toSet());
    }

    private static class Blocks extends BlockLoot {
        @Override
        protected void addTables() {
            this.dropSelf(PAPER_LANTERN_WHITE.get());
            this.dropSelf(PAPER_LANTERN_BROWN.get());
            this.dropSelf(PAPER_LANTERN_GRAY.get());
            this.dropSelf(PAPER_LANTERN_LIGHT_GRAY.get());
            this.dropSelf(PAPER_LANTERN_RED.get());
            this.dropSelf(PAPER_LANTERN_ORANGE.get());
            this.dropSelf(PAPER_LANTERN_YELLOW.get());
            this.dropSelf(PAPER_LANTERN_LIME.get());
            this.dropSelf(PAPER_LANTERN_GREEN.get());
            this.dropSelf(PAPER_LANTERN_BLUE.get());
            this.dropSelf(PAPER_LANTERN_LIGHT_BLUE.get());
            this.dropSelf(PAPER_LANTERN_CYAN.get());
            this.dropSelf(PAPER_LANTERN_PURPLE.get());
            this.dropSelf(PAPER_LANTERN_MAGENTA.get());
            this.dropSelf(PAPER_LANTERN_PINK.get());
            this.dropSelf(PAPER_LANTERN_BLACK.get());
        }

        @Override
        public @NotNull Iterable<Block> getKnownBlocks() {
            return getContent(ForgeRegistries.BLOCKS);
        }
    }
}
