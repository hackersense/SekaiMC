package jp.timeline.asm.agent;

import java.util.HashMap;
import java.util.Map;

public class ObfuscatorHelper {
    private static final Map<String, String> minecraftClassMapping = new HashMap<String, String>()
    {{
        put("Minecraft", "ave");
        put("Tessellator", "bfx");
        put("C03PacketPlayer", "ip");
        put("Timer", "avl");
        put("KeyBinding", "avb");
        put("EntityPlayerSP", "bew");
        put("PlayerControllerMP", "bda");
        put("EntityPlayer", "wn");
        put("Entity", "pk");
        put("FoodStats", "pk");
    }};

    private static final Map<String, String> minecraftFieldMapping = new HashMap<String, String>()
    {{
        put("thePlayer", "h");
        put("theWorld", "f");
        put("onGround", "f");
        put("sendQueue", "a");
        put("leftClickCounter", "ag");
        put("curBlockDamageMP", "e");
        put("keyBindAttack", "ai");
        put("keyBindUseItem", "ag");
        put("playerController", "c");
        put("currentScreen", "m");
        put("timer", "Y");
        put("rightClickDelayTimer", "ap");
        put("timerSpeed", "d");
        put("posX", "s");
        put("posY", "t");
        put("posZ", "u");
        put("fallDistance", "O");
        put("foodLevel", "a");
        put("currentGameType", "i");
        put("gameSettings", "t");
        put("movementInput", "b");
        put("moveForward", "b");
        put("xCoord", "a");
        put("yCoord", "b");
        put("zCoord", "c");
    }};

    private static final Map<String, String> minecraftMethodMapping = new HashMap<String, String>()
    {{
        put("getMinecraft", "A");
        put("getInstance", "a");
        put("getWorldRenderer", "c");
        put("isSprinting", "aw");
        put("addToSendQueue", "a");
        put("clickMouse", "aw");
        put("getBlockReachDistance", "d");
        put("onTick", "a");
        put("isKeyDown", "d");
        put("getKeyCode", "i");
        put("isUsingItem", "bS");
        put("getRenderViewEntity", "ac");
        put("getPositionEyes", "e");
        put("getFoodStats", "cl");
        put("addVector", "b");
        put("getLook", "d");
        put("runTick", "s");
        put("shutdown", "m");
        put("getCollisionBorderSize", "ao");
        put("isCreative", "d");
        put("onUpdateWalkingPlayer", "p");
        put("setSprinting", "d");
    }};

    public static String field(String fieldName)
    {
        return minecraftFieldMapping.getOrDefault(fieldName, fieldName);
    }

    public static String method(String methodName)
    {
        return minecraftMethodMapping.getOrDefault(methodName, methodName);
    }

    public static String clazz(String className)
    {
        return minecraftClassMapping.getOrDefault(className, className);
    }
}
