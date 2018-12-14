package erc.item;

import erc.entity.ERC_EntityCoaster;
import erc.entity.ERC_EntityCoasterConnector;
import erc.manager.ERC_CoasterAndRailManager;
import erc.tileEntity.Wrap_TileEntityRail;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ERC_ItemCoasterConnector extends ERC_ItemCoaster{

	public ERC_ItemCoasterConnector()
	{
		super();
		CoasterType = 1;
	}
	
	public ERC_EntityCoaster getItemInstance(World world, Wrap_TileEntityRail tile, double x, double y, double z)
	{
		return new ERC_EntityCoasterConnector(world, tile.getRail(), x, y, z);
	}

	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(world.isRemote) {
			ERC_CoasterAndRailManager.xOffset -= 0.5F;
			System.out.println("xOffset: "+ERC_CoasterAndRailManager.xOffset);
		}
		return EnumActionResult.SUCCESS; // �A���p�R�[�X�^�[�͒��ڐݒu�ł��Ȃ�
    }
}
