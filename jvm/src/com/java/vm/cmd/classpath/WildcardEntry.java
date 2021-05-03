package com.java.vm.cmd.classpath;

import java.io.File;
import java.util.ArrayList;

public class WildcardEntry extends Entry {

    public CompositeEntry compositeEntry;

    public WildcardEntry(String jreLibPath) {

        String baseDir = jreLibPath.substring(0, jreLibPath.length() - 1);  //去掉最后的一个字符 *
        File dir = new File(baseDir);
        File[] files = dir.listFiles();
        compositeEntry = new CompositeEntry();
        compositeEntry.compositeEntries = new ArrayList<>();
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".jar")) {
                compositeEntry.compositeEntries.add(new ZipJarEntry(baseDir,file.getName()));
            }
        }
//        System.out.println(compositeEntry.compositeEntries.size());
    }

    @Override
    byte[] readClass(String className) {
        return compositeEntry.readClass(className);
    }

    @Override
    String printClassName() {
        return null;
    }
}