package com.distribution.worker.service;


public interface CryptoService {

    String encryptString(String text);

    String decryptString(String text);

    String encryptEntityId(Long value);

    Long decryptEntityId(String text);
}
