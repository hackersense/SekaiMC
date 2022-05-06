package jp.timeline.asm.agent.virtual;

import jp.timeline.asm.agent.FieldUtils;
import jp.timeline.asm.agent.ObfuscatorHelper;

import java.lang.reflect.Method;

public class VMVec3 {
    private final Object instance;

    public VMVec3(Object instance)
    {
        this.instance = instance;
    }

    public VMVec3 addVector(double x, double y, double z)
    {
        try
        {
            for (Method method : instance.getClass().getDeclaredMethods())
            {
                if (method.getName().equalsIgnoreCase(ObfuscatorHelper.method("addVector")))
                {
                    if (!method.isAccessible())
                        method.setAccessible(true);
                    return new VMVec3(method.invoke(instance, x, y, z));
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public double getXCoord()
    {
        return FieldUtils.getFieldDoubleOfClass(instance, ObfuscatorHelper.field("xCoord"));
    }

    public double getYCoord()
    {
        return FieldUtils.getFieldDoubleOfClass(instance, ObfuscatorHelper.field("yCoord"));
    }

    public double getZCoord()
    {
        return FieldUtils.getFieldDoubleOfClass(instance, ObfuscatorHelper.field("zCoord"));
    }
}
