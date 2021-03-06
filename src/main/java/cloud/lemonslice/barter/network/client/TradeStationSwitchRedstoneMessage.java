package cloud.lemonslice.barter.network.client;

import cloud.lemonslice.barter.common.tileentity.TradeStationBlockTileEntity;
import cloud.lemonslice.silveroak.network.INormalMessage;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class TradeStationSwitchRedstoneMessage implements INormalMessage
{
    private final BlockPos pos;

    public TradeStationSwitchRedstoneMessage(BlockPos pos)
    {
        this.pos = pos;
    }

    public TradeStationSwitchRedstoneMessage(PacketBuffer buf)
    {
        pos = buf.readBlockPos();
    }

    @Override
    public void toBytes(PacketBuffer packetBuffer)
    {
        packetBuffer.writeBlockPos(pos);
    }

    @Override
    public void process(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context ctx = supplier.get();
        ServerWorld world = Objects.requireNonNull(ctx.getSender()).getServerWorld();
        if (ctx.getDirection() == NetworkDirection.PLAY_TO_SERVER)
            ctx.enqueueWork(() ->
            {
                if (world.isAreaLoaded(pos, 1))
                {
                    TileEntity te = world.getTileEntity(pos);
                    String uuid = ctx.getSender().getUniqueID().toString();
                    if (te instanceof TradeStationBlockTileEntity && (((TradeStationBlockTileEntity) te).checkOwner(uuid) || ((TradeStationBlockTileEntity) te).checkStaff(uuid)))
                    {
                        ((TradeStationBlockTileEntity) te).switchRedstoneMode();
                    }
                }
            });
    }
}
