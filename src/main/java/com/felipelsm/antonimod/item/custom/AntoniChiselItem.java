package com.felipelsm.antonimod.item.custom;

import com.felipelsm.antonimod.block.ModBlocks;
import com.felipelsm.antonimod.block.custom.AntoniLampBlock;
import com.felipelsm.antonimod.component.ModDataComponentTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

/**
 * Custom Mainhand Item
 *
 * @Note: Affected blocks are transformed according to pre-build Map
 */
public class AntoniChiselItem extends Item
{
    public AntoniChiselItem(Item.Settings settings) { super(settings); }

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
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block clickedBlock = state.getBlock();

        boolean wasChiselUsed = false;

        // Handle Chisel Mapping
        if (CHISEL_MAP.containsKey(clickedBlock)) {
            // Affect target block only in server world
            if(!world.isClient()) {
                // Change chiseled block
                world.setBlockState(pos, CHISEL_MAP.get(clickedBlock).getDefaultState());

                applyChiselEffects(context, world, pos);
            }

            wasChiselUsed = true;
        }

        // Handle Lamps
        if (clickedBlock instanceof AntoniLampBlock) {
            if (!world.isClient()) {
                // Turn lamp ON/OFF
                //world.setBlockState(pos, clickedBlock.getDefaultState().with(ON, true));
                world.setBlockState(pos, state.cycle(AntoniLampBlock.ON));
                applyChiselEffects(context, world, pos);
            }

            wasChiselUsed = true;
        }


        // Cause Use Item Animation to play
        return wasChiselUsed ? ActionResult.SUCCESS : ActionResult.PASS;
    }

    private void applyChiselEffects(ItemUsageContext context, World world, BlockPos pos) {
        // Damage chisel
        context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

        // Play sound
        world.playSound(null, pos, SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS, 1f, 1f);

        // Set Data Component
        context.getStack().set(ModDataComponentTypes.COORDINATES, pos);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.antonimod.antoni_chisel.shift_down"));
        } else {
            tooltip.add(Text.translatable("tooltip.antonimod.shift_tooltip"));
        }

        if (stack.get(ModDataComponentTypes.COORDINATES) != null) {
            tooltip.add(Text.translatable("Last Block changed at " + stack.get(ModDataComponentTypes.COORDINATES)));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}
