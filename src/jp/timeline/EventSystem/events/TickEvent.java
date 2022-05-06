package jp.timeline.EventSystem.events;

import jp.timeline.EventSystem.EventCore;
import jp.timeline.EventSystem.Events;

public class TickEvent extends EventCore
{
    public TickEvent()
    {
        super(Events.TICK);
    }
}
