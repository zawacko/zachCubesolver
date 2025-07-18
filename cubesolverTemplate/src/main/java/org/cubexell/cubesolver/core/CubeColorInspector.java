package org.cubexell.cubesolver.core;

public interface CubeColorInspector {
    void startup();
    char[][][] inspect();
    void shutdown();
}
