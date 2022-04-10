package ru.lywi.world.entity;

import net.minecraft.entity.Entity;

public class EntityDistance {
    private final Entity entity;
    private final int distance;

    public EntityDistance(Entity entity, int distance) {
        this.entity = entity;
        this.distance = distance;
    }

    public Entity getEntity() {
        return entity;
    }

    public int getDistance() {
        return distance;
    }
}
