package ru.lywi.world.render.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.util.math.Box;

public class WorldRender {
    public static void renderBox(Box box) {
        double d = box.minX;
        double e = box.minY;
        double f = box.minZ;
        double g = box.maxX;
        double h = box.maxY;
        double i = box.maxZ;
        float red = 1.0F;
        float green = 0.0F;
        float blue = 0.0F;
        float alpha = 0.5F;

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);
        RenderSystem.enableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        bufferBuilder.vertex(d, e, f).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(d, e, i).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(d, h, f).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(d, h, i).color(red, green, blue, alpha).next();
        tessellator.draw();

        bufferBuilder.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(d, h, i).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(d, e, i).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(g, h, i).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(g, e, i).color(red, green, blue, alpha).next();
        tessellator.draw();

        bufferBuilder.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(g, e, i).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(g, e, f).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(g, h, i).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(g, h, f).color(red, green, blue, alpha).next();
        tessellator.draw();

        bufferBuilder.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(g, h, f).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(g, e, f).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(d, h, f).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(d, e, f).color(red, green, blue, alpha).next();
        tessellator.draw();

        bufferBuilder.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(d, e, f).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(g, e, f).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(d, e, i).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(g, e, i).color(red, green, blue, alpha).next();
        tessellator.draw();

        bufferBuilder.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(d, h, f).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(d, h, i).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(g, h, f).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(g, h, i).color(red, green, blue, alpha).next();
        tessellator.draw();
    }
}
