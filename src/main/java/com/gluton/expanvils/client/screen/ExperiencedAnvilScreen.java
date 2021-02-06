package com.gluton.expanvils.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.AnvilScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.RepairContainer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author Gluton
 */
@OnlyIn(Dist.CLIENT)
public class ExperiencedAnvilScreen extends AnvilScreen {

	private static final ITextComponent TOO_EXPENSIVE = new TranslationTextComponent("container.repair.expensive");

	public ExperiencedAnvilScreen(RepairContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
		RenderSystem.disableBlend();
		this.font.func_243248_b(matrixStack, this.title, (float) this.titleX, (float) this.titleY, 4210752);
		this.font.func_243248_b(matrixStack, this.playerInventory.getDisplayName(), (float) this.playerInventoryTitleX, (float) this.playerInventoryTitleY, 4210752);
		int xpCost = this.container.getMaximumCost();
		if (xpCost > 0) {
			int textColor = 8453920;
			ITextComponent textComponent;
			// if the level limit has not been removed server side and the item is over 39, no itemstack will be in the ouput, so the item will be too expensive
			if (xpCost >= 40 && !this.minecraft.player.abilities.isCreativeMode && !this.container.getSlot(2).getHasStack() && this.container.getSlot(1).getHasStack()) {
				textComponent = TOO_EXPENSIVE;
				textColor = 16736352;
			} else if (!this.container.getSlot(2).getHasStack()) {
				textComponent = null;
			} else {
				textComponent = new TranslationTextComponent("container.repair.cost", xpCost);
				if (!this.container.getSlot(2).canTakeStack(this.playerInventory.player)) {
					textColor = 16736352;
				}
			}

			if (textComponent != null) {
				int xPosition = this.xSize - 8 - this.font.getStringPropertyWidth(textComponent) - 2;
				fill(matrixStack, xPosition - 2, 67, this.xSize - 8, 79, 1325400064);
				this.font.func_243246_a(matrixStack, textComponent, (float) xPosition, 69.0F, textColor);
			}
		}
	}
}
