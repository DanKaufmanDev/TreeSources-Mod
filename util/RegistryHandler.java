package com.bax.treesources.util;

import com.bax.treesources.IHasModel;
import com.bax.treesources.init.BlockInit;
import com.bax.treesources.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler 
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
	event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
	
	}

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) 
	{
	for(Item item : ItemInit.ITEMS)
	{
		if(item instanceof IHasModel)
		{
			((IHasModel)item).registerModels();
		}
	}

	for(Block block : BlockInit.BLOCKS)
	{
		if(block instanceof IHasModel)
		{
			((IHasModel)block).registerModels();
		}

	}
	
}

}
