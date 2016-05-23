package com.yz.bourse.common.util;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by ht on 11/9/15.
 * AES 加解密
 */
@Slf4j
public final class AES {
    private AES() {
    }

    /**
     * AES/CBC/PKCS5Padding 加密
     *
     * @param input
     * @param key   16位
     * @param iv    16位
     * @return
     */
    public static byte[] encrypt(final byte[] input, final byte[] key, final byte[] iv) {
        final SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

        final IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        final Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
            return cipher.doFinal(input);
        } catch (final Exception e) {
            throw Throwables.propagate(e);
        }
    }

    /**
     * AES/CBC/PKCS5Padding 解密
     *
     * @param input
     * @param key   16位
     * @param iv    16位
     * @return
     */
    public static byte[] decrypt(final byte[] input, final byte[] key, final byte[] iv) {
        final SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

        final IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        final Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
            return cipher.doFinal(input);
        } catch (final Exception e) {
            throw Throwables.propagate(e);
        }
    }

}
