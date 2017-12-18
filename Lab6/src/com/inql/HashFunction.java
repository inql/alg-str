package com.inql;

@FunctionalInterface
public interface HashFunction {

    int hash(long k, int m, int i);

}
