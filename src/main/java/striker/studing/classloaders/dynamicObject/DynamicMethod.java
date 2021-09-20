package striker.studing.classloaders.dynamicObject;

@FunctionalInterface
public interface DynamicMethod<P, R> {
    R invoke(P... params);
}
