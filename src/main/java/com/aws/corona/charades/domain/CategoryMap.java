package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CategoryMap {
    private Map<String, String> categoryFilePathMap;

    public CategoryMap(Map<String, String> categoryFilePathMap) {
        categoryFilePathMap.put("General", "/general.txt");
        categoryFilePathMap.put("Sports", "/sports.txt");
        categoryFilePathMap.put("Food", "/food.txt");
        categoryFilePathMap.put("Animals", "/animals.txt");
        categoryFilePathMap.put("Jobs", "/jobs.txt");
        this.categoryFilePathMap = categoryFilePathMap;
    }

    public Map<String, String> getCategoryFilePathMap() {
        return categoryFilePathMap;
    }
}
