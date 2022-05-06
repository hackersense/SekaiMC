package jp.timeline.asm.agent.transformer;

import javassist.CtClass;
import javassist.CtMethod;

public class OptiFineLaunch extends MasterTransformer {
    public OptiFineLaunch() {
        super("net.minecraft.launchwrapper.Launch");
    }

    @Override
    public CtClass transform(ClassLoader loader, String className, Class<?> classBeingRedefined, CtClass cls) throws Exception {
        CtMethod blockReach = cls.getDeclaredMethod("launch");
        blockReach.insertBefore("return;");
        return cls;
    }
}
