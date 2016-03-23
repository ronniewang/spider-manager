package com.spider.utils;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author wsy
 */
public class IoUtils {

    /**
     * @param serializable
     * @return
     * @throws IOException 写入失败抛出
     */
    public static byte[] objectToBtyeArray(Serializable serializable) throws IOException {

        byte[] bytes = null;
        ObjectOutputStream oo = null;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bo);
            oo.writeObject(serializable);
            bytes = bo.toByteArray();
        } finally {
            IOUtils.closeQuietly(oo);
        }
        return bytes;
    }
}
