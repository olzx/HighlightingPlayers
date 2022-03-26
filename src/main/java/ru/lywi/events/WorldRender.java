package ru.lywi.events;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

public class WorldRender {
    public WorldRender() {
        WorldRenderEvents.END.register(event -> {
            
        });
    }
}
