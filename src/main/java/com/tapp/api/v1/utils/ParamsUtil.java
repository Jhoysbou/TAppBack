package com.tapp.api.v1.utils;

import com.tapp.api.v1.exceptions.SignCheckException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ParamsUtil {
    private static final String ENCODING = "UTF-8";

    public static boolean isAuthentic(String url) throws SignCheckException, MalformedURLException {
        url = "https://example.com/" + url;
        Map<String, String> queryParams = getQueryParams(new URL(url));

        String checkString = queryParams.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("vk_"))
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> encode(entry.getKey()) + "=" + encode(entry.getValue()))
                .collect(Collectors.joining("&"));

        String sign = null;
        try {
            sign = getHashCode(checkString, Credentials.VK_SECRETE_KEY);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new SignCheckException();
        }
        return sign.equals(queryParams.getOrDefault("sign", ""));
    }

    public static long getUserId(String url) throws MalformedURLException, SignCheckException {
        url = "https://example.com/" + url;
        Map<String, String> queryParams = getQueryParams(new URL(url));
        return Long.parseLong(queryParams.get("vk_user_id"));
    }


    private static Map<String, String> getQueryParams(URL url) throws SignCheckException {
        final Map<String, String> result = new LinkedHashMap<>();
        String[] pairs;
        try {
            pairs = url.getQuery().split("&");
        } catch (NullPointerException e) {
            throw new SignCheckException();
        }

        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            String key = idx > 0 ? decode(pair.substring(0, idx)) : pair;
            String value = idx > 0 && pair.length() > idx + 1 ? decode(pair.substring(idx + 1)) : null;
            result.put(key, value);
        }

        return result;
    }

    private static String getHashCode(String data, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKey = null;
        secretKey = new SecretKeySpec(key.getBytes(ENCODING), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        byte[] hmacData = mac.doFinal(data.getBytes(ENCODING));
        return new String(Base64.getUrlEncoder().withoutPadding().encode(hmacData));
    }

    private static String decode(String value) {
        try {
            return URLDecoder.decode(value, ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return value;
    }

    private static String encode(String value) {
        try {
            if (value == null) {
                value = "";
            }
            return URLEncoder.encode(value, ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return value;
    }
}
