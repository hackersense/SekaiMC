package jp.timeline.asm.agent.virtual;

import java.lang.reflect.Method;

public class VMMouse {
    public static boolean isButtonDown(int keyCode)
    {
        try
        {
            Class<?> mouse = Class.forName("org.lwjgl.input.Mouse");
            for (Method method : mouse.getDeclaredMethods())
            {
                if (method.getName().equalsIgnoreCase("isButtonDown"))
                {
                    if (!method.isAccessible())
                        method.setAccessible(true);
                    return (boolean) method.invoke(mouse, keyCode);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
