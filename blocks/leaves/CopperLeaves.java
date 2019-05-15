package com.bax.treesources.blocks.leaves;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import com.bax.treesources.IHasModel;
import com.bax.treesources.Main;
import com.bax.treesources.init.BlockInit;
import com.bax.treesources.init.ItemInit;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CopperLeaves extends BlockLeaves implements IHasModel
{
	public static String type;
	
	public CopperLeaves(String name) 
	{
		
		type = name.replaceAll("_leaves", "").trim();
		System.out.println(type);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.PLANT);
		setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
		setCreativeTab(Main.treesources);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	@Override 
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune){
			ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		
			drops.add(new ItemStack(ItemInit.COPPER_PINECONE, RANDOM.nextInt(6)+-4));
			if(RANDOM.nextFloat()>0.7f) {
				drops.add(new ItemStack(BlockInit.COPPER_SAPLING, RANDOM.nextInt(6)+-4));
				if(RANDOM.nextFloat()>0.5) {
				}
			}
			return drops;
	
	}		
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		int i = 0;
		if(!((Boolean)state.getValue(DECAYABLE)).booleanValue()) i |= 2;
		if(!((Boolean)state.getValue(CHECK_DECAY)).booleanValue()) i|= 4;
		return i;
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) 
	{
		return new ItemStack(this);
	}
	
	@Override
	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {return;}
	
	@Override
	protected int getSaplingDropChance(IBlockState state) {return 5;}
	
	@Override
	public EnumType getWoodType(int meta) {return null;}
	
	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) 
	{
		return NonNullList.withSize(1, new ItemStack(this));
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) 
	{
		return false;
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
	}
	
	@Override
	public boolean canRenderInLayer(IBlockState state, @Nonnull BlockRenderLayer layer) {
		return layer == BlockRenderLayer.TRANSLUCENT;

	}	
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
