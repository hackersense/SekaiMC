package jp.timeline.EventSystem;

public class CurrentEvent
{
    private static EventCore event = null;

    public static void setEvent(EventCore event)
    {
        CurrentEvent.event = event;
    }

    public static EventCore getEvent()
    {
        return event;
    }
}
