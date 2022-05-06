package jp.timeline.asm.agent.virtual;

public class VMClientWorld extends VMWorld {
    private final Object instance;

    public VMClientWorld(Object instance)
    {
        super(instance);
        this.instance = instance;
    }
}
