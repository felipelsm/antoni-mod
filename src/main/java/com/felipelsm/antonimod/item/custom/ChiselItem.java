package com.felipelsm.antonimod.item.custom;

import com.felipelsm.antonimod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * Custom Mainhand Item
 *
 * @Note: Affected blocks are transformed according to pre-build Map
 */
public class ChiselItem extends Item
{
    public ChiselItem(Item.Settings settings) { super(settings); }

    /**
     * Maps Chiseled blocks to their target transformations
     */
    public static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    ModBlocks.ANTONI_BLOCK, ModBlocks.CHISELED_ANTONI_BLOCK,
                    ModBlocks.CHISELED_ANTONI_BLOCK, Blocks.IRON_BLOCK,
                    Blocks.IRON_BLOCK, Blocks.GOLD_BLOCK,
                    Blocks.GOLD_BLOCK, Blocks.DIAMOND_BLOCK,
                    Blocks.DIAMOND_BLOCK, Blocks.GRAVEL
            );

    @Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if (CHISEL_MAP.containsKey(clickedBlock)) {
            // Affect target block only in server world
            if(!world.isClient()) {
                // Change chiseled block
                world.setBlockState(context.getBlockPos(), CHISEL_MAP.get(clickedBlock).getDefaultState());

                // Damage chisel
                context.getStack().damage(
                        1,
                        ((ServerWorld) world),
                        ((ServerPlayerEntity) context.getPlayer()),
                        item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                // Play chisel sound
                world.playSound(
                        null,
                        context.getBlockPos(),
                        SoundEvents.BLOCK_GRINDSTONE_USE,
                        SoundCategory.BLOCKS
                );
            }
        }

        // Cause Use Item Animation to play
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.antonimod.antoni_chisel.shift_down"));
        } else {
            tooltip.add(Text.translatable("tooltip.antonimod.antoni_chisel"));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}
