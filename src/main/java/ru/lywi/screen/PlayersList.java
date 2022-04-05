package ru.lywi.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import ru.lywi.world.entity.EntityUtils;

import java.util.ArrayList;


public class PlayersList extends Screen {
    public static ArrayList<PlayerEntity> playersList = new ArrayList<>();

    public PlayersList(Text title) {
        super(title);

        playersList.clear();
        playersList.addAll(EntityUtils.getEntityListInRange(PlayerEntity.class, 80));
    }

    public void init() {
        this.addDrawableChild(new ButtonWidget(20, this.height-40, 50, 20, new LiteralText("Закрыть"), action -> this.client.setScreen((Screen)null)));

        int margin = 20;
        for (Entity entity : playersList) {
            this.addDrawableChild(new ButtonWidget(20, margin, 60, 20, entity.getDisplayName(), action -> {
                MinecraftClient.getInstance().player.lookAt(entity.getCommandSource().getEntityAnchor(), entity.getPos());
            }));
            margin += 25;
        }
    }
}
