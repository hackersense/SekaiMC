package jp.timeline.asm.agent.virtual;

import jp.timeline.asm.agent.ObfuscatorHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class VMEntityPlayer extends VMEntity {
    private final Object instance;
    private final Class<?> clazz;

    public VMEntityPlayer(Object instance)
    {
        super(instance);
        this.instance = instance;
        this.clazz = instance.getClass().getSuperclass().getSuperclass();
    }

    public VMFoodStats getFoodStats()
    {
        try
        {
            Method getFoodStats = clazz.getDeclaredMethod(ObfuscatorHelper.method("getFoodStats"));
            if (!getFoodStats.isAccessible())
                getFoodStats.setAccessible(true);
            Object foodStats = getFoodStats.invoke(instance);
            return new VMFoodStats(foodStats);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isUsingItem()
    {
        try
        {
            Method isUsingItem = clazz.getDeclaredMethod(ObfuscatorHelper.method("isUsingItem"));
            if (!isUsingItem.isAccessible())
                isUsingItem.setAccessible(true);
            return (boolean) isUsingItem.invoke(instance);
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
