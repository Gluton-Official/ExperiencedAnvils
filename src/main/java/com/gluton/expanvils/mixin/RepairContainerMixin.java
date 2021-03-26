package com.gluton.expanvils.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.inventory.container.RepairContainer;
import net.minecraft.util.IntReferenceHolder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.loading.FMLEnvironment;

/**
 * @author Gluton
 */
@Mixin(RepairContainer.class)
public abstract class RepairContainerMixin {
	
	/**
	 * Wrapper method for {@link Minecraft#isSingleplayer()} so a RuntimeException
	 * won't be thrown when referencing a client side class server side
	 * 
	 * @return the value of {@link Minecraft#isSingleplayer()}
	 */
	@OnlyIn(Dist.CLIENT)
	private static boolean isSingleplayer() {
		return net.minecraft.client.Minecraft.getInstance().isSingleplayer();
	}
	
	/**
	 * Prevents xp cost clamping by redirecting ocurrances of {@code this.maximumCost.get()} in
	 * {@link RepairContainer#updateRepairOutput()} to return 1 so checks for above 39 is always false,
	 * but operation details can still be calculated in {@link AnvilScreen#drawGuiContainerForegroundLayer()}
	 * 
	 * @param irh holds the anvil operation cost
	 * @return 1 if the mod is running on the server or in singleplayer, normal cost otherwise
	 */
	@Redirect(method = "updateRepairOutput()V", at = @At(value = "INVOKE",
			target = "Lnet/minecraft/util/IntReferenceHolder;get()I"))
	private int preventClamp(IntReferenceHolder irh) {
		if (FMLEnvironment.dist == Dist.CLIENT) {
			return isSingleplayer() ? 1 : irh.get();
		} else {
			return 1;
		}
	}
}
