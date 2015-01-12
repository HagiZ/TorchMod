package se.hagiz.minecraft.torchmod.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ModEventHandler {
	
	@SubscribeEvent
	public void onUsePick(PlayerInteractEvent event) {
		
		EntityPlayer player = event.entityPlayer;
		World world = event.world;
		
		int x = event.x;
		int y = event.y;
		int z = event.z;
		float playerX = (float)player.posX;
		float playerY = (float)player.posY;
		float playerZ = (float)player.posZ;
		
		int facing = event.face;
		
		ItemStack nearbyStack = null;
		ItemStack heldStack = null;
		Item heldItem = null;
				
		if (!world.isRemote && event.action == event.action.RIGHT_CLICK_BLOCK) {
			int activeSlot = player.inventory.currentItem;
	        int itemSlot = activeSlot == 0 ? 8 : activeSlot + 1;
	        	        
	        if (activeSlot < 8) {
	            nearbyStack = player.inventory.getStackInSlot(itemSlot);
	            heldStack = player.inventory.getStackInSlot(activeSlot);
	            
	            if (heldStack != null) {
	            	heldItem = heldStack.getItem();
	            }
	            		            
	            if (nearbyStack != null && heldItem != null && heldItem.isItemTool(heldStack)) {
	                Item item = nearbyStack.getItem();
	                if (item instanceof ItemPotion && ((ItemPotion) item).isSplash(nearbyStack.getItemDamage())) {
	                    nearbyStack = item.onItemRightClick(nearbyStack, world, player);
	                    if (nearbyStack.stackSize < 1)
	                    {
	                        nearbyStack = null;
	                        player.inventory.setInventorySlotContents(itemSlot, null);
	                    }
	                    
	                } else if (item != null && item == item.getItemFromBlock(Blocks.torch)) {	                	
		                	player.inventory.currentItem = itemSlot;
		                	item.onItemUse(nearbyStack, player, world, x, y, z, facing, playerX, playerY, playerZ);
		                	player.inventory.currentItem = activeSlot;
		                	player.swingItem();
		                	
		                	if (nearbyStack.stackSize < 1 ) {
		                		nearbyStack = null;
		                		player.inventory.setInventorySlotContents(itemSlot, null);
		                	}
	                	
	                	player.inventoryContainer.detectAndSendChanges();
	                	
	                }   
	            } 
	        }
		} 		
	}
}
