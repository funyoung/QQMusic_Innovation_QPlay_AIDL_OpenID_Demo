package com.tencent.qqmusic.api.common

import android.net.Uri
import com.tencent.qqmusic.third.api.contract.Keys
import org.json.JSONObject

class SchemeHelper {
    public companion object {
        const val BASE_SCHEME = "qqmusicapidemo://"
        const val SAMPLE_SCHEME = "${BASE_SCHEME}xxx"
        const val URI_LOGIN = "${BASE_SCHEME}login"

        private const val QM_LOGIN_KEY = "qmlogin"

        @JvmStatic
        fun buildSourceBytes(signString: String, nonce: String): ByteArray {
            val signJson = JSONObject()
            signJson.put(Keys.API_RETURN_KEY_NONCE, nonce)
            signJson.put(Keys.API_RETURN_KEY_SIGN, signString)
            signJson.put(Keys.API_RETURN_KEY_CALLBACK_URL, BASE_SCHEME)
            val sourceString = signJson.toString()
            return sourceString.toByteArray()
        }

        // 跳转qq音乐登录，登录成功后返回：qqmusicapidemo://login?qmlogin=1
        // qmlogin=0表示失败
        fun isLoginSuccessfully(uri: Uri): Boolean {
            val loginResult = uri.getQueryParameter(QM_LOGIN_KEY)
            return "1" == loginResult
        }
    }
}