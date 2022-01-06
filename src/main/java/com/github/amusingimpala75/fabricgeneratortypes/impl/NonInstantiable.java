package com.github.amusingimpala75.fabricgeneratortypes.impl;

public class NonInstantiable {
    protected NonInstantiable() {
        throw new IllegalStateException("Should not construct this class!");
    }
}
