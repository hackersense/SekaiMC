package jp.timeline.EventSystem.events;

import jp.timeline.EventSystem.EventCore;
import jp.timeline.EventSystem.Events;

public class BlockReachEvent extends EventCore {
    private float reach;

    public BlockReachEvent(float reach)
    {
        super(Events.BLOCK_REACH);
        this.reach = reach;
    }

    public void setReach(float reach) {
        this.reach = reach;
    }

    public float getReach() {
        return reach;
    }
}
