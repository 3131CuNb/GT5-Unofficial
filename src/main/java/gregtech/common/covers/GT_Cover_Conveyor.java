package gregtech.common.covers;

import gregtech.api.interfaces.tileentity.ICoverable;
import gregtech.api.interfaces.tileentity.IMachineProgress;
import gregtech.api.util.GT_CoverBehavior;
import gregtech.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;

public class GT_Cover_Conveyor
        extends GT_CoverBehavior {
    public final int mTickRate;

    public GT_Cover_Conveyor(int aTickRate) {
        this.mTickRate = aTickRate;
    }

    public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity, long aTimer) {
        if ((aCoverVariable % 6 > 1) && ((aTileEntity instanceof IMachineProgress))) {
            if (((IMachineProgress) aTileEntity).isAllowedToWork() != aCoverVariable % 6 < 4) {
                return aCoverVariable;
            }
        }
        TileEntity tTileEntity = aTileEntity.getTileEntityAtSide(aSide);
        //aTileEntity.decreaseStoredEnergyUnits(1L, true);
        if (((aCoverVariable % 2 == 0) || (aSide != 1)) && ((aCoverVariable % 2 != 0) || (aSide != 0)) && (aTileEntity.getUniversalEnergyCapacity() >= 128L)) {
            if (aTileEntity.isUniversalEnergyStored(256L)) {
                aTileEntity.decreaseStoredEnergyUnits(4 * GT_Utility.moveOneItemStack(aCoverVariable % 2 == 0 ? aTileEntity : tTileEntity, aCoverVariable % 2 != 0 ? aTileEntity : tTileEntity, aCoverVariable % 2 != 0 ? GT_Utility.getOppositeSide(aSide) : aSide, aCoverVariable % 2 == 0 ? GT_Utility.getOppositeSide(aSide) : aSide, null, false, (byte) 64, (byte) 1, (byte) 64, (byte) 1), true);
            }
        } else {
            GT_Utility.moveOneItemStack(aCoverVariable % 2 == 0 ? aTileEntity : tTileEntity, aCoverVariable % 2 != 0 ? aTileEntity : tTileEntity, aCoverVariable % 2 != 0 ? GT_Utility.getOppositeSide(aSide) : aSide, aCoverVariable % 2 == 0 ? GT_Utility.getOppositeSide(aSide) : aSide, null, false, (byte) 64, (byte) 1, (byte) 64, (byte) 1);
        }
        return aCoverVariable;
    }

    public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
        aCoverVariable = (aCoverVariable + (aPlayer.isSneaking()? -1 : 1)) % 12;
        if(aCoverVariable <0){aCoverVariable = 11;}
        switch(aCoverVariable) {
            case 0: GT_Utility.sendChatToPlayer(aPlayer, trans("006")); break;
            case 1: GT_Utility.sendChatToPlayer(aPlayer, trans("007")); break;
            case 2: GT_Utility.sendChatToPlayer(aPlayer, trans("008")); break;
            case 3: GT_Utility.sendChatToPlayer(aPlayer, trans("009")); break;
            case 4: GT_Utility.sendChatToPlayer(aPlayer, trans("010")); break;
            case 5: GT_Utility.sendChatToPlayer(aPlayer, trans("011")); break;
            case 6: GT_Utility.sendChatToPlayer(aPlayer, trans("012")); break;
            case 7: GT_Utility.sendChatToPlayer(aPlayer, trans("013")); break;
            case 8: GT_Utility.sendChatToPlayer(aPlayer, trans("014")); break;
            case 9: GT_Utility.sendChatToPlayer(aPlayer, trans("015")); break;
            case 10: GT_Utility.sendChatToPlayer(aPlayer, trans("016")); break;
            case 11: GT_Utility.sendChatToPlayer(aPlayer, trans("017")); break;
        }
        return aCoverVariable;
    }

    public boolean letsRedstoneGoIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return true;
    }

    public boolean letsRedstoneGoOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return true;
    }

    public boolean letsEnergyIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return true;
    }

    public boolean letsEnergyOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return true;
    }

    public boolean letsFluidIn(byte aSide, int aCoverID, int aCoverVariable, Fluid aFluid, ICoverable aTileEntity) {
        return true;
    }

    public boolean letsFluidOut(byte aSide, int aCoverID, int aCoverVariable, Fluid aFluid, ICoverable aTileEntity) {
        return true;
    }

    public boolean letsItemsIn(byte aSide, int aCoverID, int aCoverVariable, int aSlot, ICoverable aTileEntity) {
        return (aCoverVariable >= 6) || (aCoverVariable % 2 != 0);
    }

    public boolean letsItemsOut(byte aSide, int aCoverID, int aCoverVariable, int aSlot, ICoverable aTileEntity) {
        return (aCoverVariable >= 6) || (aCoverVariable % 2 == 0);
    }

    public boolean alwaysLookConnected(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return true;
    }

    public int getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return this.mTickRate;
    }
}
