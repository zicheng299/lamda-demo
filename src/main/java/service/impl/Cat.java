package service.impl;

import service.Printable;

public class Cat implements Printable {
    @Override
    public void print() {
        System.out.println("Meow");
    }
}
