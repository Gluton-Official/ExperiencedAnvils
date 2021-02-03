package com.gluton.expanvils.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.AnvilScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.RepairContainer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

/**
 * @author Gluton
 */
public class ExperiencedAnvilScreen extends AnvilScreen {

	public ExperiencedAnvilScreen(RepairContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
		RenderSystem.disableBlend();
		this.font.func_243248_b(matrixStack, this.title, (float)this.titleX, (float)this.titleY, 4210752);
		this.font.func_243248_b(matrixStack, this.playerInventory.getDisplayName(), (float)this.playerInventoryTitleX, (float)this.playerInventoryTitleY, 4210752);
	    int i = this.container.getMaximumCost();
	    if (i > 0) {
	    	int j = 8453920;
	        ITextComponent itextcomponent;
	        if (!this.container.getSlot(2).getHasStack()) {
	        	itextcomponent = null;
	        } else {
	        	itextcomponent = new TranslationTextComponent("container.repair.cost", i);
	        	if (!this.container.getSlot(2).canTakeStack(this.playerInventory.player)) {
	        		j = 16736352;
	        	}
	        }

	        if (itextcomponent != null) {
	        	int k = this.xSize - 8 - this.font.getStringPropertyWidth(itextcomponent) - 2;
	        	fill(matrixStack, k - 2, 67, this.xSize - 8, 79, 1325400064);
	        	this.font.func_243246_a(matrixStack, itextcomponent, (float)k, 69.0F, j);
	        }
	    }
	}
}
