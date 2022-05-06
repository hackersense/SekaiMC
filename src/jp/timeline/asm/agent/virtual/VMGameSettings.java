package jp.timeline.asm.agent.virtual;

import jp.timeline.asm.agent.FieldUtils;
import jp.timeline.asm.agent.ObfuscatorHelper;

public class VMGameSettings {
    private final Object instance;

    public VMGameSettings(Object instance) {
        this.instance = instance;
    }

    public VMKeyBinding getKeyBindAttack()
    {
        Object clazz = FieldUtils.getFieldClassOfClass(instance, ObfuscatorHelper.field("keyBindAttack"));
        return clazz != null ? new VMKeyBinding(clazz) : null;
    }

    public VMKeyBinding getKeyBindUseItem()
    {
        Object clazz = FieldUtils.getFieldClassOfClass(instance, ObfuscatorHelper.field("keyBindUseItem"));
        return clazz != null ? new VMKeyBinding(clazz) : null;
    }
}
