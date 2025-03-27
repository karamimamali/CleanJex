package org.karam.cleanjex;

import java.lang.instrument.Instrumentation;

public class CleanJexAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        CleanErrorHandler.enable();
    }
}
