package jp.timeline.asm.agent.module;

import jp.timeline.asm.agent.UITest;
import jp.timeline.asm.agent.virtual.VMKeyBinding;
import jp.timeline.asm.agent.virtual.VMMinecraft;

public class AutoClicker {
    private static final VMMinecraft mc = VMMinecraft.getInstance();

    private static long rightDelay = randomClickDelay(UITest.autoClicker_RightCPS_MIN, UITest.autoClicker_RightCPS_MAX);
    private static long rightLastSwing = 0L;
    private static long leftDelay = randomClickDelay(UITest.autoClicker_LeftCPS_MIN, UITest.autoClicker_LeftCPS_MAX);
    private static long leftLastSwing = 0L;

    public static void onTick() {
        if (mc != null && mc.getPlayer() != null && mc.getGameSettings() != null && UITest.autoClicker) {
            // Left click
            if (mc.getGameSettings().getKeyBindAttack().isKeyDown() && UITest.autoClicker_Left &&
                    System.currentTimeMillis() - leftLastSwing >= leftDelay && mc.getPlayerController().getCurrentBlockDamageMP() == 0F) {
                VMKeyBinding.onTick(mc.getGameSettings().getKeyBindAttack().getKeyCode()); // Minecraft Click Handling

                leftLastSwing = System.currentTimeMillis();
                leftDelay = randomClickDelay(UITest.autoClicker_LeftCPS_MIN, UITest.autoClicker_LeftCPS_MAX);
            }

            // Right click
            if (mc.getGameSettings().getKeyBindUseItem().isKeyDown() && !mc.getPlayer().isUsingItem() && UITest.autoClicker_Right &&
                    System.currentTimeMillis() - rightLastSwing >= rightDelay) {
                VMKeyBinding.onTick(mc.getGameSettings().getKeyBindUseItem().getKeyCode()); // Minecraft Click Handling

                rightLastSwing = System.currentTimeMillis();
                rightDelay = randomClickDelay(UITest.autoClicker_RightCPS_MIN, UITest.autoClicker_RightCPS_MAX);
            }
        }
    }

    public static long randomClickDelay(final int minCPS, final int maxCPS) {
        return (long) ((Math.random() * (1000 / minCPS - 1000 / maxCPS + 1)) + 1000 / maxCPS);
    }
}
