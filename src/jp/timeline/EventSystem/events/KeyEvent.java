package jp.timeline.EventSystem.events;

import jp.timeline.EventSystem.EventCore;
import jp.timeline.EventSystem.Events;

public class KeyEvent extends EventCore {
    private final int keyCode;

    public KeyEvent(int keyCode) {
        super(Events.KEY);
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
