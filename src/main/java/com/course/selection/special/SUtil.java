package com.course.selection.special;

import java.util.HashMap;
import java.util.Map;

public class SUtil {
    public static Map<Integer, Attribute> attributes = new HashMap<>();
    public static Map<Integer, Service> services = new HashMap<>();

    static {
        attributes.put(1, new Attribute(1, "test1", 500, 100));
        attributes.put(2, new Attribute(2, "test2", 500, 100));
        services.put(1, new Service(1, "tests", 400));
        services.put(2, new Service(2, "testt", 200));
    }
}
