package committee.nova.elephantfix.mixin;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.inventory.Container;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryEffectRenderer.class)
public abstract class MixinInventoryEffectRenderer extends GuiContainer {
    public MixinInventoryEffectRenderer(Container container) {
        super(container);
    }

    @Inject(method = "func_147044_g", at = @At(value = "HEAD"))
    public void inject$drawActivePotionEffects$1(CallbackInfo ci) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
    }

    @Inject(method = "func_147044_g", at = @At("TAIL"))
    public void inject$drawActivePotionEffects$2(CallbackInfo ci) {
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
}
