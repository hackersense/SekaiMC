package jp.timeline.EventSystem.events;

import jp.timeline.EventSystem.EventCore;
import jp.timeline.EventSystem.Events;

public class ClickMouseEvent extends EventCore {
    public ClickMouseEvent()
    {
        super(Events.CLICK_MOUSE);
    }
}
