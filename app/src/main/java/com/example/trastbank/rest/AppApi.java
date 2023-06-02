package com.example.trastbank.rest;

import com.example.trastbank.domain.SMS;

public interface AppApi {

    void insertSMS(SMS sms, long userId);
}
