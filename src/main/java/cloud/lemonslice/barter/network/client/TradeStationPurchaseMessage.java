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

public class TradeStationPurchaseMessage implements INormalMessage
{
    private final BlockPos pos;
    private final boolean all;

    public TradeStationPurchaseMessage(BlockPos pos, boolean all)
    {
        this.pos = pos;
        this.all = all;
    }

    public TradeStationPurchaseMessage(PacketBuffer buf)
    {
        pos = buf.readBlockPos();
        all = buf.readBoolean();
    }

    @Override
    public void toBytes(PacketBuffer packetBuffer)
    {
        packetBuffer.writeBlockPos(pos);
        packetBuffer.writeBoolean(all);
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
                    if (te instanceof TradeStationBlockTileEntity && !((TradeStationBlockTileEntity) te).isLocked())
                    {
                        ((TradeStationBlockTileEntity) te).purchase(ctx.getSender(), all);
                    }
                }
            });
    }
}
