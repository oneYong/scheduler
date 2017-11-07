package com.scheduler.vo;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by WYKIM on 2017-09-26.
 */
public class ClientSignatureGenerator {
    private final String HMAC_ALGORISM = "Hmacsha1";  //서명 알고리즘

    /**
     * 서명값 생성
     *
     * @param data 서명을위한 데이터문자열
     * @param key 서명하기위한 키
     * @return String 서명값
     * @throws InvalidKeyException, NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException , IllegalStateException  UTF-8인코딩 실패
     */
    public String generateSignature(String data, String key) throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, UnsupportedEncodingException{
        String signature = "";

        byte[] bKey = Base64.decodeBase64(key);

        //문자열 key를 base64 decoding, SecretKey 오브젝트화
        SecretKey sk = new SecretKeySpec(bKey, HMAC_ALGORISM);
        Mac mac = Mac.getInstance(HMAC_ALGORISM);

        mac.init(sk);

        byte[] bSignature = null;
        bSignature = mac.doFinal(data.getBytes("UTF-8"));

        signature = Base64.encodeBase64String(bSignature);

        //시그너처 마지막에 뉴라인이 들어있으면 Http Request Header에 추가하면, 서버가 해석하지 못함 (400, Bad Request)
        if (signature != null)
            signature = signature.trim();

        return signature;
    }
}
