package jp.timeline.asm.agent.hook;

import jp.timeline.EventSystem.EventListener;
import jp.timeline.EventSystem.EventManager;
import jp.timeline.EventSystem.Events;
import jp.timeline.EventSystem.events.BlockReachEvent;
import jp.timeline.EventSystem.events.ClickMouseEvent;
import jp.timeline.EventSystem.events.HitboxEvent;
import jp.timeline.EventSystem.events.KeyEvent;
import jp.timeline.asm.agent.UITest;
import jp.timeline.asm.agent.module.AutoClicker;
import jp.timeline.asm.agent.module.NoFall;
import jp.timeline.asm.agent.virtual.*;

public class EventHook {
    @EventListener(event = Events.TICK)
    public void onTick() {
        AutoClicker.onTick();

        if (UITest.fastPlace && VMMinecraft.getInstance() != null)
            VMMinecraft.getInstance().setRightClickDelayTimer(0);

        if (VMKeyboard.getEventKeyState() &&VMMinecraft.getInstance() != null && VMMinecraft.getInstance().currentScreenIsNull()) {
            int k = VMKeyboard.getEventKey() == 0 ? VMKeyboard.getEventCharacter() + 256 : VMKeyboard.getEventKey();
            EventManager.call(new KeyEvent(k));
        }
    }

    @EventListener
    public void onHitboxes(HitboxEvent event)
    {
        if (UITest.hitboxsize != 0)
            event.setSize(UITest.hitboxsize);
    }

    @EventListener
    public void onClickMouse(ClickMouseEvent event)
    {
        if (UITest.autoClicker && VMMinecraft.getInstance() != null) {
            VMMinecraft.getInstance().setLeftClickCounter(0);
        }
    }

    @EventListener
    public void onBlockReach(BlockReachEvent event)
    {
        if (UITest.blockReachsize != 0)
            event.setReach(UITest.blockReachsize); // Max 7 blocks
    }

    @EventListener(event = Events.SHUT_DOWN)
    public void onShutdown()
    {
        System.out.println("Shutdown");
    }

    @EventListener(event = Events.UPDATE)
    public void onUpdate()
    {
        if (VMMinecraft.getInstance() != null && VMMinecraft.getInstance().getPlayer() != null && UITest.Sprint)
        {
            VMEntityPlayerSP player = VMMinecraft.getInstance().getPlayer();
            if (player.getFoodStats().getFoodLevel() > 6 && !player.isSprinting() && player.getMovementInput().getMoveForward() > 0)
                player.setSprinting(true);
        }
    }
}
