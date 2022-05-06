package jp.timeline.asm.agent.virtual;

import jp.timeline.asm.agent.ObfuscatorHelper;

import java.lang.reflect.Method;

public class VMTessellator {
    private final Object instance;

    public VMTessellator(Object instance)
    {
        this.instance = instance;
    }

    public VMWorldRenderer getWorldRenderer()
    {
        try
        {
            Method getWorldRenderer = instance.getClass().getDeclaredMethod(ObfuscatorHelper.method("getWorldRenderer"));
            if (!getWorldRenderer.isAccessible())
                getWorldRenderer.setAccessible(true);
            return new VMWorldRenderer(getWorldRenderer.invoke(instance));
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static VMTessellator getInstance() {
        try
        {
            Class<?> tessellator = Class.forName(ObfuscatorHelper.clazz("Tessellator"));
            Method getInstance = tessellator.getDeclaredMethod(ObfuscatorHelper.method("getInstance"));
            if (!getInstance.isAccessible())
                getInstance.setAccessible(true);
            Object instance = getInstance.invoke(tessellator);
            return new VMTessellator(instance);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
