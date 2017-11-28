package com.lester.core.service.impl;

import com.lester.core.service.IAopManService;
import org.springframework.stereotype.Service;

@Service
public class AopMainService implements IAopManService {

    private final static String name = "lesterhsu";
    private final static String url = "song";

    public void printName() {
        System.out.println("Customer name : " + name);
//        return this.name;
    }

    public void printURL() {
        System.out.println("Customer website : " + url);
    }

    public void printThrowException() {
        throw new IllegalArgumentException();
    }
}
