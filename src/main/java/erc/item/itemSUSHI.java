package erc.item;

import erc.entity.entitySUSHI;
import erc.manager.ERC_CoasterAndRailManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class itemSUSHI extends Item{
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(world.isRemote) {
			ERC_CoasterAndRailManager.zOffset -= 0.5F;
			System.out.println("zOffset: "+ERC_CoasterAndRailManager.zOffset);
		}
    	return EnumActionResult.SUCCESS;
    }
}
	