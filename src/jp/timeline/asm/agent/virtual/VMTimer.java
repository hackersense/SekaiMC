package jp.timeline.asm.agent.virtual;

import jp.timeline.asm.agent.FieldUtils;
import jp.timeline.asm.agent.ObfuscatorHelper;

public class VMTimer {
    private final Object instance;

    public VMTimer(Object instance)
    {
        this.instance = instance;
    }

    public float getTimerSpeed() {
        return FieldUtils.getFieldFloatOfClass(instance, ObfuscatorHelper.field("timerSpeed"));
    }

    public void setTimerSpeed(float timerSpeed) {
        try
        {
            FieldUtils.setField(FieldUtils.getFieldOfClass(this.instance, ObfuscatorHelper.field("timerSpeed")), instance, timerSpeed);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
