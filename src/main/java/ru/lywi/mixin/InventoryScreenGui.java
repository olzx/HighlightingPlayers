package ru.lywi.mixin;

import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenGui extends Screen {
    private static final Identifier QUESTION_ICON_TEXTURE = new Identifier("lywi", "textures/gui/question.png");

    public InventoryScreenGui(Text title)
    {
        super(title);
    }

    @Inject(at = @At("HEAD"), method = "init")
    private void addButtonInScreen(CallbackInfo ci) {
        this.addDrawableChild(new TexturedButtonWidget(5, 5, 20, 20, 0, 0, 20, QUESTION_ICON_TEXTURE, 32, 64, button -> this.client.setScreen(new DeathScreen(new LiteralText("DEAD"), false)), new TranslatableText("Players list")));
    }
}
