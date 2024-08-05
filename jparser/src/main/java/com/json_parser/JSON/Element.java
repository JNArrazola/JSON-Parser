package com.json_parser.JSON;

import java.util.ArrayList;

/**
 * Element class represents an element in the JSON string
  */
public class Element {
    private String name;
    private String value;
    private ArrayList<Element> children;

    public Element(String name, String value) {
        this.name = name;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public Element(String name) {
        this.name = name;
        this.value = null;
        this.children = new ArrayList<>();
    }

    public void addChild(Element element) {
        children.add(element);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public ArrayList<Element> getChildren() {
        return children;
    }
}
