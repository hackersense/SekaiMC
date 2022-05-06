package jp.timeline.asm.agent.virtual;

import jp.timeline.asm.agent.FieldUtils;
import jp.timeline.asm.agent.ObfuscatorHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class VMEntityPlayerSP extends VMEntityPlayer {
    private final Object instance;

    public VMEntityPlayerSP(Object instance)
    {
        super(instance);
        this.instance = instance;
    }

    public VMMovementInput getMovementInput()
    {
        Object clazz = FieldUtils.getFieldClassOfClass(instance, ObfuscatorHelper.field("movementInput"));
        return clazz != null ? new VMMovementInput(clazz) : null;
    }

    public VMNetHandlerPlayClient getSendQueue()
    {
        Object clazz = FieldUtils.getFieldClassOfClass(instance, ObfuscatorHelper.field("sendQueue"));
        return clazz != null ? new VMNetHandlerPlayClient(clazz) : null;
    }

    public void setSprinting(boolean sprinting)
    {
        try
        {
            for (Method method : instance.getClass().getDeclaredMethods())
            {
                if (method.getName().equalsIgnoreCase(ObfuscatorHelper.method("setSprinting")))
                {
                    if (!method.isAccessible())
                        method.setAccessible(true);
                    method.invoke(instance, sprinting);
                }
            }
        }
        catch (IllegalArgumentException ignored)
        {

        }
        catch (InvocationTargetException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}
