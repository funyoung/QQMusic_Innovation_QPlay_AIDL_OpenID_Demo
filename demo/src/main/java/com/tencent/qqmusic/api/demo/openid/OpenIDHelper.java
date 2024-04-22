package com.tencent.qqmusic.api.demo.openid;

import android.util.Base64;

import com.tencent.qqmusic.api.common.SchemeHelper;
import com.tencent.qqmusic.api.demo.Config;

public class OpenIDHelper {

    private static final String QQMusicPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrp4sMcJjY9hb2J3sHWlwIEBrJlw2Cimv+rZAQmR8V3EI+0PUK14pL8OcG7CY79li30IHwYGWwUapADKA01nKgNeq7+rSciMYZv6ByVq+ocxKY8az78HwIppwxKWpQ+ziqYavvfE5+iHIzAc8RvGj9lL6xx1zhoPkdaA0agAyuMQIDAQAB";


    public static String getEncryptString() {
        final String nonce = String.valueOf(System.currentTimeMillis());
        return getEncryptNonce(nonce);
    }

    public static String getEncryptNonce(String nonce) {
        if (nonce == null || nonce.isEmpty()) {
            return null;
        }

        try {
            //1.使用App私钥签名
            String signString = RSAUtils.sign(nonce.getBytes(), Config.OPENID_APP_PRIVATE_KEY);

            final byte[] sourceBytes = SchemeHelper.buildSourceBytes(signString, nonce);

            //2. 使用Q音公钥加密(随机数+签名)
            byte[] encryptData = RSAUtils.encryptByPublicKey(sourceBytes, QQMusicPublicKey);
            if (encryptData == null) {
                return null;
            }
            return Base64.encodeToString(encryptData, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptQQMEncryptString(String qmEncryptString) {
        try {
            byte[] qmEncryptData = Base64.decode(qmEncryptString, Base64.DEFAULT);
            //7.使用App私钥解密
            byte[] decryptData = RSAUtils.decryptByPrivateKey(qmEncryptData, Config.OPENID_APP_PRIVATE_KEY);
            if (decryptData != null) {
                return new String(decryptData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检查QQ音乐签名
     *
     * @param sign  签名
     * @param nonce 种子
     */
    public static boolean checkQMSign(String sign, String nonce) {
        if (sign == null || nonce == null)
            return false;
        try {
            return RSAUtils.verify(nonce.getBytes(), QQMusicPublicKey, sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
