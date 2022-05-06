package jp.timeline.asm.agent.virtual;

import jp.timeline.asm.agent.ObfuscatorHelper;
import jp.timeline.asm.agent.virtual.packets.VMPacket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class VMNetHandlerPlayClient {
    private final Object instance;

    public VMNetHandlerPlayClient(Object instance) {
        this.instance = instance;
    }

    public void addToSendQueue(VMPacket packet)
    {
        try
        {
            for (Method method : instance.getClass().getDeclaredMethods())
            {
                if (method.getName().equalsIgnoreCase(ObfuscatorHelper.method("addToSendQueue")))
                {
                    if (!method.isAccessible())
                        method.setAccessible(true);
                    method.invoke(instance, packet.toMCPacket());
                }
            }
        }
        catch (IllegalArgumentException ignored)
        {

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
