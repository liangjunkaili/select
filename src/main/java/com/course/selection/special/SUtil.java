package com.course.selection.special;

import java.util.*;

public class SUtil {
    public static Map<Integer, List<Attribute>> attributes = new HashMap<>();
    public static Map<Integer, Service> services = new HashMap<>();

    static {
        List<Attribute> attributes1 = new ArrayList<>();
        List<Attribute> attributes2 = new ArrayList<>();
        attributes1.add(new Attribute(1, "线上：皮纹测试（指纹拍照）", 580, 100));
        attributes1.add(new Attribute(2, "线下：皮纹测试（指纹+脑波仪器测量）", 1980, 800));
        attributes1.add(new Attribute(3, "线下：基因测试（唾液）", 7980, 4000));
        attributes2.add(new Attribute(1, "小学版", 380, 0));
        attributes2.add(new Attribute(2, "中学版", 380, 0));
        attributes.put(1,attributes1);
        attributes.put(2,attributes2);

        services.put(1, new Service(1, "标准", 0));
        services.put(2, new Service(2, "专家", 800));
        services.put(3, new Service(3, "资深专家", 1500));
    }

//    public static List<Attribute> toListA() {
//        Iterator<Map.Entry<Integer, Attribute>> iterator = attributes.entrySet().iterator();
//        List<Attribute> list = new ArrayList<>();
//        while (iterator.hasNext()) {
//            Attribute value = iterator.next().getValue();
//            list.add(value);
//        }
//        return list;
//    }

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
