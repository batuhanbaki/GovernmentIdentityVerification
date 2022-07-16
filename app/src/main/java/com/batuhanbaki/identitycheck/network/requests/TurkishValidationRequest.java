package com.batuhanbaki.identitycheck.network.requests;


import android.nfc.FormatException;

import com.batuhanbaki.identitycheck.BuildConfig;
import com.batuhanbaki.identitycheck.models.ResponseModel;
import com.batuhanbaki.identitycheck.models.UserModel;
import com.batuhanbaki.identitycheck.network.base.BaseSOAP;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import java.util.Objects;


public class TurkishValidationRequest extends BaseSOAP {

    public TurkishValidationRequest() {
        super();
        request = new SoapObject(BuildConfig.NAME_SPACE, BuildConfig.METHOD_NAME);
        aht = new AndroidHttpTransport(BuildConfig.URL);
    }

    @Override
    public boolean checkRequest() {
        return request != null &&
                request.getProperty("TCKimlikNo") != null &&
                request.getProperty("Ad") != null &&
                request.getProperty("Soyad") != null &&
                request.getProperty("DogumYili") != null;
    }

    @Override
    public boolean checkModel(UserModel model) {
        return model != null && model.getIdentityNumber().length() == 11 && model.getBirthYear().length() == 4;
    }

    @Override
    public void setRequestProp(UserModel model) throws FormatException {
        if (!checkModel(model)) {
            throw new FormatException("User model not correct format!");
        }
        request.addProperty("TCKimlikNo", model.getIdentityNumber());
        request.addProperty("Ad", model.getName());
        request.addProperty("Soyad", model.getSurname());
        request.addProperty("DogumYili", model.getBirthYear());
    }

    @Override
    public ResponseModel call() {
        if (!checkRequest())
            throw new NullPointerException("Request model not correct format, fill the setRequestProp Method!");

        soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.dotNet = true;
        soapEnvelope.setOutputSoapObject(request);
        try {
            aht.call(BuildConfig.SOAP_ACTION, soapEnvelope);
            SoapPrimitive resultString = (SoapPrimitive) soapEnvelope.getResponse();
            boolean response = Boolean.parseBoolean(resultString.toString());
            String msg = response ? "Doğrulama başarılı, kullanıcı verileri doğru" : "Doğrulama hatalı, kullanıcı verileri yanlış";
            return new ResponseModel(response, msg, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseModel(false, Objects.requireNonNull(e.getLocalizedMessage()), e);
        }
    }
}
