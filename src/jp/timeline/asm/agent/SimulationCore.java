package jp.timeline.asm.agent;

import jp.timeline.EventSystem.EventManager;
import jp.timeline.EventSystem.events.BlockReachEvent;
import jp.timeline.EventSystem.events.HitboxEvent;
import jp.timeline.asm.agent.virtual.VMMinecraft;

public class SimulationCore {
    public static float getHitboxes()
    {
        HitboxEvent event = (HitboxEvent) EventManager.call(new HitboxEvent(0.1F));
        return event.getSize();
    }

    public static float getBlockReachDistance()
    {
        BlockReachEvent event = (BlockReachEvent) EventManager.call(new BlockReachEvent(VMMinecraft.getInstance() != null && VMMinecraft.getInstance().getPlayerController().extendedReach() ? 5.0F : 4.5F));
        return event.getReach();
    }
}
