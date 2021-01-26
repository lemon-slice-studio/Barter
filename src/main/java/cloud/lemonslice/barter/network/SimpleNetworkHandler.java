package cloud.lemonslice.barter.network;

import cloud.lemonslice.barter.network.client.TradeStationPurchaseMessage;
import cloud.lemonslice.barter.network.client.TradeStationSpecialPurchaseMessage;
import cloud.lemonslice.barter.network.client.TradeStationTextChangeMessage;
import cloud.lemonslice.silveroak.network.INormalMessage;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.function.Function;

import static cloud.lemonslice.barter.Barter.MODID;

public final class SimpleNetworkHandler
{
    public static final String NETWORK_VERSION = "1.0";
    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(MODID, "main")).networkProtocolVersion(() -> NETWORK_VERSION).serverAcceptedVersions(NETWORK_VERSION::equals).clientAcceptedVersions(NETWORK_VERSION::equals).simpleChannel();

    public static void init()
    {
        int id = 0;
        registerMessage(id++, TradeStationTextChangeMessage.class, TradeStationTextChangeMessage::new);
        registerMessage(id++, TradeStationPurchaseMessage.class, TradeStationPurchaseMessage::new);
        registerMessage(id++, TradeStationSpecialPurchaseMessage.class, TradeStationSpecialPurchaseMessage::new);
    }

    private static <T extends INormalMessage> void registerMessage(int index, Class<T> messageType, Function<PacketBuffer, T> decoder)
    {
        CHANNEL.registerMessage(index, messageType, INormalMessage::toBytes, decoder, (message, context) ->
        {
            message.process(context);
            context.get().setPacketHandled(true);
        });
    }
}
