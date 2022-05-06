package jp.timeline.asm.agent.virtual;

import jp.timeline.asm.agent.FieldUtils;
import jp.timeline.asm.agent.ObfuscatorHelper;

import java.lang.reflect.Method;

public class VMMinecraft {
    private final Object instance;

    public VMMinecraft(Object instance) {
        this.instance = instance;
    }

    public VMEntityPlayerSP getPlayer() {
        Object clazz = FieldUtils.getFieldClassOfClass(instance, ObfuscatorHelper.field("thePlayer"));
        return clazz != null ? new VMEntityPlayerSP(clazz) : null;
    }

    public VMGameSettings getGameSettings() {
        Object clazz = FieldUtils.getFieldClassOfClass(instance, ObfuscatorHelper.field("gameSettings"));
        return clazz != null ? new VMGameSettings(clazz) : null;
    }

    public void setLeftClickCounter(int leftClickCounter) {
        try
        {
            FieldUtils.setField(FieldUtils.getFieldOfClass(instance, ObfuscatorHelper.field("leftClickCounter")), instance, leftClickCounter);
        }
        catch (NoSuchFieldException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    public void setRightClickDelayTimer(int rightClickDelayTimer) {
        try
        {
            FieldUtils.setField(FieldUtils.getFieldOfClass(instance, ObfuscatorHelper.field("rightClickDelayTimer")), instance, rightClickDelayTimer);
        }
        catch (NoSuchFieldException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    public VMEntity getRenderViewEntity() {
        try
        {
            Method getRenderViewEntity = instance.getClass().getDeclaredMethod(ObfuscatorHelper.method("getRenderViewEntity"));
            if (!getRenderViewEntity.isAccessible())
                getRenderViewEntity.setAccessible(true);
            return new VMEntity(getRenderViewEntity.invoke(instance));
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public boolean currentScreenIsNull() {
        Object clazz = FieldUtils.getFieldClassOfClass(instance, ObfuscatorHelper.field("currentScreen"));
        return clazz == null;
    }

    public VMClientWorld getWorld() {
        Object clazz = FieldUtils.getFieldClassOfClass(instance, ObfuscatorHelper.field("theWorld"));
        return clazz != null ? new VMClientWorld(clazz) : null;
    }

    public VMPlayerControllerMP getPlayerController() {
        Object clazz = FieldUtils.getFieldClassOfClass(instance, ObfuscatorHelper.field("playerController"));
        return clazz != null ? new VMPlayerControllerMP(clazz) : null;
    }

    public VMTimer getTimer() {
        Object clazz = FieldUtils.getFieldClassOfClass(instance, ObfuscatorHelper.field("timer"));
        return clazz != null ? new VMTimer(clazz) : null;
    }

    public static VMMinecraft getInstance() {
        try
        {
            Class<?> minecraft = Class.forName(ObfuscatorHelper.clazz("Minecraft"));
            Method getMinecraft = minecraft.getDeclaredMethod(ObfuscatorHelper.method("getMinecraft"));
            if (!getMinecraft.isAccessible())
                getMinecraft.setAccessible(true);
            Object theMinecraft = getMinecraft.invoke(minecraft);
            return new VMMinecraft(theMinecraft);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
