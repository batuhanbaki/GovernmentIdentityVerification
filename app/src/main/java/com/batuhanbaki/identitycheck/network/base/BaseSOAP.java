package com.batuhanbaki.identitycheck.network.base;

import android.nfc.FormatException;
import android.os.StrictMode;

import com.batuhanbaki.identitycheck.models.IdentityEnum;
import com.batuhanbaki.identitycheck.models.UserModel;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

public abstract class BaseSOAP implements ISOAPActions{

    public SoapObject request;
    public SoapSerializationEnvelope soapEnvelope;
    public AndroidHttpTransport aht;
    public UserModel model;

    public BaseSOAP() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public abstract boolean checkRequest();

    public abstract boolean checkModel(UserModel model);

    public abstract void setRequestProp(UserModel model) throws Exception;
}
