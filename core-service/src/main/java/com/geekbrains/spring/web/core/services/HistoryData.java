package com.geekbrains.spring.web.core.services;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HistoryData implements PropertyChangeListener {

    private String name;

    public HistoryData(String name) {
        this.name = name;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        this.setNews((String) evt.getNewValue());
    }

    public void setNews(String news) {
        System.out.println(name + " узнал новость: " + news);
    }
}
