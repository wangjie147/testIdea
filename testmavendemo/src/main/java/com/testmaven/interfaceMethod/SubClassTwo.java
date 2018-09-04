package com.testmaven.interfaceMethod;

public class SubClassTwo implements MyFun,MyInterface{

    @Override
    public String getName() {
        return MyFun.super.getName();
    }
}
