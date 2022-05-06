package jp.timeline.asm.agent.virtual;

import jp.timeline.asm.agent.FieldUtils;
import jp.timeline.asm.agent.ObfuscatorHelper;

public class VMPlayerControllerMP {
    private final Object instance;

    public VMPlayerControllerMP(Object instance)
    {
        this.instance = instance;
    }

    public VMGameType getCurrentGameType()
    {
        Object clazz = FieldUtils.getFieldClassOfClass(instance, ObfuscatorHelper.field("currentGameType"));
        return clazz != null ? new VMGameType(clazz) : null;
    }

    public float getCurrentBlockDamageMP()
    {
        return FieldUtils.getFieldFloatOfClass(instance, ObfuscatorHelper.field("curBlockDamageMP"));
    }

    public boolean extendedReach()
    {
        return this.getCurrentGameType().isCreative();
    }
}
