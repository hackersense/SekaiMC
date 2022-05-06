package jp.timeline.EventSystem.type;

public enum EventPriority {
    HIGHEST("Highest", 0),
    HIGH("High", 1),
    MEDIUM("Medium", 2),
    LOW("Low", 3),
    LOWEST("Lowest", 4);

    private final String name;
    private final int level;

    EventPriority(String name, int level)
    {
        this.name = name;
        this.level = level;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }
}