package jp.timeline.asm.agent.virtual;

import jp.timeline.asm.agent.FieldUtils;
import jp.timeline.asm.agent.ObfuscatorHelper;

public class VMFoodStats {
    private final Object instance;

    public VMFoodStats(Object instance) {
        this.instance = instance;
    }

    public int getFoodLevel() {
        return FieldUtils.getFieldIntOfClass(instance, ObfuscatorHelper.field("foodLevel"));
    }
}
