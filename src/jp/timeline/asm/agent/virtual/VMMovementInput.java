package jp.timeline.asm.agent.virtual;

import jp.timeline.asm.agent.FieldUtils;
import jp.timeline.asm.agent.ObfuscatorHelper;

public class VMMovementInput {
    private final Object instance;
    private final Class<?> clazz;

    public VMMovementInput(Object instance) {
        this.instance = instance;
        this.clazz = instance.getClass().getSuperclass();
    }

    public float getMoveForward() {
        return FieldUtils.getFieldFloatOfClass(instance, clazz, ObfuscatorHelper.field("moveForward"));
    }
}
