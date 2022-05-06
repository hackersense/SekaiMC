package jp.timeline.asm.agent.virtual;

import jp.timeline.asm.agent.FieldUtils;
import jp.timeline.asm.agent.ObfuscatorHelper;

import java.lang.reflect.Method;

public class VMEntity {
    private final Object instance;
    private final Class<?> clazz;

    public VMEntity(Object instance)
    {
        this.instance = instance;
        this.clazz = instance.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass();
    }

    public boolean isSprinting()
    {
        try
        {
            Method isSprinting = clazz.getDeclaredMethod(ObfuscatorHelper.method("isSprinting"));
            if (!isSprinting.isAccessible())
                isSprinting.setAccessible(true);
            return (boolean) isSprinting.invoke(instance);
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public VMVec3 getPositionEyes(float partialTicks)
    {
        try
        {
            for (Method method : instance.getClass().getDeclaredMethods())
            {
                if (method.getName().equalsIgnoreCase(ObfuscatorHelper.method("getPositionEyes")))
                {
                    if (!method.isAccessible())
                        method.setAccessible(true);
                    return new VMVec3(method.invoke(instance, partialTicks));
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public VMVec3 getLook(float partialTicks)
    {
        try
        {
            for (Method method : instance.getClass().getDeclaredMethods())
            {
                if (method.getName().equalsIgnoreCase(ObfuscatorHelper.method("getLook")))
                {
                    if (!method.isAccessible())
                        method.setAccessible(true);
                    return new VMVec3(method.invoke(instance, partialTicks));
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public double getFallDistance()
    {
        return FieldUtils.getFieldFloatOfClass(instance, clazz, ObfuscatorHelper.field("fallDistance"));
    }

    public double getPositionX()
    {
        return FieldUtils.getFieldDoubleOfClass(clazz, instance, ObfuscatorHelper.field("posX"));
    }

    public double getPositionY()
    {
        return FieldUtils.getFieldDoubleOfClass(clazz, instance, ObfuscatorHelper.field("posY"));
    }

    public double getPositionZ()
    {
        return FieldUtils.getFieldDoubleOfClass(clazz, instance, ObfuscatorHelper.field("posZ"));
    }
}
