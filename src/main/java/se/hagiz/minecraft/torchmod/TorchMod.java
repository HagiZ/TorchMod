package se.hagiz.minecraft.torchmod;

import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import se.hagiz.minecraft.torchmod.event.ModEventHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TorchMod.MODID, version = TorchMod.VERSION, name="TorchMod")
public class TorchMod
{
    public static final String MODID = "TorchMod";
    public static final String VERSION = "${version}";
    
    public static final Logger logger = LogManager.getLogger(MODID);
    
    ModEventHandler handler = new ModEventHandler();
    
    @Instance(MODID)
    public static TorchMod instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    	if (!Loader.isModLoaded("TConstruct")) {
    		
        	FMLCommonHandler.instance().bus().register(handler);
        	MinecraftForge.EVENT_BUS.register(handler);
        	
        	logger.info("Activating TorchMod!");
    		
    	} else {
    		
    		logger.info("Tinker's Construct is loaded... I'm not needed here! Abort!!");    
    		
    	}

    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	logger.info("TorchMod..?");
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	logger.info("TorchMod.. Yep!");
    }
}
