package jp.timeline.EventSystem.events;

import jp.timeline.EventSystem.EventCore;
import jp.timeline.EventSystem.Events;

public class ShutdownEvent extends EventCore
{
    public ShutdownEvent()
    {
        super(Events.SHUT_DOWN);
    }
}
