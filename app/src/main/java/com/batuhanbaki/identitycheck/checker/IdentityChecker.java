package com.batuhanbaki.identitycheck.checker;

import com.batuhanbaki.identitycheck.models.IdentityEnum;
import com.batuhanbaki.identitycheck.models.ResponseModel;
import com.batuhanbaki.identitycheck.models.UserModel;
import com.batuhanbaki.identitycheck.network.base.BaseSOAP;
import com.batuhanbaki.identitycheck.network.base.ISOAPActions;
import com.batuhanbaki.identitycheck.network.requests.TurkishValidationRequest;

import java.util.concurrent.CountDownLatch;

public class IdentityChecker extends Thread {

    private static IdentityChecker INSTANCE;
    private BaseSOAP baseSOAP;
    CountDownLatch latch;
    ResponseModel model;


    private IdentityChecker(IdentityEnum identityEnum, UserModel model) throws Exception {
        if (identityEnum == IdentityEnum.TURKISH)
            baseSOAP = new TurkishValidationRequest();

        baseSOAP.setRequestProp(model);
        latch = new CountDownLatch(1);
    }

    public static IdentityChecker getInstance(IdentityEnum identityEnum, UserModel model) throws Exception {
        if (INSTANCE == null) {
            synchronized (IdentityChecker.class) {
                if (INSTANCE == null) {
                    INSTANCE = new IdentityChecker(identityEnum, model);
                }
            }
        }


        return INSTANCE;
    }


    public ISOAPActions getIsoapActions() {
        return baseSOAP;
    }

    public ResponseModel call() throws InterruptedException {
        start();
        latch.await();
        return model;
    }

    @Override
    public void run() {
        super.run();
        this.model = baseSOAP.call();
        latch.countDown();
    }
}
