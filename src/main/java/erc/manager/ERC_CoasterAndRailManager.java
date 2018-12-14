package erc.manager;

import erc.entity.Wrap_EntityCoaster;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erc._core.ERC_Logger;
import erc.entity.ERC_EntityCoaster;
import erc.entity.ERC_EntityCoasterSeat;
import erc.tileEntity.TileEntityRailBase;
import erc.tileEntity.Wrap_TileEntityRail;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ERC_CoasterAndRailManager {

//	public static ERC_TileEntityRailTest prevTileRail;
	public static int prevX = -1;
	public static int prevY = -1;
	public static int prevZ = -1;
	public static int nextX = -1;
	public static int nextY = -1;
	public static int nextZ = -1;
	// �R�[�X�^�[�ݒu�ʒu���N���C�A���g���ɒm�点��p
	public static int coastersetX = -1;
	public static int coastersetY = -1;
	public static int coastersetZ = -1;
	// �A���R�[�X�^�[�p�@�e�R�[�X�^�[ID
	private static int parentCoasterID = -1;
	// ���f���I��p�@���f��ID
	public static int saveModelID = -1;
	// ��Ԓ����_�ړ���
	public static float rotationViewYaw = 0f;
    public static float prevRotationViewYaw = 0f;
    public static float rotationViewPitch = 0f;
    public static float prevRotationViewPitch = 0f;
    // �J�����p�x����
    public static float rotationYaw = 0;      
	public static float prevRotationYaw = 0;  
	public static float rotationPitch = 0;    
	public static float prevRotationPitch = 0;
    public static float rotationRoll = 0f;
    public static float prevRotationRoll = 0f;
	
	public static TileEntityRailBase clickedTileForGUI;

	public static float xOffset = 0.0F;
	public static float yOffset = 0.0F;
	public static float zOffset = 0.0F;
	
	public ERC_CoasterAndRailManager()
	{
		ResetData();
		clickedTileForGUI = null;
	}

	public static void SetPrevData(int x, int y, int z)
	{
		prevX = x;
		prevY = y;
		prevZ = z;
	}
	public static void SetNextData(int x, int y, int z)
	{
		nextX = x;
		nextY = y;
		nextZ = z;
	}
	public static void ResetData()
	{
		prevX = -1;
		prevY = -1;
		prevZ = -1;
		nextX = -1;
		nextY = -1;
		nextZ = -1;
	}
	
	public static boolean isPlacedRail()
	{
		return isPlacedPrevRail() || isPlacedNextRail();
	}
	
	public static boolean isPlacedPrevRail()
	{
		return prevY > -1;
	}
	public static boolean isPlacedNextRail()
	{
		return nextY > -1;
	}
	
	public static Wrap_TileEntityRail GetPrevTileEntity(World world)
	{
		return (Wrap_TileEntityRail)world.getTileEntity(new BlockPos(prevX, prevY, prevZ));
	}
	public static Wrap_TileEntityRail GetNextTileEntity(World world)
	{
		return (Wrap_TileEntityRail)world.getTileEntity(new BlockPos(nextX, nextY, nextZ));
	}
	
	public static void OpenRailGUI(TileEntityRailBase tl)
	{
		clickedTileForGUI = tl;
	}
	public static void CloseRailGUI()
	{
		clickedTileForGUI = null;
	}
	
	public static void SetCoasterPos(int x, int y, int z)
	{
		coastersetX = x;
		coastersetY = y;
		coastersetZ = z;
	}
	
	public static void client_setParentCoaster(ERC_EntityCoaster parent)
	{
		parentCoasterID = parent.getEntityId();
	}
	
	public static ERC_EntityCoaster client_getParentCoaster(World world)
	{
		ERC_EntityCoaster ret = (ERC_EntityCoaster) world.getEntityByID(parentCoasterID);
		parentCoasterID = -1;
		return ret;
	}
	
//	@SideOnly(Side.CLIENT)
    public static void setAngles(float deltax, float deltay)
    {
        float f2 = rotationViewPitch;
        float f3 = rotationViewYaw;
        rotationViewYaw = (float)((double)rotationViewYaw + (double)deltax * 0.15D);
        rotationViewPitch = (float)((double)rotationViewPitch + (double)deltay * 0.15D);

        if (rotationViewPitch < -80.0F)rotationViewPitch = -80.0F;
        if (rotationViewPitch > 80.0F)rotationViewPitch = 80.0F;
        if (rotationViewYaw < -150.0F)rotationViewYaw = -150.0F;
        if (rotationViewYaw > 150.0F)rotationViewYaw = 150.0F;

        prevRotationViewPitch += rotationViewPitch - f2;
        prevRotationViewYaw += rotationViewYaw - f3;
    }
    
    public static void resetViewAngles()
    {
    	rotationViewYaw = 0f;      
    	prevRotationViewYaw = 0f;  
    	rotationViewPitch = 0f;    
    	prevRotationViewPitch = 0f;
    	rotationRoll = 0f;
    	prevRotationRoll = 0f;
    }
    
    public static void setRots(float y, float py, float p, float pp, float r, float pr)
    {
    	rotationYaw = y;      
    	prevRotationYaw = py;  
    	rotationPitch = p;    
    	prevRotationPitch = pp;
    	rotationRoll = r;
    	prevRotationRoll = pr;
    }
    public static void setRotRoll(float r, float pr)
    {
    	rotationRoll = r;
    	prevRotationRoll = pr;
    }
    
    @SideOnly(Side.CLIENT)
    public static void CameraProc(float f)
    {
//    	ERC_Logger.debugInfo("CoasterManager:CameraProc   p:"+rotationRoll+", pp:"+prevRotationRoll+", pt:"+f);
		if(Minecraft.getMinecraft().player.getRidingEntity() instanceof Wrap_EntityCoaster) {
			float roll = prevRotationRoll + (rotationRoll - prevRotationRoll) * f;
//			float xOff = 1.0F * (float)-Math.cos(roll * Math.PI / 180.0) + 1.0F; //minus is to the right
//			float yOff = 1.0F * (float)-Math.cos(roll * Math.PI / 180.0) + 1.25F; //minus is higher
//			System.out.println("Roll: " + roll + "; x: "+xOff);
//			GL11.glTranslatef(xOff, yOff, 0F);
			GL11.glTranslatef(0F, 1F, 0F);
    		GL11.glRotatef(roll, 0.0F, 0.0F, 1.0F);
    		GL11.glTranslatef(0F, -1F, 0F);
		}
//    	ERC_Logger.debugInfo(""+f+":::"+rotationViewRoll+":::"+prevRotationViewRoll);
    }
    
    
    static Vec3d dir;
    static double speed;
    static EntityPlayer player;
    public static void GetOffAndButtobi(EntityPlayer Player)
    {
    	if(/*!Player.worldObj.isRemote &&*/ Player.isSneaking())
    	{
    		if(Player.getRidingEntity() instanceof ERC_EntityCoasterSeat)
    		{
    			ERC_EntityCoasterSeat seat = (ERC_EntityCoasterSeat)Player.getRidingEntity();
    			dir = seat.parent.ERCPosMat.Dir;
    			player = Player;
    			speed = seat.parent.Speed;
    			//Player.motionX += seat.parent.Speed * dir.xCoord * 1;
    			//Player.motionY += seat.parent.Speed * dir.yCoord * 1;
    			//Player.motionZ += seat.parent.Speed * dir.zCoord * 1;
    			ERC_Logger.info(dir.toString());
    		}
    	}
    }
    public static void motionactive()
    {
    	player.motionX += speed * dir.x * 1;
    	player.motionY += speed * dir.y * 1;
		player.motionZ += speed * dir.z * 1;
    }
}

