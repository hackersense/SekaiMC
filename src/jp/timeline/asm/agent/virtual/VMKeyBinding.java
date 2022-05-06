package jp.timeline.asm.agent.virtual;

import jp.timeline.asm.agent.ObfuscatorHelper;

import java.lang.reflect.Method;

public class VMKeyBinding {
    private final Object instance;

    public VMKeyBinding(Object instance) {
        this.instance = instance;
    }

    public static void onTick(int keyCode)
    {
        try
        {
            Class<?> keyBinding = Class.forName(ObfuscatorHelper.clazz("KeyBinding"));
            Method onTick = keyBinding.getDeclaredMethod(ObfuscatorHelper.method("onTick"), int.class);
            if (!onTick.isAccessible())
                onTick.setAccessible(true);
            onTick.invoke(keyBinding, keyCode);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public int getKeyCode()
    {
        try
        {
            Method getKeyCode = instance.getClass().getDeclaredMethod(ObfuscatorHelper.method("getKeyCode"));
            if (!getKeyCode.isAccessible())
                getKeyCode.setAccessible(true);
            return (int) getKeyCode.invoke(instance);
        } catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean isKeyDown() {
        try {
            Method isKeyDown = instance.getClass().getDeclaredMethod(ObfuscatorHelper.method("isKeyDown"));
            if (!isKeyDown.isAccessible())
                isKeyDown.setAccessible(true);
            return (boolean) isKeyDown.invoke(instance);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
