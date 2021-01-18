package com.distribution.worker.service.impl;

import com.distribution.worker.config.ConfigProperties;
import com.distribution.worker.exception.EntityIdCryptoException;
import com.distribution.worker.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Random;


@Service
public class CryptoServiceImpl implements CryptoService {

    @Autowired
    private ConfigProperties configs;

    private TextEncryptor textEncryptor;

    private TextEncryptor entityIdEncryptor;

    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 128;

    @PostConstruct
    public void init() {
        Security.setProperty("crypto.policy", "unlimited");
        entityIdEncryptor = Encryptors.queryableText(configs.getCrypto().getPassword(), configs.getCrypto().getSalt());
        textEncryptor = Encryptors.text(configs.getCrypto().getPassword(), configs.getCrypto().getSalt());

    }

    @Override
    public String encryptEntityId(Long number) {
        try {

            return entityIdEncryptor.encrypt(number.toString());
        } catch (Exception e) {
            throw new EntityIdCryptoException("encryption failed! value: " + number, e);
        }

    }

    @Override
    public Long decryptEntityId(String text) {
        try {
            return Long.valueOf(entityIdEncryptor.decrypt(text));
        } catch (Exception e) {
            throw new EntityIdCryptoException("decryption failed! value: " + text, e);
        }
    }

    @Override
    public String encryptString(String text) {
        return textEncryptor.encrypt(text);
    }

    @Override
    public String decryptString(String text) {
        return textEncryptor.decrypt(text);
    }


}
