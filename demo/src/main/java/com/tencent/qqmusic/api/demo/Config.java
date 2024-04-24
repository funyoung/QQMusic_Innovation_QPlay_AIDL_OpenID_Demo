package com.tencent.qqmusic.api.demo;

import com.tencent.qqmusic.third.api.contract.CommonCmd;

/**
 * QQ音乐使用RSA密钥位数为1024位，密钥格式使用PKCS#8格式，因此OPENID_APP_PRIVATE_KEY应该使用
 * README说明文件中OpenSSL生成的rsa私钥后转换成pkcs#8格式命令生成的文件中保存的私钥串，并去掉首尾
 * 两行注释内容，以及空格换行符，仅单纯的密钥字符串，形如openid/目录下OpenIDHelper中的Q音公钥串
 */
public class Config {

    /**
     * 需向QQ音乐发邮件申请，具体参考README
     */
    public static final String OPENID_APPID = "";

    /**
     * 配置接入方自己生成的RSA私钥，由于是Demo，所以直接写在代码中了，真实环境中需要注意私钥泄漏的风险。
     */
    public static final String OPENID_APP_PRIVATE_KEY = "";

    /**
     * 配置平台类型，可选值如下:
     * {@link CommonCmd#AIDL_PLATFORM_TYPE_PHONE }
     * {@link CommonCmd#AIDL_PLATFORM_TYPE_TV}
     * {@link CommonCmd#AIDL_PLATFORM_TYPE_CAR}
     * {@link CommonCmd#AIDL_PLATFORM_TYPE_PAD}
     */
    public static String BIND_PLATFORM = CommonCmd.AIDL_PLATFORM_TYPE_PHONE;

}