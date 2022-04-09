package ru.lywi.world.render;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import ru.lywi.screen.PlayersList;
import ru.lywi.world.entity.EntityDelay;
import ru.lywi.world.render.utils.WorldRender;

public class PlayerRender {
    public static void playerBox(WorldRenderContext wrc) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        BlockPos playerPos = player.getBlockPos();
        Vec3d cameraPos = wrc.gameRenderer().getCamera().getPos();

        for (EntityDelay<PlayerEntity> entityDelay: PlayersList.playerListDelay) {
            Entity entity = entityDelay.getEntity();
            if (entity == player) continue;
            boolean timePassed = entityDelay.timePassed();
            if (timePassed) {
                PlayersList.playerListDelay.remove(entityDelay);
                return;
            }

            Box box = new Box(entity.getBlockPos()).offset(cameraPos.negate());
            WorldRender.renderBox(box);
            WorldRender.renderBox(box.offset(0, 1, 0));
        }
    }
}
