package striker.studing.classloaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DynamicClassLoader extends ClassLoader {
    private final Map<String, Class<?>> cache = new HashMap<>();
    private final String[] path;

    public DynamicClassLoader(String... path) {
        this.path = path;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }

    @Override
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = findClass(name);
        if(resolve)
            resolveClass(clazz);
        return clazz;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> clazz = cache.get(name);
        if(clazz != null){
            return clazz;
        }
        File file = findFile(name, ".class");
        if(file == null){
            return findSystemClass(name);
        }
        byte[] bytes = new byte[(int) file.length()];
        try (FileInputStream inputStream = new FileInputStream(file)){
            inputStream.read(bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clazz = defineClass(name, bytes, 0, bytes.length);
        cache.put(name, clazz);
        return clazz;
    }

    private File findFile(String name, String separator) {
        File file;
        for (String dir : path) {
            file = new File( dir +
                    (dir.equals("") ? "" : File.separatorChar) +
                    name.replace('.', File.separatorChar) + separator);
            if(file.exists())
                return file;
        }
        return null;
    }

    @Override
    protected URL findResource(String name) {
        File file = findFile(name, "");
        try {
            if(file != null)
                return file.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
