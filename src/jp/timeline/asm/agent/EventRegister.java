package jp.timeline.asm.agent;

import jp.timeline.EventSystem.EventManager;
import jp.timeline.EventSystem.events.*;

public class EventRegister {
    public static void callTick()
    {
        EventManager.call(new TickEvent());
    }

    public static void callUpdate()
    {
        EventManager.call(new UpdateEvent());
    }

    public static void callHitboxes(float size)
    {
        EventManager.call(new HitboxEvent(size));
    }

    public static void callShutdown() {
        EventManager.call(new ShutdownEvent());
    }

    public static void callClickMouse() {
        EventManager.call(new ClickMouseEvent());
    }
}
