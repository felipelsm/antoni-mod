package com.felipelsm.antonimod.util;

import com.felipelsm.antonimod.AntoniMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags
{
    public static class Blocks
    {

        private static TagKey<Block> createTag(String name)
        {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(AntoniMod.MOD_ID, name));
        }
    }

    public static class Items
    {
        public static final TagKey<Item> TRANSFORMABLE_ANTONI_ITEM = createTag("transformable_antoni_items");

        private static TagKey<Item> createTag(String name)
        {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(AntoniMod.MOD_ID, name));
        }
    }
}
