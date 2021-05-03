package com.java.vm.cmd.classpath;

import java.io.IOException;
import java.util.ArrayList;

public class CompositeEntry extends Entry {
    //不用担心,list中的entry是按照父类来转入的,在真正执行的时候,是按照各自的实际类型执行readClass()方法
    ArrayList<Entry> compositeEntries;
    private String pathList;

    public CompositeEntry() {
    }

    public CompositeEntry(String pathList, String pathListSeparator) {
        this.pathList = pathList;
        String[] paths = pathList.split(pathListSeparator);
        compositeEntries = new ArrayList<>(paths.length);
        for (int i = 0; i < paths.length; i++) {
            compositeEntries.add(new DirEntry(paths[i]));

        }
    }

    @Override
    byte[] readClass(String className) {
        byte[] data;
        for (int i = 0; i < compositeEntries.size(); i++) {
            try {
                data = compositeEntries.get(i).readClass(className);
                if (data != null) {
                    return data;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    String printClassName() {
        return pathList;
    }
}