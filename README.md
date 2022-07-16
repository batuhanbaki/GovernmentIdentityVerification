# Identity Check Library

This library uses https://tckimlik.nvi.gov.tr/Service/KPSPublic.asmx?op=TCKimlikNoDogrula source. NVI is goverment departmant and if you want use custom checker follow the link.

# How To Use

### Add Implementations

```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

```
implementation 'com.github.batuhanbaki:identitycheck:{RELEASE-VERSION}'
```

### Using

```
        Thread thread = new Thread(() ->
        {
            UserModel model = new UserModel.Builder()
                    .identityNumber(12345678910L) //Length: 11
                    .name("batuhan")
                    .surname("surname")
                    .birthYear(2000)
                    .build();
            try {
                ISOAPActions actions = IdentityChecker.getInstance(IdentityEnum.TURKISH, model)
                        .getIsoapActions();
                ResponseModel responseModel = actions.call();
                System.out.println("Model: " + responseModel.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();
```

### NOTE : Just for Turkish Identity Verification For Now!

