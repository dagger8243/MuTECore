package com.blueweabo.mutecore.api.logic;

import javax.annotation.ParametersAreNonnullByDefault;

import com.blueweabo.mutecore.api.logic.interfaces.PowerLogic;
import com.cleanroommc.modularui.api.IItemStackLong;
import com.cleanroommc.modularui.utils.item.ItemStackLongDelegate;

import net.minecraft.item.ItemStack;

@ParametersAreNonnullByDefault
public class ItemPowerLogic implements PowerLogic {

    private ItemInventoryLogic container;
    private ItemStack itemToBurn;
    private long startingBurnTime;
    private long currentBurnTime;

    @Override
    public boolean consume(long tick) {
        if (currentBurnTime > 0) {
            currentBurnTime--;
            return true;
        }
        IItemStackLong toExtract = new ItemStackLongDelegate(itemToBurn);
        IItemStackLong extractedTry = container.extract(toExtract, 1, false);
        if (extractedTry == null || extractedTry.getStackSize() < 1) {
            return false;
        }
        container.extract(toExtract, 1, true);
        currentBurnTime = startingBurnTime;
        return true;
    }

    @Override
    public boolean generate(long tick) {
        return true;
    }
}