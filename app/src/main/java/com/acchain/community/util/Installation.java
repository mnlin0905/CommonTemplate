package com.acchain.community.util;

import android.content.Context;

import com.blankj.utilcode.util.DeviceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

/**
 * Created on 2018/2/2
 * function : 生成唯一的ID号
 *
 * @author ACChain
 */

public class Installation {
    private static String sID = null;
    private static final String INSTALLATION = "INSTALLATION";
    public synchronized static String id(Context context) {
        //首先获取设备AndroidId,如果发现生成了固定的串号,就自动来生成一个唯一id
        String androidID = DeviceUtils.getAndroidID();
        if(!androidID.equalsIgnoreCase("9774d56d682e549c")){
            return androidID;
        }

        if (sID == null) {
            File installation = new File(context.getFilesDir(), INSTALLATION);
            try {
                if (!installation.exists())
                    writeInstallationFile(installation);
                sID = readInstallationFile(installation);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sID;
    }

    private static String readInstallationFile(File installation) throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }

    private static void writeInstallationFile(File installation) throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String id = UUID.randomUUID().toString();
        out.write(id.getBytes());
        out.close();
    }

}
