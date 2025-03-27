package org.karam.cleanjex;

public class CleanFormatter {
    public static String format(Thread t, Throwable e) {
        final String RED = "\u001B[31m";
        final String BLUE = "\u001B[34m";
        final String CYAN = "\u001B[36m";
        final String RESET = "\u001B[0m";
        final String BRIGHT_GREEN = "\u001B[92m";
        final String BRIGHT_BLUE = "\u001B[94m";
        final String BRIGHT_CYAN = "\u001B[96m";


        String fullExceptionName = e.getClass().getName();
        String simpleExceptionName = e.getClass().getSimpleName();
        String exceptionPackage = fullExceptionName.substring(0, fullExceptionName.lastIndexOf('.'));

        String displayedException = fullExceptionName.startsWith("java.") ? simpleExceptionName : exceptionPackage + "." + simpleExceptionName;

        String threadInfo = "";
        if (Thread.activeCount() > 1) {
            threadInfo = "Exception[%s]: ".formatted(t.getName());
        } else {
            threadInfo = "Exception: ";
        }

        String formattedException = BLUE + threadInfo + RED + displayedException + RESET;

        String formattedMessage = "\""+ CYAN + e.getMessage() + RESET + "\"";

        StringBuilder sb = new StringBuilder();
        sb.append(formattedException).append(" ").append(formattedMessage).append("\n");

        for (StackTraceElement trace : e.getStackTrace()) {
            String file = trace.getFileName() != null ? trace.getFileName() : "Unknown Source";
            String line = trace.getLineNumber() > 0 ? String.valueOf(trace.getLineNumber()) : "?";
            String method = trace.getMethodName();

            sb.append("\t")
                    .append(BRIGHT_CYAN).append(file).append(RESET)
                    .append(" ").append(BRIGHT_GREEN).append(line).append(RESET)
                    .append("  ").append(BRIGHT_BLUE).append(method).append("()").append(RESET)
                    .append("\n");
        }

        return sb.toString();
    }
}
