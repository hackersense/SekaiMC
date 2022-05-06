package jp.timeline.asm.agent.transformer;

import javassist.CtClass;
import javassist.CtMethod;

public class OptiFineLaunchClassLoader extends MasterTransformer {
    public OptiFineLaunchClassLoader() {
        super("net.minecraft.launchwrapper.LaunchClassLoader");
    }

    @Override
    public CtClass transform(ClassLoader loader, String className, Class<?> classBeingRedefined, CtClass cls) throws Exception {
        CtMethod blockReach = cls.getDeclaredMethod("findClass");
        blockReach.insertBefore("if (name.contains(\"EventRegister\")) return null;");
        return cls;
    }
}
