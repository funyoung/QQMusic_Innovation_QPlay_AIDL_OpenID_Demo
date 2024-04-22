//package com.tencent.qqmusic.api.demo.openid
//
//import android.util.Base64
//import com.tencent.qqmusic.api.common.SchemeHelper.Companion.buildSourceBytes
//import com.tencent.qqmusic.api.demo.Config
//
//object OpenIDHelper {
//    const val QQMusicPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrp4sMcJjY9hb2J3sHWlwIEBrJlw2Cimv+rZAQmR8V3EI+0PUK14pL8OcG7CY79li30IHwYGWwUapADKA01nKgNeq7+rSciMYZv6ByVq+ocxKY8az78HwIppwxKWpQ+ziqYavvfE5+iHIzAc8RvGj9lL6xx1zhoPkdaA0agAyuMQIDAQAB"
//    fun getEncryptString(): String? {
//        val time = System.currentTimeMillis()
//        val nonce = time.toString()
//        return if (nonce == null || nonce.isEmpty()) {
//            null
//        } else try {
//            //1.使用App私钥签名
//            val signString = RSAUtils.sign(nonce.toByteArray(), Config.OPENID_APP_PRIVATE_KEY)
//            val sourceBytes = buildSourceBytes(signString, nonce)
//
//            //2. 使用Q音公钥加密(随机数+签名)
//            val encryptData = RSAUtils.encryptByPublicKey(sourceBytes, QQMusicPublicKey)
//                ?: return null
//            Base64.encodeToString(encryptData, Base64.DEFAULT)
//        } catch (e: Exception) {
//            e.printStackTrace()
//            null
//        }
//    }
//
//    fun decryptQQMEncryptString(qmEncryptString: String?): String? {
//        try {
//            val qmEncryptData = Base64.decode(qmEncryptString, Base64.DEFAULT)
//            //7.使用App私钥解密
//            val decryptData =
//                RSAUtils.decryptByPrivateKey(qmEncryptData, Config.OPENID_APP_PRIVATE_KEY)
//            if (decryptData != null) {
//                return String(decryptData)
//            }
//        } catch (e: java.lang.Exception) {
//            e.printStackTrace()
//        }
//        return null
//    }
//
//    /**
//     * 检查QQ音乐签名
//     *
//     * @param sign  签名
//     * @param nonce 种子
//     */
//    fun checkQMSign(sign: String?, nonce: String?): Boolean {
//        if (sign == null || nonce == null) return false
//        try {
//            return RSAUtils.verify(nonce.toByteArray(), QQMusicPublicKey, sign)
//        } catch (e: java.lang.Exception) {
//            e.printStackTrace()
//        }
//        return false
//    }
//
//}