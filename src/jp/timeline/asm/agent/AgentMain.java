package jp.timeline.asm.agent;

import jp.timeline.EventSystem.EventManager;
import jp.timeline.asm.agent.hook.EventHook;
import jp.timeline.asm.agent.transformer.*;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class AgentMain {
    public static final List<String> classList = new ArrayList<String>(){{
        add(ObfuscatorHelper.clazz("Minecraft"));
        add(ObfuscatorHelper.clazz("Entity"));
        add(ObfuscatorHelper.clazz("EntityPlayerSP"));
        add(ObfuscatorHelper.clazz("PlayerControllerMP"));
    }};

    public static void agentmain(String agentArgs, Instrumentation inst) {
        EventManager.addListener(new EventHook());

        try {
            System.out.println("Injected");

            boolean isOptiFineOrForge = false;

            for (Class<?> c : inst.getAllLoadedClasses())
                if (c.getName().equalsIgnoreCase("net.minecraft.launchwrapper.LaunchClassLoader")) {
                    isOptiFineOrForge = true;
                    break;
                }
            
            inst.addTransformer(new SimpleTransformer(classList), true);

            Arrays.stream(inst.getAllLoadedClasses())
                    .filter(c -> classList.contains(c.getName()))
                    .forEach(cls -> {
                        try {
                            //inst.addTransformer(classList.get(cls.getName()), true);
                            inst.retransformClasses(cls);
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    });
        } catch (Throwable e) {
            e.printStackTrace();
        }

        UITest.drawUI();
    }

    public static void shutdown()
    {
        System.out.println("shutdown");
    }
}
