package ru.javalab.contex;

// объект этого класса
// создает объекты
// всех интерфейсов Component с помощью рефлексии
public interface ApplicationContext {
    // при получении компонента, если у этого компонента
    // есть зависимости - то нужно их тоже проставить
    <T extends Component> T getComponent(Class<T> componentType, String name);
}
