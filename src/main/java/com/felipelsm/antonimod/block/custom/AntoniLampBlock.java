package com.felipelsm.antonimod.block.custom;

import com.felipelsm.antonimod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;

import java.util.List;

public class AntoniLampBlock extends Block
{
    public AntoniLampBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(ON, false));
    }

    public static final BooleanProperty ON = BooleanProperty.of("on");

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(ON);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type)
    {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.antonimod.antoni_lamp.shift_down"));
        } else {
            tooltip.add(Text.translatable("tooltip.antonimod.shift_tooltip"));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}
