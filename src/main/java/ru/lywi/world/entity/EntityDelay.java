package ru.lywi.world.entity;

import net.minecraft.entity.Entity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EntityDelay <T extends Entity>{
    private final T entity;
    private Calendar calendar;

    public EntityDelay(T entity, int secondsDelay) {
        this.entity = entity;
        this.calendar = new GregorianCalendar();
        this.addSeconds(secondsDelay);
    }

    public T getEntity() {
        return entity;
    }

    public Date getDate() {
        return this.calendar.getTime();
    }

    public void addSeconds(int seconds) {
        int sec = this.calendar.get(Calendar.SECOND);
        this.calendar.set(Calendar.SECOND, sec + seconds);
    }
}
