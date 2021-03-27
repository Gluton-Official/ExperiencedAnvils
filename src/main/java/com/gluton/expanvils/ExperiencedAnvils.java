package com.gluton.expanvils;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
}
