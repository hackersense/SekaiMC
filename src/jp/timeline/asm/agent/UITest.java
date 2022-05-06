package jp.timeline.asm.agent;

import jdk.nashorn.internal.objects.NativeBoolean;
import org.ice1000.jimgui.*;
import org.ice1000.jimgui.util.JniLoader;

public class UITest {
    public static float hitboxsize = 0;
    public static float blockReachsize = 0;
    public static boolean fastPlace = false;
    public static boolean Sprint = false;
    public static boolean autoClicker = false;
    public static boolean autoClicker_Right = false;
    public static boolean autoClicker_Left = false;
    public static int autoClicker_RightCPS_MIN = 1;
    public static int autoClicker_RightCPS_MAX = 1;
    public static int autoClicker_LeftCPS_MIN = 1;
    public static int autoClicker_LeftCPS_MAX = 1;

    public static void main(String[] args) {
        drawUI();
    }

    public static void drawUI()
    {
        JniLoader.load();
        NativeFloat hitboxes = new NativeFloat();
        NativeFloat blockReach = new NativeFloat();
        NativeBool fastplace = new NativeBool();
        NativeBool sprint = new NativeBool();
        NativeBool autoClick = new NativeBool();
        NativeBool autoClickRight = new NativeBool();
        NativeBool autoClickLeft = new NativeBool();
        NativeInt autoClickRightCPS_MIN = new NativeInt();
        NativeInt autoClickRightCPS_MAX = new NativeInt();
        NativeInt autoClickLeftCPS_MIN = new NativeInt();
        NativeInt autoClickLeftCPS_MAX = new NativeInt();
        hitboxes.modifyValue(hitboxsize);
        blockReach.modifyValue(blockReachsize);
        fastplace.modifyValue(fastPlace);
        sprint.modifyValue(Sprint);
        autoClick.modifyValue(autoClicker);
        autoClickRight.modifyValue(autoClicker_Right);
        autoClickLeft.modifyValue(autoClicker_Left);
        autoClickRightCPS_MIN.modifyValue(autoClicker_RightCPS_MIN);
        autoClickRightCPS_MAX.modifyValue(autoClicker_RightCPS_MAX);
        autoClickLeftCPS_MIN.modifyValue(autoClicker_LeftCPS_MIN);
        autoClickLeftCPS_MAX.modifyValue(autoClicker_LeftCPS_MAX);
        //JImGuiUtil.runWithinPer(800, 15, imGui -> {
        //    imGui.checkbox("my_id", pOpen);
        //});
        try (JImGui imGui = new JImGui(800, 600, "Sekai UI")) {
            // load fonts, global initializations, etc.
            while (!imGui.windowShouldClose()) {
                // some drawing-unrelated initializations
                // mostly do nothing here
                imGui.initNewFrame();
                imGui.styleColorsLight();
                // draw your widgets here, like this
                imGui.sliderFloat("Hitbox Size", hitboxes, 0.0F, 1.0F, "%.2f");
                imGui.sliderFloat("Block reach", blockReach, 0.0F, 7.0F, "%.1f");
                imGui.checkbox("Fast place", fastplace);
                imGui.checkbox("Sprint", sprint);
                imGui.checkbox("Auto Clicker", autoClick);
                if (autoClick.accessValue())
                {
                    imGui.checkbox("Auto Clicker - Right", autoClickRight);
                    imGui.checkbox("Auto Clicker - Left", autoClickLeft);
                    if (autoClickRight.accessValue())
                    {
                        imGui.sliderInt("Auto Clicker - Right CPS (Min)", autoClickRightCPS_MIN, 1, 20);
                        imGui.sliderInt("Auto Clicker - Right CPS (Max)", autoClickRightCPS_MAX, 1, 20);
                    }
                    if (autoClickLeft.accessValue())
                    {
                        imGui.sliderInt("Auto Clicker - Left CPS (Min)", autoClickLeftCPS_MIN, 1, 20);
                        imGui.sliderInt("Auto Clicker - Left CPS (Max)", autoClickLeftCPS_MAX, 1, 20);
                    }
                }
                if (autoClickRightCPS_MIN.intValue() > autoClickRightCPS_MAX.intValue())
                {
                    autoClickRightCPS_MIN.modifyValue(autoClickRightCPS_MAX.intValue());
                }
                if (autoClickLeftCPS_MIN.intValue() > autoClickLeftCPS_MAX.intValue())
                {
                    autoClickLeftCPS_MIN.modifyValue(autoClickLeftCPS_MAX.intValue());
                }
                imGui.render();
                hitboxsize = hitboxes.floatValue();
                blockReachsize = blockReach.floatValue();
                fastPlace = fastplace.accessValue();
                Sprint = sprint.accessValue();
                autoClicker = autoClick.accessValue();
                autoClicker_Right = autoClickRight.accessValue();
                autoClicker_Left = autoClickLeft.accessValue();
                autoClicker_RightCPS_MIN = autoClickRightCPS_MIN.intValue();
                autoClicker_RightCPS_MAX = autoClickRightCPS_MAX.intValue();
                autoClicker_LeftCPS_MIN = autoClickLeftCPS_MIN.intValue();
                autoClicker_LeftCPS_MAX = autoClickLeftCPS_MAX.intValue();
                // mostly do nothing here
            }
        }
    }
}
