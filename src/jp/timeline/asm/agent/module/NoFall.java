package jp.timeline.asm.agent.module;

import jp.timeline.asm.agent.virtual.VMMinecraft;
import jp.timeline.asm.agent.virtual.packets.VMCPlayerPacket;

public class NoFall {
    public static void onUpdate()
    {
        if (VMMinecraft.getInstance() != null && VMMinecraft.getInstance().getPlayer() != null && VMMinecraft.getInstance().getPlayer().getFallDistance() > 2.5)
            VMMinecraft.getInstance().getPlayer().getSendQueue().addToSendQueue(new VMCPlayerPacket(true));
    }
}
