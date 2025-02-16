package com.github.wolfshotz.wyrmroost.registry;

import com.github.wolfshotz.wyrmroost.Wyrmroost;
import com.github.wolfshotz.wyrmroost.blocks.*;
import com.github.wolfshotz.wyrmroost.world.features.OseriTreeFeature;
import com.github.wolfshotz.wyrmroost.world.features.TreeGen;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class WRBlocks
{
    static final ItemGroup BLOCKS_ITEM_GROUP = new ItemGroup("wyrmroost_dimension")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(PURPLE_GEODE_BLOCK.get());
        }
    };

    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, Wyrmroost.MOD_ID);
    public static final List<BlockExtension> EXTENSIONS = new ArrayList<>();

    public static final RegistryObject<Block> PLATINUM_ORE = register("platinum_ore", () -> new Block(mineable(Material.STONE, ToolType.PICKAXE, 1, 3f, SoundType.STONE)));
    public static final RegistryObject<Block> PLATINUM_BLOCK = register("platinum_block", () -> new Block(mineable(Material.METAL, ToolType.PICKAXE, 1, 5f, SoundType.METAL)));

    public static final RegistryObject<Block> BLUE_GEODE_ORE = register("blue_geode_ore", () -> new EXPBlock(3, 7, mineable(Material.STONE, ToolType.PICKAXE, 2, 3f, SoundType.STONE)));
    public static final RegistryObject<Block> BLUE_GEODE_BLOCK = register("blue_geode_block", () -> new Block(mineable(Material.METAL, ToolType.PICKAXE, 2, 5f, SoundType.METAL)));
    public static final RegistryObject<Block> RED_GEODE_ORE = register("red_geode_ore", () -> new EXPBlock(4, 8, mineable(Material.STONE, ToolType.PICKAXE, 3, 3f, SoundType.NETHER_ORE)));
    public static final RegistryObject<Block> RED_GEODE_BLOCK = register("red_geode_block", () -> new Block(mineable(Material.METAL, ToolType.PICKAXE, 3, 5f, SoundType.METAL)));
    public static final RegistryObject<Block> PURPLE_GEODE_ORE = register("purple_geode_ore", () -> new EXPBlock(8, 11, mineable(Material.METAL, ToolType.PICKAXE, 4, 5f, SoundType.GILDED_BLACKSTONE)));
    public static final RegistryObject<Block> PURPLE_GEODE_BLOCK = register("purple_geode_block", () -> new Block(mineable(Material.METAL, ToolType.PICKAXE, 4, 7f, SoundType.METAL)));

    // tincture weald
    public static final RegistryObject<Block> MULCH = register("mulch", MulchBlock::new);
    public static final RegistryObject<Block> SILVER_MOSS = register("silver_moss", () -> new GrowingPlantBlock(plant().randomTicks(), Direction.DOWN, 2, 0.0875, WRBlocks.SILVER_MOSS_BODY), extend().render(() -> RenderType::cutout).flammability(30, 80));
    public static final RegistryObject<Block> SILVER_MOSS_BODY = register("silver_moss_body", () -> new GrowingPlantBodyBlock(plant(), WRBlocks.SILVER_MOSS), extend().render(() -> RenderType::cutout).noItem().flammability(30, 60));
    public static final RegistryObject<Block> GILLA = register("gilla", GillaBushBlock::new, extend().render(() -> RenderType::cutout).flammability(60, 100));
    public static final RegistryObject<Block> MOSS_VINE = register("moss_vine", () -> new VineBlock(properties(Material.REPLACEABLE_PLANT, SoundType.VINE).randomTicks().noCollission().strength(0.2f)), extend().render(() -> RenderType::cutout).flammability(15, 100));
    public static final RegistryObject<Block> BLUE_OSERI_SAPLING = register("blue_oseri_sapling", () -> new SaplingBlock(new TreeGen(WRWorld.Features.CONFIGURED_BLUE_OSERI), plant().randomTicks()), extend().render(() -> RenderType::cutout));
    public static final RegistryObject<Block> BLUE_OSERI_LEAVES = register("blue_oseri_leaves", () -> new OseriLeaves(OseriTreeFeature.Type.BLUE.color, leaves()), extend().render(() -> RenderType::cutout).flammability(30, 60));
    public static final RegistryObject<Block> BLUE_OSERI_VINES = register("blue_oseri_vines", () -> new OseriVinesBlock(WRBlocks.BLUE_OSERI_VINES_BODY, OseriTreeFeature.Type.BLUE.color), extend().render(() -> RenderType::cutout).flammability(30, 80));
    public static final RegistryObject<Block> BLUE_OSERI_VINES_BODY = register("blue_oseri_vines_body", () -> new OseriVinesBodyBlock(WRBlocks.BLUE_OSERI_VINES, OseriTreeFeature.Type.BLUE.color), extend().render(() -> RenderType::cutout).noItem().flammability(30, 80));
    public static final RegistryObject<Block> BLUE_OSERI_PETALS = register("blue_oseri_petals", () -> new PetalsBlock(plant()), extend().render(() -> RenderType::cutout).flammability(60, 30));
    public static final RegistryObject<Block> GOLD_OSERI_SAPLING = register("gold_oseri_sapling", () -> new SaplingBlock(new TreeGen(WRWorld.Features.CONFIGURED_GOLD_OSERI), plant().randomTicks()), extend().render(() -> RenderType::cutout));
    public static final RegistryObject<Block> GOLD_OSERI_LEAVES = register("gold_oseri_leaves", () -> new OseriLeaves(OseriTreeFeature.Type.GOLD.color, leaves()), extend().render(() -> RenderType::cutout).flammability(30, 60));
    public static final RegistryObject<Block> GOLD_OSERI_VINES = register("gold_oseri_vines", () -> new OseriVinesBlock(WRBlocks.GOLD_OSERI_VINES_BODY, OseriTreeFeature.Type.GOLD.color), extend().render(() -> RenderType::cutout).flammability(30, 80));
    public static final RegistryObject<Block> GOLD_OSERI_VINES_BODY = register("gold_oseri_vines_body", () -> new OseriVinesBodyBlock(WRBlocks.GOLD_OSERI_VINES, OseriTreeFeature.Type.GOLD.color), extend().render(() -> RenderType::cutout).noItem().flammability(30, 80));
    public static final RegistryObject<Block> GOLD_OSERI_PETALS = register("gold_oseri_petals", () -> new PetalsBlock(plant()), extend().render(() -> RenderType::cutout).flammability(60, 30));
    public static final RegistryObject<Block> PINK_OSERI_SAPLING = register("pink_oseri_sapling", () -> new SaplingBlock(new TreeGen(WRWorld.Features.CONFIGURED_PINK_OSERI), plant().randomTicks()), extend().render(() -> RenderType::cutout));
    public static final RegistryObject<Block> PINK_OSERI_LEAVES = register("pink_oseri_leaves", () -> new OseriLeaves(OseriTreeFeature.Type.PINK.color, leaves()), extend().render(() -> RenderType::cutout).flammability(30, 60));
    public static final RegistryObject<Block> PINK_OSERI_VINES = register("pink_oseri_vines", () -> new OseriVinesBlock(WRBlocks.PINK_OSERI_VINES_BODY, OseriTreeFeature.Type.PINK.color), extend().render(() -> RenderType::cutout).flammability(30, 80));
    public static final RegistryObject<Block> PINK_OSERI_VINES_BODY = register("pink_oseri_vines_body", () -> new OseriVinesBodyBlock(WRBlocks.PINK_OSERI_VINES, OseriTreeFeature.Type.PINK.color), extend().render(() -> RenderType::cutout).noItem().flammability(30, 80));
    public static final RegistryObject<Block> PINK_OSERI_PETALS = register("pink_oseri_petals", () -> new PetalsBlock(plant()), extend().render(() -> RenderType::cutout).flammability(60, 30));
    public static final RegistryObject<Block> PURPLE_OSERI_SAPLING = register("purple_oseri_sapling", () -> new SaplingBlock(new TreeGen(WRWorld.Features.CONFIGURED_PURPLE_OSERI), plant().randomTicks()), extend().render(() -> RenderType::cutout));
    public static final RegistryObject<Block> PURPLE_OSERI_LEAVES = register("purple_oseri_leaves", () -> new OseriLeaves(OseriTreeFeature.Type.PURPLE.color, leaves()), extend().render(() -> RenderType::cutout).flammability(30, 60));
    public static final RegistryObject<Block> PURPLE_OSERI_VINES = register("purple_oseri_vines", () -> new OseriVinesBlock(WRBlocks.PURPLE_OSERI_VINES_BODY, OseriTreeFeature.Type.PURPLE.color), extend().render(() -> RenderType::cutout).flammability(30, 80));
    public static final RegistryObject<Block> PURPLE_OSERI_VINES_BODY = register("purple_oseri_vines_body", () -> new OseriVinesBodyBlock(WRBlocks.PURPLE_OSERI_VINES, OseriTreeFeature.Type.PURPLE.color), extend().render(() -> RenderType::cutout).noItem().flammability(30, 80));
    public static final RegistryObject<Block> PURPLE_OSERI_PETALS = register("purple_oseri_petals", () -> new PetalsBlock(plant()), extend().render(() -> RenderType::cutout).flammability(60, 30));
    public static final RegistryObject<Block> WHITE_OSERI_SAPLING = register("white_oseri_sapling", () -> new SaplingBlock(new TreeGen(WRWorld.Features.CONFIGURED_WHITE_OSERI), plant().randomTicks()), extend().render(() -> RenderType::cutout));
    public static final RegistryObject<Block> WHITE_OSERI_LEAVES = register("white_oseri_leaves", () -> new OseriLeaves(OseriTreeFeature.Type.WHITE.color, leaves()), extend().render(() -> RenderType::cutout).flammability(30, 60));
    public static final RegistryObject<Block> WHITE_OSERI_VINES = register("white_oseri_vines", () -> new OseriVinesBlock(WRBlocks.WHITE_OSERI_VINES_BODY, OseriTreeFeature.Type.WHITE.color), extend().render(() -> RenderType::cutout).flammability(30, 80));
    public static final RegistryObject<Block> WHITE_OSERI_VINES_BODY = register("white_oseri_vines_body", () -> new OseriVinesBodyBlock(WRBlocks.WHITE_OSERI_VINES, OseriTreeFeature.Type.WHITE.color), extend().render(() -> RenderType::cutout).noItem().flammability(30, 80));
    public static final RegistryObject<Block> WHITE_OSERI_PETALS = register("white_oseri_petals", () -> new PetalsBlock(plant()), extend().render(() -> RenderType::cutout).flammability(60, 30));
    public static final WoodGroup OSERI_WOOD = new WoodGroup("oseri", MaterialColor.SAND, MaterialColor.STONE);

    // frost crevasse
    public static final RegistryObject<Block> FROSTED_GRASS = register("frosted_grass", () -> new GrassBlock(properties(Material.DIRT, WRSounds.Types.FROSTED_GRASS).strength(0.55f)));
    public static final RegistryObject<Block> CREVASSE_COTTON = register("crevasse_cotton", CrevasseCottonBlock::new, extend().render(() -> RenderType::cutout).flammability(30, 80));
    public static final RegistryObject<Block> FROST_GOWN = register("frost_gown", () -> new TallFlowerBlock(properties(Material.REPLACEABLE_PLANT, SoundType.GRASS).noCollission()), extend().render(() -> RenderType::cutout).flammability(30, 80));
//    public static final WoodGroup SAL_WOOD = new WoodGroup("sal", MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_GRAY);

    static RegistryObject<Block> register(String name, Supplier<Block> block)
    {
        return register(name, block, extend());
    }

    static RegistryObject<Block> register(String name, Supplier<Block> block, BlockExtension extension)
    {
        RegistryObject<Block> delegate = REGISTRY.register(name, block);
        if (extension.itemFactory != null) WRItems.register(name, () -> extension.itemFactory.apply(delegate.get()));
        if (extension.requiresSetup())
        {
            extension.block = delegate;
            EXTENSIONS.add(extension);
        }
        return delegate;
    }

    public static AbstractBlock.Properties properties(Material material, SoundType sound)
    {
        return AbstractBlock.Properties
                .of(material)
                .sound(sound);
    }

    public static AbstractBlock.Properties plant()
    {
        return properties(Material.LEAVES, SoundType.GRASS).noCollission();
    }

    public static AbstractBlock.Properties leaves()
    {
        return properties(Material.LEAVES, SoundType.GRASS)
                .strength(0.2f)
                .randomTicks()
                .noOcclusion()
                .isValidSpawn((s, r, p, e) -> false)
                .isSuffocating((s, r, p) -> false)
                .isViewBlocking((s, r, p) -> false);
    }

    public static AbstractBlock.Properties mineable(Material material, ToolType harvestTool, int harvestLevel, float hardnessResistance, SoundType sound)
    {
        return AbstractBlock.Properties
                .of(material)
                .harvestTool(harvestTool)
                .harvestLevel(harvestLevel)
                .requiresCorrectToolForDrops()
                .strength(hardnessResistance)
                .sound(sound);
    }

    public static AbstractBlock.Properties mineable(Material material, ToolType harvestTool, int harvestLevel, float hardness, float resistance, SoundType sound)
    {
        return AbstractBlock.Properties
                .of(material)
                .harvestTool(harvestTool)
                .harvestLevel(harvestLevel)
                .requiresCorrectToolForDrops()
                .strength(hardness, resistance)
                .sound(sound);
    }

    public static class Tags
    {
        public static final Map<INamedTag<Block>, INamedTag<Item>> ITEM_BLOCK_TAGS = new HashMap<>();

        public static final INamedTag<Block> ORES_GEODE = forge("ores/geode");
        public static final INamedTag<Block> ORES_PLATINUM = forge("ores/platinum");

        public static final INamedTag<Block> STORAGE_BLOCKS_GEODE = forge("storage_blocks/geode");
        public static final INamedTag<Block> STORAGE_BLOCKS_PLATINUM = forge("storage_blocks/platinum");
        public static final INamedTag<Block> OSERI_LOGS = tag("oseri_logs");
        public static final INamedTag<Block> SAL_LOGS = tag("sal_logs");

        static INamedTag<Block> forge(String path)
        {
            return getFor("forge:" + path);
        }

        public static INamedTag<Block> tag(String path)
        {
            return getFor(Wyrmroost.MOD_ID + ":" + path);
        }

        public static INamedTag<Block> getFor(String path)
        {
            INamedTag<Block> tag = BlockTags.bind(path);
            ITEM_BLOCK_TAGS.put(tag, ItemTags.bind(path));
            return tag;
        }

        public static INamedTag<Item> getItemTagFor(INamedTag<Block> blockTag)
        {
            return ITEM_BLOCK_TAGS.get(blockTag);
        }
    }

    public static class WoodGroup extends WoodType
    {
        final RegistryObject<Block> planks;
        final RegistryObject<Block> log;
        final RegistryObject<Block> strippedLog;
        final RegistryObject<Block> wood;
        final RegistryObject<Block> strippedWood;
        final RegistryObject<Block> slab;
        final RegistryObject<Block> pressurePlate;
        final RegistryObject<Block> fence;
        final RegistryObject<Block> fenceGate;
        final RegistryObject<Block> trapDoor;
        final RegistryObject<Block> stairs;
        final RegistryObject<Block> button;
        final RegistryObject<Block> door;
        final RegistryObject<Block> sign;
        final RegistryObject<Block> wallSign;

        public WoodGroup(String name, MaterialColor color, MaterialColor logColor)
        {
            super(name);

            this.planks = WRBlocks.register(name + "_planks", () -> new Block(props(color)), extend().flammability(5, 20));
            this.log = WRBlocks.register(name + "_log", () -> new LogBlock(color, logColor, self().strippedLog), extend().flammability(5, 5));
            this.strippedLog = WRBlocks.register("stripped_" + name + "_log", () -> new RotatedPillarBlock(LogBlock.properties(color, logColor)), extend().flammability(5, 5));
            this.wood = WRBlocks.register(name + "_wood", () -> new LogBlock(logColor, logColor, self().strippedWood), extend().flammability(5, 5));
            this.strippedWood = WRBlocks.register("stripped_" + name + "_wood", () -> new RotatedPillarBlock(LogBlock.properties(color, color)), extend().flammability(5, 5));
            this.slab = WRBlocks.register(name + "_slab", () -> new SlabBlock(props(color)), extend().flammability(5, 20));
            this.pressurePlate = WRBlocks.register(name + "_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, props(color).noCollission().strength(0.5f)));
            this.fence = WRBlocks.register(name + "_fence", () -> new FenceBlock(props(color)), extend().flammability(5, 20));
            this.fenceGate = WRBlocks.register(name + "_fence_gate", () -> new FenceGateBlock(props(color)), extend().flammability(5, 20));
            this.trapDoor = WRBlocks.register(name + "_trapdoor", () -> new TrapDoorBlock(props(color).strength(3f).noOcclusion().isValidSpawn((s, r, p, e) -> false)));
            this.stairs = WRBlocks.register(name + "_stairs", () -> new StairsBlock(() -> getPlanks().defaultBlockState(), props(color)), extend().flammability(5, 20));
            this.button = WRBlocks.register(name + "_button", () -> new WoodButtonBlock(AbstractBlock.Properties.of(Material.DECORATION).noCollission().harvestTool(ToolType.AXE).strength(0.5f).sound(SoundType.WOOD)));
            this.door = WRBlocks.register(name + "_door", () -> new DoorBlock(props(color).strength(3f).noOcclusion()));
            this.wallSign = WRBlocks.register(name + "_wall_sign", () -> new WRSignBlock.Wall(props(color).noCollission().strength(1f).lootFrom(self().sign), this), extend().noItem());
            this.sign = WRBlocks.register(name + "_sign", () -> new WRSignBlock(props(color).noCollission().strength(1f), this), extend().item(b -> new SignItem(new Item.Properties().stacksTo(16).tab(BLOCKS_ITEM_GROUP), b, getWallSign())));

            WoodType.register(this);
        }

        public Block getPlanks()
        {
            return planks.get();
        }

        public Block getLog()
        {
            return log.get();
        }

        public Block getStrippedLog()
        {
            return strippedLog.get();
        }

        public Block getWood()
        {
            return wood.get();
        }

        public Block getStrippedWood()
        {
            return strippedWood.get();
        }

        public Block getSlab()
        {
            return slab.get();
        }

        public Block getPressurePlate()
        {
            return pressurePlate.get();
        }

        public Block getFence()
        {
            return fence.get();
        }

        public Block getFenceGate()
        {
            return fenceGate.get();
        }

        public Block getTrapDoor()
        {
            return trapDoor.get();
        }

        public Block getStairs()
        {
            return stairs.get();
        }

        public Block getButton()
        {
            return button.get();
        }

        public Block getDoor()
        {
            return door.get();
        }

        public Block getSign()
        {
            return sign.get();
        }

        public Block getWallSign()
        {
            return wallSign.get();
        }

        private WoodGroup self()
        {
            return this;
        }

        @Override
        public String name()
        {
            return Wyrmroost.MOD_ID + ":" + super.name();
        }

        private static AbstractBlock.Properties props(MaterialColor color)
        {
            return AbstractBlock.Properties
                    .of(Material.WOOD, color)
                    .strength(2f, 3f)
                    .harvestTool(ToolType.AXE)
                    .sound(SoundType.WOOD);
        }
    }

    public static BlockExtension extend()
    {
        return new BlockExtension();
    }

    public static class BlockExtension
    {
        RegistryObject<Block> block;
        Function<Block, BlockItem> itemFactory = b -> new BlockItem(b, new Item.Properties().tab(BLOCKS_ITEM_GROUP));
        Supplier<Supplier<RenderType>> renderType;
        int[] flammability;

        public BlockExtension item(Function<Block, BlockItem> factory)
        {
            this.itemFactory = factory;
            return this;
        }

        public BlockExtension noItem()
        {
            return item(null);
        }

        public BlockExtension render(Supplier<Supplier<RenderType>> renderSupplier)
        {
            this.renderType = renderSupplier;
            return this;
        }

        public BlockExtension flammability(int spread, int destroyRate)
        {
            this.flammability = new int[]{spread, destroyRate};
            return this;
        }

        private boolean requiresSetup()
        {
            return renderType != null || flammability != null;
        }

        public void callBack()
        {
            if (renderType != null)
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> RenderTypeLookup.setRenderLayer(block.get(), renderType.get().get()));
            if (flammability != null)
                ((FireBlock) Blocks.FIRE).setFlammable(block.get(), flammability[0], flammability[1]);
        }
    }
}