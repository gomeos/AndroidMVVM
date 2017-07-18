package com.gomeos.mvvm.utils;

import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by liuyuxuan on 16/4/29.
 */
public class ObjectUtils {

    public static <T extends Serializable> String objectToHexString(T object) {
        String hexString = null;
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            hexString = StringUtils.bytesToHexString(bytes);
        } catch (Exception e) {
        } finally {
            if (null != baos) {
                try {
                    baos.close();
                } catch (IOException e) {
                }
            }
            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                }
            }
        }
        return hexString;
    }

    public static <T extends Serializable> T hexStringToObject(String hexString) {
        T object = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {

            byte[] bytes = StringUtils.hexStringToBytes(hexString);
            if (bytes == null) {
                return null;
            }
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            object = (T) ois.readObject();

        } catch (Exception e) {
            return null;
        } finally {
            if (null != bais) {
                try {
                    bais.close();
                } catch (Exception e) {
                }
            }
            if (null != ois) {
                try {
                    bais.close();
                } catch (Exception e) {
                }
            }
        }

        return object;
    }

    public static boolean equals(Object o1, Object o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }

    public static <T> T cast(Object obj, Class<T> type) {
        if (!type.isInstance(obj)) {
            return null;
        }
        return type.cast(obj);
    }

    public static <T> T newInstance(String className) {

        try {
            Class<T> cls = (Class<T>) Class.forName(className);
            return newInstance(cls);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T newInstance(Class<T> cls) {
        return newInstance(cls, null, null);
    }

    public static <T> T newInstance(Class<T> cls, Object... object) {
        return newInstance(cls, null, object);
    }

    public static <T> T newInstance(Class<T> cls, Class<?>[] parameterTypes, Object[] parameters) {
        CheckUtils.checkNotNull(cls);
        if (parameters == null) {
            parameters = new Object[]{};
        }

        if (parameterTypes != null) {
            try {
                Constructor<T> constructor = cls.getDeclaredConstructor(parameterTypes);
                constructor.setAccessible(true);
                return constructor.newInstance(parameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
                try {
                    constructor.setAccessible(true);
                    return (T) constructor.newInstance(parameters);
                } catch (Exception e) {
                    // 此处不处理
                }
            }
        }

        return null;
    }

    /**
     * Get the Field object from an object's class or its all super classes.
     *
     * @param obj  the object to search
     * @param name field name
     * @return Field object
     * @throws NoSuchFieldException no field found with given name
     */
    public static Field getField(Object obj, String name) throws NoSuchFieldException {
        if (obj == null || TextUtils.isEmpty(name)) {
            return null;
        }

        Field field = null;
        NoSuchFieldException exception = null;

        Class<?> type = obj.getClass();
        while (type != null && !type.equals(Object.class)) {
            // loop up to Object, null means a primitive type
            try {
                field = type.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                // hold the first exception
                if (exception == null) {
                    exception = e;
                }
            }

            if (field != null) {
                // found the field, exit the loop
                break;
            } else {
                // if no such field in current class, then search super class
                type = type.getSuperclass();
            }
        }

        if (field == null && exception != null) {
            // didn't find any field
            throw exception;
        }

        return field;
    }

    /**
     * Get the value of a field
     *
     * @param obj  the object
     * @param name the field name
     * @return the value
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object getFieldValue(Object obj, String name) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(obj, name);
        if (field == null) {
            return null;
        }

        field.setAccessible(true);
        return field.get(obj);
    }

    public static Class<?> getGenericClass(Object obj, Class<?> interfaceType, int index) {
        Type superType = obj.getClass().getGenericSuperclass();
        if (superType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superType;
            Type actualType = parameterizedType.getActualTypeArguments()[index];
            if (actualType instanceof Class) {
                return (Class<?>) actualType;
            }
        }

        if (interfaceType != null && interfaceType.isInstance(obj)) {
            Type[] types = obj.getClass().getGenericInterfaces();
            for (Type type : types) {
                if (!(type instanceof ParameterizedType)) {
                    continue;
                }
                ParameterizedType paramType = ParameterizedType.class.cast(type);
                if (paramType.getRawType() != interfaceType) {
                    continue;
                }
                Type actualType = paramType.getActualTypeArguments()[index];
                if (actualType instanceof Class) {
                    return (Class<?>) actualType;
                }
            }
        }

        return null;
    }

    public interface MethodFilter {
        boolean accept(Method method);
    }

    private static class MethodWrapper {
        private final Method method;

        MethodWrapper(Method method) {
            this.method = method;
        }

        public boolean equals(Method m) {
            return method.getName().equals(m.getName())
                    && Arrays.asList(method.getParameterTypes()).equals(Arrays.asList(m.getParameterTypes()));
        }

        public int hashCode() {
            return method.getName().hashCode() ^ Arrays.asList(method.getParameterTypes()).hashCode();
        }

        public Method getMethod() {
            return method;
        }
    }

    public static List<Method> findMethods(Class<?> clazz, MethodFilter filter) {
        Class<?> current = clazz;
        Set<MethodWrapper> wrappers = new HashSet<>();
        List<Method> methods = new LinkedList<>();
        while (current != null && current != Object.class) {
            for (Method method : current.getDeclaredMethods()) {
                if (filter.accept(method)) {
                    wrappers.add(new MethodWrapper(method));
                }
            }
            current = current.getSuperclass();
        }

        for (MethodWrapper wrapper : wrappers) {
            methods.add(wrapper.getMethod());
        }

        return methods;
    }
}
