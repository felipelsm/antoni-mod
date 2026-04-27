package com.felipelsm.antonimod.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

public class AntoniHammerItem extends MiningToolItem
{
    private final int maxRange;
    private final int radius;

    public AntoniHammerItem(int maxRange, int radius, ToolMaterial material, Item.Settings settings) {
        super(material, BlockTags.PICKAXE_MINEABLE, settings);
        this.maxRange = maxRange;
        this.radius = radius;
    }

    public int getMaxRange() { return this.maxRange; }

    public int getRadius() { return this.radius; }

    /**
     * Obtain an NxN grid based on the block face the player is looking at.
     *
     * @param range - Mining range
     * @param radius - Dimension N of the grid
     * @param centralBlockPos - Position of the central block of the NxN grid
     * @param player
     * @return List of BlockPos of the NxN grid
     */
    public static List<BlockPos> getBlocksToMine(int range, int radius, BlockPos centralBlockPos, ServerPlayerEntity player)
    {
        List<BlockPos> posToMineList = new ArrayList<>();
        HitResult hit = player.raycast(range, 0, false);

        if (hit.getType() == HitResult.Type.BLOCK) {
            Direction side = ((BlockHitResult) hit).getSide();

            for (int i = -radius; i <= radius; i++) {
                for (int j = -radius; j <= radius; j++) {
                    // Logic to orient the 3x3 square based on the face the player is looking at
                    posToMineList.add(switch (side.getAxis()) {
                        case Y -> centralBlockPos.add(i, 0, j);
                        case X -> centralBlockPos.add(0, i, j);
                        case Z -> centralBlockPos.add(i, j, 0);
                    });
                }
            }
        }

        return posToMineList;
    }
}
