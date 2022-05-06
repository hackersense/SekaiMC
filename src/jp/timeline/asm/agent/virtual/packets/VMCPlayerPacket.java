package jp.timeline.asm.agent.virtual.packets;

import jp.timeline.asm.agent.ObfuscatorHelper;

public class VMCPlayerPacket implements VMPacket {
    private final boolean onGround;

    public VMCPlayerPacket(boolean onGround)
    {
        this.onGround = onGround;
    }

    @Override
    public Object toMCPacket()
    {
        try
        {
            return Class.forName(ObfuscatorHelper.clazz("C03PacketPlayer")).getDeclaredConstructor(boolean.class).newInstance(onGround);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
