package com.course.selection.special;

import java.util.*;

public class SUtil {
    public static Map<Integer, Attribute> attributes = new HashMap<>();
    public static Map<Integer, Service> services = new HashMap<>();

    static {
        attributes.put(1, new Attribute(1, "线上：皮纹测试（指纹拍照）", 580, 100));
        attributes.put(2, new Attribute(2, "线下：皮纹测试（指纹+脑波仪器测量）", 1980, 800));
        attributes.put(3, new Attribute(3, "线下：基因测试（唾液）", 7980, 4000));
        services.put(1, new Service(1, "标准", 0));
        services.put(2, new Service(2, "专家", 800));
        services.put(3, new Service(3, "资深专家", 1500));
    }

    public static List<Attribute> toListA() {
        Iterator<Map.Entry<Integer, Attribute>> iterator = attributes.entrySet().iterator();
        List<Attribute> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Attribute value = iterator.next().getValue();
            list.add(value);
        }
        return list;
    }

    public static List<Service> toListS() {
        Iterator<Map.Entry<Integer, Service>> iterator = services.entrySet().iterator();
        List<Service> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Service value = iterator.next().getValue();
            list.add(value);
        }
        return list;
    }
}
