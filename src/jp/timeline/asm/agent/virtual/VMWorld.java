package jp.timeline.asm.agent.virtual;

public class VMWorld {
    private final Object instance;
    private final Class<?> clazz;

    public VMWorld(Object instance)
    {
        this.instance = instance;
        this.clazz = instance.getClass().getSuperclass();
    }
}
