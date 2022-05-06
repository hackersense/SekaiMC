package jp.timeline.asm.agent.transformer;

import javassist.CtClass;
import javassist.CtMethod;

public class PlayerControllerMPTransformer extends MasterTransformer {
    public PlayerControllerMPTransformer() {
        super("bda");
    }

    @Override
    public CtClass transform(ClassLoader loader, String className, Class<?> classBeingRedefined, CtClass cls) throws Exception {
        System.out.println("Find PlayerControllerMP Hook");

        CtMethod blockReach = cls.getDeclaredMethod("d");

        blockReach.setBody("return 7.0F;");

        return cls;
    }
}
