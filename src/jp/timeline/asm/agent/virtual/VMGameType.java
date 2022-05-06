package jp.timeline.asm.agent.virtual;

import jp.timeline.asm.agent.ObfuscatorHelper;

import java.lang.reflect.Method;

public class VMGameType {
    private final Object instance;

    public VMGameType(Object instance)
    {
        this.instance = instance;
    }

    public boolean isCreative()
    {
        try
        {
            Method isCreative = instance.getClass().getDeclaredMethod(ObfuscatorHelper.method("isCreative"));
            if (!isCreative.isAccessible())
                isCreative.setAccessible(true);
            return (boolean) isCreative.invoke(instance);
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
