package net.quantumdeathlord.randommod.client;

import com.mojang.blaze3d.platform.InputConstants;
import cpw.mods.util.Lazy;
import net.minecraft.client.KeyMapping;

import org.lwjgl.glfw.GLFW;

public class Keybindings {

    public static final Lazy<KeyMapping> HEART_RIP = Lazy.of(() ->
            new KeyMapping(
                    "key.random_mod.heartRip",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_U,
                    "key.categories.misc"
            ));


}
