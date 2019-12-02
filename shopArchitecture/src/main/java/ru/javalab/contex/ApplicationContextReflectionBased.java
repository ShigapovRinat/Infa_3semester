package ru.javalab.contex;

import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ApplicationContextReflectionBased implements ApplicationContext {

    private Map<String, Component> components;

    public ApplicationContextReflectionBased() {
        this.components = new HashMap<>();

        Reflections reflections = new Reflections("ru.javalab");
        Set<Class<? extends Component>> allComponents = reflections.getSubTypesOf(Component.class);

        for (Class<? extends Component> componentClass : allComponents) {
            try {
                Component component = componentClass.getConstructor().newInstance();
                components.put(component.getName(), component);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        List<Component> objectComponents = new ArrayList<>(components.values());
        for (Component comp1 : objectComponents) {
            for (Field field : comp1.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                for (Component comp2 : objectComponents) {
                    if (field.getType().isAssignableFrom(comp2.getClass())) {
                        try {
                            field.set(comp1, comp2);
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException(e);
                        }
                    }
                }
            }
        }
    }
    @Override
    public <T extends Component> T getComponent(Class<T> componentType, String name) {
        return (T) components.get(name);
    }


}
