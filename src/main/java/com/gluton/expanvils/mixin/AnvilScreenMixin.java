package com.gluton.expanvils.mixin;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.gui.screen.inventory.AbstractRepairScreen;
import net.minecraft.client.gui.screen.inventory.AnvilScreen;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.RepairContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/**
 * @author Gluton
 */
@Mixin(AnvilScreen.class)
public abstract class AnvilScreenMixin extends AbstractRepairScreen<RepairContainer> {
	
	private AnvilScreenMixin(RepairContainer container, PlayerInventory playerInv, ITextComponent title,
			ResourceLocation texture) {
		super(container, playerInv, title, texture);
	}
	
	/**
	 * Combines two more conditionals into the value returned by {@code abilities.isCreativeMode}.
	 * <ul>
	 *   <li>If an item is in the output slot, the operation was successful serverside, and the xp cost
	 *   should be shown for the client.
	 *   <li>If there is no item in the output slot and there is an
	 *   item also in the second input slot, then the server isn't removing the lvl cap, so the
	 *   client should not see a successful operation available.
	 *   <li>If the second item is not enchanted
	 *   and the first item is undamaged, a level cost will still be calculated, which can cause a
	 *   false positive for a too expensive message, since the two items are unable to be combined.
	 * </ul>
	 * 
	 * @param abilities the abilities of the player who opened the gui
	 * @return false if the operation was interpreted as too expensive
	 */
	@Redirect(method = "drawGuiContainerForegroundLayer(Lcom/mojang/blaze3d/matrix/MatrixStack;II)V",
			at = @At(target = "Lnet/minecraft/entity/player/PlayerAbilities;isCreativeMode:Z",
			value = "FIELD", opcode = Opcodes.GETFIELD))
	private boolean textRenderFix(PlayerAbilities abilities) {
		return abilities.isCreativeMode
				|| this.container.getSlot(2).getHasStack()
				|| !this.container.getSlot(1).getHasStack()
				// checks undamaged + unenchanted repairable item combo
				|| (!this.container.getSlot(2).getHasStack()
						&& this.container.getSlot(0).getHasStack()
						&& !this.container.getSlot(0).getStack().isDamaged()
						&& this.container.getSlot(1).getHasStack()
						&& !this.container.getSlot(1).getStack().isEnchanted());
	}
}
