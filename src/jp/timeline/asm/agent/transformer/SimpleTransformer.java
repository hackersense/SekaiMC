package jp.timeline.asm.agent.transformer;

import jp.timeline.asm.agent.EventRegister;
import jp.timeline.asm.agent.SimulationCore;
import jp.timeline.asm.agent.ObfuscatorHelper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.List;

public class SimpleTransformer implements ClassFileTransformer {
    private final List<String> maps;

    public SimpleTransformer(List<String> maps)
    {
        this.maps = maps;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!maps.contains(className))
            return null;

        ClassReader classReader = new ClassReader(classfileBuffer);
        ClassNode classNode = new ClassNode();

        // The debug information is removed since it isn't needed and causes bugs
        classReader.accept(classNode, ClassReader.SKIP_DEBUG);

        for (MethodNode node : classNode.methods)
        {
            if (className.equals(ObfuscatorHelper.clazz("Minecraft")))
            {
                if (node.name.equals(ObfuscatorHelper.method("runTick")))
                {
                    System.out.println("Create onTick");
                    node.instructions.insert(this.getEventInsnTick());
                }

                if (node.name.equals(ObfuscatorHelper.method("clickMouse")))
                {
                    System.out.println("Create onClickMouse");
                    node.instructions.insert(this.getEventInsnClickMouse());
                }

                if (node.name.equals(ObfuscatorHelper.method("shutdown")))
                {
                    System.out.println("Create onShutdown");
                    node.instructions.insert(this.getEventInsnShutdown());
                }
            }

            if (className.equals(ObfuscatorHelper.clazz("EntityPlayerSP")))
                if (node.name.equals(ObfuscatorHelper.method("onUpdateWalkingPlayer")))
                {
                    System.out.println("Create onUpdate");
                    node.instructions.insert(this.getEventInsnUpdate());
                }

            if (className.equals(ObfuscatorHelper.clazz("Entity")))
                if (node.name.equals(ObfuscatorHelper.method("getCollisionBorderSize")))
                {
                    System.out.println("Create onHitboxes");
                    node.instructions.insert(this.getEventInsnHitboxes());
                }

            if (className.equals(ObfuscatorHelper.clazz("PlayerControllerMP")))
                if (node.name.equals(ObfuscatorHelper.method("getBlockReachDistance")))
                {
                    System.out.println("Create onBlockReach");
                    node.instructions.insert(this.getEventInsnBlockReach());
                }
        }

        // Recalculate frames and maximums. All classes are available at runtime
        // so it makes the agent a lot safer from producing illegal classes
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

        classNode.accept(classWriter);

        return classWriter.toByteArray();
    }

    private InsnList getEventInsnTick() {
        InsnList insnList = new InsnList();
        insnList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, EventRegister.class.getName().replace(".", "/"), "callTick", "()V", false));
        return insnList;
    }

    private InsnList getEventInsnClickMouse() {
        InsnList insnList = new InsnList();
        insnList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, EventRegister.class.getName().replace(".", "/"), "callClickMouse", "()V", false));
        return insnList;
    }

    private InsnList getEventInsnUpdate() {
        InsnList insnList = new InsnList();
        insnList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, EventRegister.class.getName().replace(".", "/"), "callUpdate", "()V", false));
        return insnList;
    }

    private InsnList getEventInsnHitboxes() {
        InsnList insnList = new InsnList();
        insnList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, SimulationCore.class.getName().replace(".", "/"), "getHitboxes", "()F", false));
        insnList.add(new InsnNode(Opcodes.FRETURN));
        return insnList;
    }

    private InsnList getEventInsnShutdown() {
        InsnList insnList = new InsnList();
        insnList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, EventRegister.class.getName().replace(".", "/"), "callShutdown", "()V", false));
        return insnList;
    }

    private InsnList getEventInsnBlockReach() {
        InsnList insnList = new InsnList();
        insnList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, SimulationCore.class.getName().replace(".", "/"), "getBlockReachDistance", "()F", false));
        insnList.add(new InsnNode(Opcodes.FRETURN));
        return insnList;
    }
}
