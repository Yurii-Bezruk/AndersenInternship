package striker.studing.classloaders;

import striker.studing.classloaders.dynamicObject.DynamicObject;

public class Main {
    public static void main(String[] arguments) throws Exception {
//        while (true) {
//            ClassLoader loader = new DynamicClassLoader("com\\test");
//            Class<?> clazz = Class.forName("MyClass", true, loader);
//            Object object = clazz.getConstructors()[0].newInstance();
//            System.out.println(object);
//            Thread.sleep(2000);
//        }
        DynamicObject object = new DynamicObject();
        object.addMethod("sum", (Integer... args) -> args[0] + args[1]);
        object.addMethod("printShoRomaPidor", args -> {
            System.out.println("Roma pidor");
            return null;
        });

        System.out.println(object.invokeMethod("sum", 5, 8));
    }
}