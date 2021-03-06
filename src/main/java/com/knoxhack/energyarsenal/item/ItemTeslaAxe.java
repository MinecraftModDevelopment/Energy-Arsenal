package com.knoxhack.energyarsenal.item;
import com.google.common.collect.Sets;
import com.knoxhack.energyarsenal.ModInfo;
import com.knoxhack.energyarsenal.Main;
import com.knoxhack.energyarsenal.TeslaArsenalTeslaUtilities;

import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.api.implementation.BaseTeslaContainerProvider;
import net.darkhax.tesla.lib.TeslaUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import java.util.List;
import java.util.Set;
public class ItemTeslaAxe extends ItemTesla
{
    public long cost = 200;
    public static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] {Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE});

    public ItemTeslaAxe()
    {
        super();
        setUnlocalizedName("energyarsenal.itemteslaaxe");
        setRegistryName("itemteslaaxe");
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
    	TeslaUtils.takePower(stack, null, cost, false);
        return true;
    }
	@Override public boolean isRepairable()
	{
		return false;
	}

    @Override
    public boolean canHarvestBlock(IBlockState state)
    {
        return Items.DIAMOND_AXE.canHarvestBlock(state);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state)
    {
        if (this.getStoredPower(stack, null) < cost)
        {
            return 0.5F;
        }
        if (Items.WOODEN_AXE.getStrVsBlock(stack, state) > 1.0F )
        {
            return 5.5F;
        }
        else
        {
            return super.getStrVsBlock(stack, state);
        }
    }
    
    
    
    
		private long getStoredPower(ItemStack stack, Object object) {
			return TeslaUtils.getStoredPower(stack, null);
		}


	@Override
    public ICapabilityProvider initCapabilities(final ItemStack stack, NBTTagCompound nbt) {
        return new BaseTeslaContainerProvider(new BaseTeslaContainer(100000, 100, 100){
            @Override
            public long givePower(long Tesla, boolean simulated) {
            setDamage(stack, 0);
            return super.givePower(Tesla, simulated);
            }

            @Override
            public long takePower(long Tesla, boolean simulated) {
            setDamage(stack, 0);
            return super.takePower(Tesla, simulated);
            }
        });
    }
    
    
}