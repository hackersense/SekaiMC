package jp.timeline.EventSystem;

import jp.timeline.EventSystem.type.EventType;

public class EventCore
{
    public static final String name = "EventSystem";
    public static final String dev = "HackerSense";
    public static final String ver = "3.1";
    private final Events event;
    private EventType type = EventType.NONE;
    private boolean isCancel = false;

    public EventCore(Events event)
    {
        this.event = event;
    }

    public Events getEvents()
    {
        return event;
    }

    public void setCancel(boolean cancel)
    {
        isCancel = cancel;
    }

    public boolean isCancel()
    {
        return isCancel;
    }

    public void setType(EventType type)
    {
        this.type = type;
    }

    public EventType getType()
    {
        return type;
    }
}
