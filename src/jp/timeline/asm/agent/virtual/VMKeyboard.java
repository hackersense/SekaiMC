package jp.timeline.asm.agent.virtual;

import java.lang.reflect.Method;

public class VMKeyboard {
    public static boolean next()
    {
        try
        {
            Class<?> keyboard = Class.forName("org.lwjgl.input.Keyboard");
            Method next = keyboard.getDeclaredMethod("next");
            if (!next.isAccessible())
                next.setAccessible(true);
            return (boolean) next.invoke(keyboard);
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static int getEventKey()
    {
        try
        {
            Class<?> keyboard = Class.forName("org.lwjgl.input.Keyboard");
            Method getEventKey = keyboard.getDeclaredMethod("getEventKey");
            if (!getEventKey.isAccessible())
                getEventKey.setAccessible(true);
            return (int) getEventKey.invoke(keyboard);
        } catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    public static char getEventCharacter()
    {
        try
        {
            Class<?> keyboard = Class.forName("org.lwjgl.input.Keyboard");
            Method getEventCharacter = keyboard.getDeclaredMethod("getEventCharacter");
            if (!getEventCharacter.isAccessible())
                getEventCharacter.setAccessible(true);
            return (char) getEventCharacter.invoke(keyboard);
        } catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean getEventKeyState()
    {
        try
        {
            Class<?> keyboard = Class.forName("org.lwjgl.input.Keyboard");
            Method getEventKeyState = keyboard.getDeclaredMethod("getEventKeyState");
            if (!getEventKeyState.isAccessible())
                getEventKeyState.setAccessible(true);
            return (boolean) getEventKeyState.invoke(keyboard);
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isKeyDown(int keyCode)
    {
        try
        {
            Class<?> keyboard = Class.forName("org.lwjgl.input.Keyboard");
            for (Method method : keyboard.getDeclaredMethods())
            {
                if (method.getName().equalsIgnoreCase("isKeyDown"))
                {
                    if (!method.isAccessible())
                        method.setAccessible(true);
                    return (boolean) method.invoke(keyboard, keyCode);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
