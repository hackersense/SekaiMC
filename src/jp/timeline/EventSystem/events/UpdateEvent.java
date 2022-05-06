package jp.timeline.EventSystem.events;

import jp.timeline.EventSystem.EventCore;
import jp.timeline.EventSystem.Events;

public class UpdateEvent extends EventCore {
    public UpdateEvent() {
        super(Events.UPDATE);
    }
}
