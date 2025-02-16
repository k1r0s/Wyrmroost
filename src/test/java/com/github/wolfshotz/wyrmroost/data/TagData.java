package com.github.wolfshotz.wyrmroost.data;

import com.github.wolfshotz.wyrmroost.Wyrmroost;
import com.github.wolfshotz.wyrmroost.registry.WRBlocks;
import com.github.wolfshotz.wyrmroost.registry.WREntities;
import com.github.wolfshotz.wyrmroost.registry.WRItems;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.EntityTypeTagsProvider;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class TagData
{
    // note block tags need to run before item tags
    static void provide(DataGenerator gen, ExistingFileHelper fileHelper)
    {
        BlockData blockGen = new BlockData(gen, fileHelper);
        gen.addProvider(blockGen);
        gen.addProvider(new ItemData(gen, blockGen, fileHelper));
        gen.addProvider(new EntityData(gen, fileHelper));
    }

    private static class ItemData extends ItemTagsProvider
    {
        private final BlockData blockProvider;

        public ItemData(DataGenerator gen, BlockData blockGen, ExistingFileHelper fileHelper)
        {
            super(gen, blockGen, Wyrmroost.MOD_ID, fileHelper);
            this.blockProvider = blockGen;
        }

        @Override
        protected void addTags()
        {
            WRBlocks.Tags.ITEM_BLOCK_TAGS.forEach(this::copy);
            blockProvider.tags.forEach(this::copy);

            tag(Tags.Items.EGGS).add(WRItems.DRAGON_EGG.get());

            tag(ItemTags.PIGLIN_LOVED).add(WRItems.DRAGON_ARMOR_GOLD.get());

            tag(Tags.Items.GEMS).addTag(WRItems.Tags.GEMS_GEODE);
            tag(WRItems.Tags.GEMS_GEODE).add(WRItems.BLUE_GEODE.get(), WRItems.RED_GEODE.get(), WRItems.PURPLE_GEODE.get());

            tag(WRItems.Tags.DRAGON_MEATS).add(WRItems.RAW_LOWTIER_MEAT.get(), WRItems.COOKED_LOWTIER_MEAT.get(), WRItems.RAW_COMMON_MEAT.get(), WRItems.COOKED_COMMON_MEAT.get(), WRItems.RAW_APEX_MEAT.get(), WRItems.COOKED_APEX_MEAT.get(), WRItems.RAW_BEHEMOTH_MEAT.get(), WRItems.COOKED_BEHEMOTH_MEAT.get());

            tag(Tags.Items.INGOTS).addTag(WRItems.Tags.INGOTS_PLATINUM);
            tag(WRItems.Tags.INGOTS_PLATINUM).add(WRItems.PLATINUM_INGOT.get());

            tag(ItemTags.ARROWS).add(WRItems.BLUE_GEODE_ARROW.get(), WRItems.RED_GEODE_ARROW.get(), WRItems.PURPLE_GEODE_ARROW.get());

            tag(ItemTags.BEACON_PAYMENT_ITEMS).addTag(WRItems.Tags.GEMS_GEODE);
        }
    }

    private static class BlockData extends BlockTagsProvider
    {
        final Map<ITag.INamedTag<Block>, ITag.INamedTag<Item>> tags = new HashMap<>();

        public BlockData(DataGenerator generatorIn, @Nullable ExistingFileHelper existingFileHelper)
        {
            super(generatorIn, Wyrmroost.MOD_ID, existingFileHelper);
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void addTags()
        {
            tag(Tags.Blocks.DIRT).add(WRBlocks.MULCH.get(), WRBlocks.FROSTED_GRASS.get());
            tag(BlockTags.BEACON_BASE_BLOCKS).addTags(WRBlocks.Tags.STORAGE_BLOCKS_GEODE, WRBlocks.Tags.STORAGE_BLOCKS_PLATINUM);
            cloneToItem(Tags.Blocks.ORES, Tags.Items.ORES).addTags(WRBlocks.Tags.ORES_GEODE, WRBlocks.Tags.ORES_PLATINUM);
            tag(WRBlocks.Tags.ORES_GEODE).add(WRBlocks.BLUE_GEODE_ORE.get(), WRBlocks.RED_GEODE_ORE.get(), WRBlocks.PURPLE_GEODE_ORE.get());
            tag(WRBlocks.Tags.ORES_PLATINUM).add(WRBlocks.PLATINUM_ORE.get());
            cloneToItem(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS).addTags(WRBlocks.Tags.STORAGE_BLOCKS_GEODE, WRBlocks.Tags.STORAGE_BLOCKS_PLATINUM);
            tag(WRBlocks.Tags.STORAGE_BLOCKS_GEODE).add(WRBlocks.BLUE_GEODE_BLOCK.get(), WRBlocks.RED_GEODE_BLOCK.get(), WRBlocks.PURPLE_GEODE_BLOCK.get());
            tag(WRBlocks.Tags.STORAGE_BLOCKS_PLATINUM).add(WRBlocks.PLATINUM_BLOCK.get());
            tag(BlockTags.DRAGON_IMMUNE).add(WRBlocks.PURPLE_GEODE_ORE.get());
            cloneToItem(BlockTags.LEAVES, ItemTags.LEAVES).add(WRBlocks.BLUE_OSERI_LEAVES.get(), WRBlocks.GOLD_OSERI_LEAVES.get(), WRBlocks.PINK_OSERI_LEAVES.get(), WRBlocks.PURPLE_OSERI_LEAVES.get());
            cloneToItem(BlockTags.SAPLINGS, ItemTags.SAPLINGS).add(WRBlocks.BLUE_OSERI_SAPLING.get(), WRBlocks.GOLD_OSERI_SAPLING.get(), WRBlocks.PINK_OSERI_SAPLING.get(), WRBlocks.PURPLE_OSERI_SAPLING.get());
            cloneToItem(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS).add(WRBlocks.CREVASSE_COTTON.get());
            tag(BlockTags.CLIMBABLE).add(WRBlocks.MOSS_VINE.get());
            tag(BlockTags.ENDERMAN_HOLDABLE).add(WRBlocks.FROSTED_GRASS.get());
            tag(BlockTags.BAMBOO_PLANTABLE_ON).add(WRBlocks.FROSTED_GRASS.get());
            tag(BlockTags.VALID_SPAWN).add(WRBlocks.FROSTED_GRASS.get());

            tagWoodGroup(WRBlocks.OSERI_WOOD, WRBlocks.Tags.OSERI_LOGS, true);
//            tagWoodGroup(WRBlocks.SAL_WOOD, WRBlocks.Tags.SAL_LOGS, true);
        }

        private void tagWoodGroup(WRBlocks.WoodGroup group, ITag.INamedTag<Block> logTag, boolean flammable)
        {
            tag(logTag).add(group.getLog(), group.getStrippedLog(), group.getWood(), group.getStrippedWood());

            cloneToItem(BlockTags.PLANKS, ItemTags.PLANKS).add(group.getPlanks());
            cloneToItem(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS).add(group.getButton());
            cloneToItem(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS).add(group.getDoor());
            cloneToItem(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS).add(group.getStairs());
            cloneToItem(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS).add(group.getSlab());
            cloneToItem(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES).add(group.getFence());
            cloneToItem(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES).add(group.getPressurePlate());
            cloneToItem(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS).add(group.getTrapDoor());
            tag(BlockTags.FENCE_GATES).add(group.getFenceGate());
            tag(BlockTags.STANDING_SIGNS).add(group.getSign());
            tag(BlockTags.WALL_SIGNS).add(group.getWallSign());

            if (flammable) tag(BlockTags.LOGS_THAT_BURN).addTag(logTag);
            else tag(BlockTags.NON_FLAMMABLE_WOOD).addTag(logTag).add(group.getPlanks(), group.getButton(), group.getDoor(), group.getStairs(), group.getSlab(), group.getFence(), group.getFenceGate(), group.getPressurePlate(), group.getTrapDoor(), group.getSign(), group.getWallSign());
        }

        /**
         * For use with vanilla tags for ease of generating
         */
        private Builder<Block> cloneToItem(ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
        {
            tags.put(blockTag, itemTag);
            return tag(blockTag);
        }
    }

    private static class EntityData extends EntityTypeTagsProvider
    {
        private EntityData(DataGenerator generatorIn, ExistingFileHelper helper)
        {
            super(generatorIn, Wyrmroost.MOD_ID, helper);
        }

        @Override
        protected void addTags()
        {
            tag(EntityTypeTags.ARROWS).add(WREntities.GEODE_TIPPED_ARROW.get());
        }
    }
}
