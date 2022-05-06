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

    private static URL[] getURLs() {
        String cp = System.getProperty("java.class.path");
        String[] elements = cp.split(File.pathSeparator);
        if(elements.length == 0) {
            elements = new String[]{""};
        }

        URL[] urls = new URL[elements.length];

        for(int i = 0; i < elements.length; ++i) {
            try {
                URL url = (new File(elements[i])).toURI().toURL();
                urls[i] = url;
            } catch (MalformedURLException ignored) {
                ;
            }
        }

        return urls;
    }

    private static void loadClasspath(URL url) {
        try
        {
            ClassLoader classLoaderExt = AgentMain.class.getClassLoader();
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);
            method.invoke(classLoaderExt, url);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        EventManager.addListener(new EventHook());

        try {
            System.out.println("Injected");

            /*
            Arrays.stream(inst.getAllLoadedClasses())
                    .filter(c -> c.getName().equalsIgnoreCase("net.minecraft.launchwrapper.Launch") || c.getName().equalsIgnoreCase("net.minecraft.launchwrapper.LaunchClassLoader"))
                    .forEach(cls -> {
                        try {
                            System.out.println("Optifine init");
                            if (cls.getName().equalsIgnoreCase("net.minecraft.launchwrapper.Launch"))
                                inst.addTransformer(new OptiFineLaunch(), true);
                            else
                                inst.addTransformer(new OptiFineLaunchClassLoader(), true);
                            inst.retransformClasses(cls);
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    });
*/

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