package ru.lywi.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import ru.lywi.world.entity.EntityDelay;
import ru.lywi.world.entity.EntityDistance;
import ru.lywi.world.entity.EntityUtils;

import java.util.ArrayList;


public class PlayersList extends Screen {
    public static ArrayList<PlayerEntity> playersList = new ArrayList<>();
    public static ArrayList<EntityDelay<PlayerEntity>> playerListDelay = new ArrayList<>();
    private final ArrayList<EntityDistance> entityDistances;
    private final PlayerEntity player;
    private int secondsShowGlow = 5;

    public PlayersList(Text title) {
        super(title);

        playersList.clear();
        playersList.addAll(EntityUtils.getEntityListInRange(PlayerEntity.class, 80));
        this.entityDistances = getEntityDistanceArray(playersList);
        this.sortEntityDistance(entityDistances);
        this.player = MinecraftClient.getInstance().player;
    }

    public void init() {
        this.addDrawableChild(new ButtonWidget(20, this.height-40, 50, 20, new LiteralText("Закрыть"), action -> this.client.setScreen((Screen)null)));

        int margin = 20;
        for (EntityDistance entityDistance : this.entityDistances) {
            PlayerEntity entity = (PlayerEntity) entityDistance.getEntity();
            if (entity == this.player) continue;
            String displayName = entity.getDisplayName().getString();
            int distanceToEntity = entityDistance.getDistance();
            String distanceInString = "[" + distanceToEntity + "]";
            String finalString = displayName + " " + distanceInString;
            int width = displayName.length() > 0 ? finalString.length()*5+20 : 50;
            this.addDrawableChild(new ButtonWidget(20, margin, width, 20, new LiteralText(finalString), action -> {
                MinecraftClient.getInstance().player.lookAt(entity.getCommandSource().getEntityAnchor(), entity.getPos());
                EntityDelay<PlayerEntity> entityDelay = new EntityDelay<>(entity, secondsShowGlow);
                playerListDelay.add(entityDelay);
            }));
            margin += 25;
        }
    }

    private int getDistanceToEntity(Entity entity) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null)
            return (int) player.distanceTo(entity);
        else
            return -1;
    }

    private ArrayList<EntityDistance> getEntityDistanceArray(ArrayList<PlayerEntity> playerEntity) {
        ArrayList<EntityDistance> entityDistancesArray = new ArrayList<>();
        for (PlayerEntity player : playerEntity) {
            int distanceToEntity = getDistanceToEntity(player);
            EntityDistance entityDistance = new EntityDistance(player, distanceToEntity);
            entityDistancesArray.add(entityDistance);
        }
        return entityDistancesArray;
    }

    private void sortEntityDistance(ArrayList<EntityDistance> entityDistancesArray) {
        for(int i = entityDistancesArray.size()-1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){
                EntityDistance entityDistance = entityDistancesArray.get(j);
                EntityDistance entityDistanceNext = entityDistancesArray.get(j+1);
                int distance = entityDistance.getDistance();
                int distanceNext = entityDistanceNext.getDistance();

                if( distance > distanceNext ){
                    entityDistancesArray.set(j, entityDistanceNext);
                    entityDistancesArray.set(j+1, entityDistance);
                }
            }
        }
    }
}
