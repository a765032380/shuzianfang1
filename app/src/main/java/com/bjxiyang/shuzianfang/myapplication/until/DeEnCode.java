package com.bjxiyang.shuzianfang.myapplication.until;


/**
 * Created by gll on 2017/12/8.
 */

public class DeEnCode {
    //密钥 加解密的密钥必须相同 可自定义
    private static final String secretKey ="KJIRCR#(KJP92)ORDCR";
    /**
     * 字符串加密
     * @param plainText 要加密的字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String plainText){
        String encryption = "";
        try {
            plainText =  new String(plainText.getBytes("UTF-8"),"iso-8859-1");
        } catch (Exception e) {
        }
        char[] cipher=new char[plainText.length()];
        for(int i=0,j=0;i<plainText.length();i++,j++){
            if(j==secretKey.length())
                j=0;
            cipher[i]=(char) (plainText.charAt(i)^secretKey.charAt(j));
            String strCipher= Integer.toHexString(cipher[i]);
            if(strCipher.length() == 1){
                encryption+="0"+strCipher;
            }else{
                encryption+=strCipher;
            }
        }
        return encryption;
    }

    /**
     * 解密字符串
     * @param encryption 要解密的字符串
     * @return 解密后的字符串
     */
    public static String decrypt(String encryption) {
        char[] decryption=new char[encryption.length()/2];
        for(int i=0,j=0;i<encryption.length()/2;i++,j++){
            if(j==secretKey.length())
                j=0;
            char n=(char)(int)Integer.valueOf(encryption.substring(i*2,i*2+2),16);
            decryption[i]=(char)(n^secretKey.charAt(j));
        }
        String decoding="";
        try {
            decoding = new String(String.valueOf(decryption).getBytes("iso-8859-1"),"UTF-8");
        } catch (Exception e) {
        }
        return decoding;
    }
    public static void main(String[] args) {
        String s="{\"code\":1000,\"msg\":\"获取广告信息成功 \",\"obj\":{\"bannerObj\":[{\"adInfo\":[],\"adveraddressId\":1}]}}";
//        String s="18813045215";
        String enc = encrypt(s);
        String dec = decrypt(enc);
        System.out.println(enc);
        System.out.println(dec);




    }
}
