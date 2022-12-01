package ru.job4j.io.duplicates;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Trial {
    public static void main(String[] args) {
        Map<Integer, String> employeeMap = new HashMap<>();
        employeeMap.put(35, "Mark");
        employeeMap.put(40, "John");
        employeeMap.put(23, "Michael");
        employeeMap.put(31, "Jim");
        employeeMap.put(25, "Kevin");


        Map<Integer, String> filteredMap = employeeMap.entrySet()
                .stream().filter(x -> x.getKey() > 30)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println("Filtered map: " + filteredMap);
    }
}
