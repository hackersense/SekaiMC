package jp.timeline.EventSystem;

import jp.timeline.EventSystem.type.EventPriority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventListener
{
    Events event() default Events.NONE;
    EventPriority priority() default EventPriority.MEDIUM;
}
