package com.gluton.expanvils;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gluton.expanvils.client.screen.ExperiencedAnvilScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.AnvilScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLNetworkConstants;

/**
 * @author Gluton
 */
@Mod("expanvils")
public class ExperiencedAnvils {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	public ExperiencedAnvils() {
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST,
				() -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (net, save) -> true));
	}
	
	@SuppressWarnings("resource")
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void openAnvilGui(GuiOpenEvent event) {
		if (event.getGui() instanceof AnvilScreen) {
			AnvilScreen screen = (AnvilScreen) event.getGui();
			event.setGui(new ExperiencedAnvilScreen(screen.getContainer(),
					Minecraft.getInstance().player.inventory, screen.getTitle()));
		}
	}
}
