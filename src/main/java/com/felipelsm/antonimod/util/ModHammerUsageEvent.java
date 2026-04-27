package com.felipelsm.antonimod.util;

import com.felipelsm.antonimod.item.custom.AntoniHammerItem;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class ModHammerUsageEvent implements PlayerBlockBreakEvents.Before
{
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity)
    {
        ItemStack mainHandItem = player.getMainHandStack();

        if (mainHandItem.getItem() instanceof AntoniHammerItem hammer && player instanceof ServerPlayerEntity serverPlayer) {
            AntoniHammerItem hammerItem = (AntoniHammerItem) mainHandItem.getItem();

            if (HARVESTED_BLOCKS.contains(pos)) { return true; }

            for (BlockPos blockPos : AntoniHammerItem.getBlocksToMine(hammerItem.getMaxRange(), hammerItem.getRadius(), pos, serverPlayer)) {
                if (pos == blockPos || !hammer.isCorrectForDrops(mainHandItem, world.getBlockState(blockPos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(blockPos);
                serverPlayer.interactionManager.tryBreakBlock(blockPos);
                HARVESTED_BLOCKS.remove(blockPos);
            }
        }

        return true;
    }
}
