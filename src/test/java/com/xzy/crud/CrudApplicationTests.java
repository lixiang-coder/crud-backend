package com.xzy.crud;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@SpringBootTest
class CrudApplicationTests {

    /**
     * 加密方法
     *      缺点：加密后的密文长度并不是固定的，而是取决于明文数据的长度
     *      优点：加密后，可以实现对密文的解密
     */
    @Test
    void AesEncryptionExample () throws Exception {
        // 生成密钥
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // 可以选择128、192或256位密钥
        SecretKey secretKey = keyGenerator.generateKey();

        // 待加密的字符串
        String plainText = "这是需要加密的返回数据";

        // 加密
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);


        // 解密
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        String decryptedText = new String(decryptedBytes, "UTF-8");

        System.out.println("原文: " + plainText);
        System.out.println("密文: " + encryptedText);
        System.out.println("解密后: " + decryptedText);
    }

    private static final String KEY_STRING = "0123456789abcdef"; // 16字节的密钥
    private static final String PADDING_CHAR = "=";

    /**
     * 我操了，一早上都在研究如何把很长的文本加密成指定长度的字符串，然后这个加密后的密文还可以解密成原文，
     * 在网上看到条评论说：有这技术都靠卖专利都成首富了，我才恍然大悟。
     */
    @Test
    void FixedLengthAESEncryption() throws Exception{
        String plainText = "这是需要加密的返回数据";
        String encryptedText = encrypt(plainText);
        System.out.println("加密后的字符串: " + encryptedText);


        String decryptedText = decrypt(encryptedText);
        System.out.println("解密后的字符串: " + decryptedText);
    }

    // 填补
    public static String pad(String plainText) {
        int paddingLength = 16 - (plainText.length() % 16);
        return plainText + PADDING_CHAR.repeat(paddingLength);
    }

    public static String unpad(String paddedText) {
        return paddedText.replace(PADDING_CHAR, "");
    }

    // 加密
    public static String encrypt(String plainText) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY_STRING.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(pad(plainText).getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // 解密
    public static String decrypt(String encryptedText) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY_STRING.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return unpad(new String(decryptedBytes));
    }




}
