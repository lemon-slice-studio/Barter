package cloud.lemonslice.barter.common.block;

import net.minecraft.util.IStringSerializable;

public enum EdgeType implements IStringSerializable
{
    NONE("none"),
    IRON("iron"),
    GOLD("gold"),
    LAPIS("lapis"),
    EMERALD("emerald"),
    DIAMOND("diamond"),
    REDSTONE("redstone"),
    NETHERITE("netherite"),
    QUARTZ("quartz");

    private final String edge;

    EdgeType(String name)
    {
        this.edge = name;
    }

    public String toString()
    {
        return this.getString();
    }

    public String getString()
    {
        return this.edge;
    }
}
