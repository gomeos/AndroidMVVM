package com.gomeos.mvvm.utils;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by chenbaocheng on 16/5/19.
 */
public class CollectionUtils {

    @SafeVarargs
    public static final <T> ArrayList<T> listWithValues(T... vals) {
        ArrayList<T> res = new ArrayList<T>();
        for (T val : vals)
            res.add(val);
        return res;
    }

    public static final <K, V> List<Pair<K, V>> mapToPairList(Map<K, V> map) {
        //TODO: 此处用HashMap太过严格，其实其他Map也可以使用此逻辑
        List<Pair<K, V>> list = new ArrayList<Pair<K, V>>();
        Iterator<K> it = map.keySet().iterator();
        while (it.hasNext()) {
            K key = it.next();
            V value = map.get(key);
            Pair<K, V> pair = new Pair<K, V>(key, value);
            list.add(pair);
        }
        return list;
    }



    public static final <T> List<T> listDuplicate(List<T> list) {
        List<T> res = new ArrayList<T>(list.size());
        res.addAll(list);
        return res;
    }

    public static final <T> void listAssertSize(List<T> list, int assertSize, Class<T> cls) throws RuntimeException {
        int listSize = list.size();
        if (listSize > assertSize) {
            for (int i = assertSize; i < listSize; i++)
                list.remove(list.size() - 1);
        } else if (listSize < assertSize) {
            for (int i = listSize; i < assertSize; i++) {
                try {
                    T t = cls.newInstance();
                    list.add(t);
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
    }

    /**
     * is null or its size is 0
     * <p/>
     * <pre>
     * isEmpty(null)   =   true;
     * isEmpty({})     =   true;
     * isEmpty({1})    =   false;
     * </pre>
     *
     * @param <V>
     * @param sourceList
     * @return if list is null or its size is 0, return true, else return false.
     */
    public static <V> boolean isEmpty(Collection<V> sourceList) {
        return (sourceList == null || sourceList.size() == 0);
    }
}
