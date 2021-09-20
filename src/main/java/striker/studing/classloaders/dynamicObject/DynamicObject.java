package striker.studing.classloaders.dynamicObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class DynamicObject {
    private final Map<String, DynamicMethod> methods = new HashMap<>();
    {
        for (Method method : getClass().getMethods()) {
            methods.put(
                    method.getName(),
                    args -> {
                        try {
                            return method.invoke(this, args);
                        } catch (Exception ignored){
                            return null;
                        }
                    }
            );
        }
    }
    @SuppressWarnings("unchecked")
    public <P> Object invokeMethod(String name, P... params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return methods.get(name).invoke(params);
    }
    public void addMethod(String name, DynamicMethod<?, ?> method){
        methods.put(name, method);
    }
    @SuppressWarnings("unchecked")
    public <V> void addMethod(String name, Consumer method){
        methods.put(name, args -> {
            method.accept(args);
            return null;
        });
    }
}
