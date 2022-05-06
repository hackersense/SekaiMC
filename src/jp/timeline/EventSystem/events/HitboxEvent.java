package jp.timeline.EventSystem.events;

import jp.timeline.EventSystem.EventCore;
import jp.timeline.EventSystem.Events;

public class HitboxEvent extends EventCore {
    private float size;

    public HitboxEvent(float size) {
        super(Events.HIT_BOX);
        this.size = size;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }
}
