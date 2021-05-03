package com.java.vm.cmd;

public class Cmd {
    boolean isRightFmt = true;
    boolean helpFlag;
    boolean versionFlag;
    String cpOption = "";
    String clazz;
    String[] args;

    public Cmd(String strs) {

    }

    public void parseCmd(String[] args) {
        int index = 1;
        if (args.length < 2) {
            isRightFmt = false;
            return;
        }
        if ("java".equals(args[0])) {
            isRightFmt = false;
        } else {
            if (args[1].equals("-help") || args[1].equals("-?")) {
                helpFlag = true;
            } else if (args[1].equals("-version")) {
                versionFlag = true;
            } else if (args[1].equals("-cp") || args[1].equals("classpath")) {
                if (args.length < 4) {
                    isRightFmt = false;
                }
                index = 4;
                this.cpOption = args[2];
            }
        }
    }

}
