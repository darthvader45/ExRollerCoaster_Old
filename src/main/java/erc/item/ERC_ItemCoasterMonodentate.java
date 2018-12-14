package erc.item;

import erc.entity.ERC_EntityCoaster;
import erc.entity.ERC_EntityCoasterMonodentate;
import erc.manager.ERC_CoasterAndRailManager;
import erc.tileEntity.Wrap_TileEntityRail;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ERC_ItemCoasterMonodentate extends ERC_ItemCoaster{

	public ERC_ItemCoasterMonodentate()
	{
		super();
		CoasterType = 2;
	}

	@Override
	public ERC_EntityCoaster getItemInstance(World world, Wrap_TileEntityRail tile, double x, double y, double z)
    {
    	return new ERC_EntityCoasterMonodentate(world, tile.getRail(),x, y, z);
    }

	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(world.isRemote) {
			ERC_CoasterAndRailManager.yOffset += 0.5F;
			System.out.println("yOffset: "+ERC_CoasterAndRailManager.yOffset);
		}
		return EnumActionResult.SUCCESS; // �A���p�R�[�X�^�[�͒��ڐݒu�ł��Ȃ�
	}
}
