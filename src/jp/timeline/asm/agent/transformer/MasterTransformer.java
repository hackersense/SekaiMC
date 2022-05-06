package jp.timeline.asm.agent.transformer;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public abstract class MasterTransformer implements ClassFileTransformer {
    public ClassPool pool;
    public String toTransform;

    public MasterTransformer(String toTransform) {
        this.pool = ClassPool.getDefault();
        this.toTransform = toTransform;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if(!className.replaceAll("/", ".").equals(toTransform))
            return classfileBuffer;

        try {
            pool.appendClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));
            pool.appendClassPath(new LoaderClassPath(loader));

            CtClass ctClass = pool.makeClass(new ByteArrayInputStream(classfileBuffer));

            if(!ctClass.isFrozen()) {
                try {
                    return transform(loader, className, classBeingRedefined, ctClass).toBytecode();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return classfileBuffer;
    }

    public abstract CtClass transform(ClassLoader loader, String className, Class<?> classBeingRedefined, CtClass cls) throws Exception;
}
