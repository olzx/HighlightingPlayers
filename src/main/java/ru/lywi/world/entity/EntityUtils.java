package ru.lywi.world.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EntityUtils {
    public static <T extends net.minecraft.entity.Entity> ArrayList<T> getEntityListInRange(Class<T> entityClass, int range) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        World world = MinecraftClient.getInstance().world;
        ArrayList<T> list = new ArrayList<>();

        if (player != null && world != null && world.isClient()) {
            BlockPos playerPos = new BlockPos(player.getPos());
            List<T> entityList = world.getEntitiesByClass(
                    entityClass,
                    new Box(playerPos.getX() - range,
                            playerPos.getY() - range,
                            playerPos.getZ() - range,
                            playerPos.getX() + range,
                            playerPos.getY() + range,
                            playerPos.getZ() + range),
                    net.minecraft.entity.Entity::isAlive);
            list.addAll(entityList);
        }
        return list;
    }
}
