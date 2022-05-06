package jp.timeline.EventSystem.threads;

import jp.timeline.EventSystem.*;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInvoke extends Thread {
    private final Method method;
    private final Object listener;
    private final EventCore event;

    public MethodInvoke(String name, Method method, Object listener, EventCore event)
    {
        super(name);
        this.method = method;
        this.listener = listener;
        this.event = event;
    }

    @Override
    public void run()
    {
        try
        {
            MethodHandle handle = EventManager.lookup.unreflect(method);
            Events events = method.getDeclaredAnnotation(EventListener.class).event();

            if (events != Events.NONE && (events == event.getEvents() || events == Events.ALL))
            {
                if (method.getParameterCount() == 1)
                {
                    Class<?> methodObject = method.getParameterTypes()[0];
                    if (methodObject != event.getClass())
                        throw new EventException("[EventSystem] The event does not match! method name:" + method.getName());
                    handle.invoke(listener, event);
                }
                else
                {
                    handle.invoke(listener);
                }
            }
            else if (events == Events.NONE && method.getParameterCount() == 1)
            {
                Class<?> methodObject = method.getParameterTypes()[0];

                if (methodObject != null && methodObject == event.getClass())
                    handle.invoke(listener, event);
            }
            else if (events == event.getEvents())
            {
                throw new EventException("Incorrect usage! method name:" + method.getName());
            }
        }
        catch (InvocationTargetException | IllegalAccessException ignored)
        {

        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
        }
    }
}
