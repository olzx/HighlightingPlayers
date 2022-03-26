package ru.lywi.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.ArrayList;

public class PlayersList extends Screen {
    public ArrayList<PlayerEntity> playersList = new ArrayList<>();

    public PlayersList(Text title) {
        super(title);

        playersList.addAll(getPlayerEntityList());
    }

    private ArrayList<PlayerEntity> getPlayerEntityList() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        World world = MinecraftClient.getInstance().world;
        ArrayList<PlayerEntity> list = new ArrayList<>();

        if (player != null && world != null && world.isClient()) {
            BlockPos playerPos = new BlockPos(player.getPos());
            list.addAll(world.getEntitiesByClass(
                    PlayerEntity.class,
                    new Box(playerPos.getX() - 200,
                            playerPos.getY() - 200,
                            playerPos.getZ() - 200,
                            playerPos.getX() + 200,
                            playerPos.getY() + 200,
                            playerPos.getZ() + 200),
                    Entity::isAlive)
            );
        }
        return list;
    }
}
