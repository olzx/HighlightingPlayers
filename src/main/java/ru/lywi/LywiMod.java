package ru.lywi;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import ru.lywi.world.render.PlayerRender;

public class LywiMod implements ModInitializer {

	@Override
	public void onInitialize() {
		WorldRenderEvents.LAST.register(PlayerRender::playerBox);
	}
}
