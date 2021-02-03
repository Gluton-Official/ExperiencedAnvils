package com.gluton.expanvils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.AnvilScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gluton.expanvils.client.screen.ExperiencedAnvilScreen;

/**
 * @author Gluton
 */
@Mod("expanvils")
public class ExperiencedAnvils {

    public static final Logger LOGGER = LogManager.getLogger();

    public ExperiencedAnvils() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SuppressWarnings("resource")
    @OnlyIn(Dist.CLIENT)
	@SubscribeEvent
    public void openAnvilGui(GuiOpenEvent event) {
    	if (event.getGui() instanceof AnvilScreen) {
    		AnvilScreen screen = (AnvilScreen) event.getGui();
    		event.setGui(new ExperiencedAnvilScreen(screen.getContainer(), Minecraft.getInstance().player.inventory, screen.getTitle()));
    	}
    }
}
