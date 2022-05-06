package jp.timeline.EventSystem.type;

public enum EventType
{
    NONE("None", -1),
    PRE("Pre", 0),
    POST("Post", 1),
    OUTGOING("OutGoing", 2),
    INCOMING("InComing", 3);

    private final String name;
    private final int level;

    EventType(String name, int level)
    {
        this.name = name;
        this.level = level;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    public int getLevel()
    {
        return this.level;
    }
}