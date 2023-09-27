# Build pjsip

Move to your local pjsip folder and followed below instructions. Then the configuration disable speex aec and code, enable webrtc aec3. After that perform make to build pjsip library.

```
./configure-android --disable-speex-aec --disable-speex-codec --enable-libwebrtc-aec3
make dep && make clean
make
```

To support pjsip in Android, move path to pjsip-apps/src/swig then perform make to build Java code and share libraty libpjsua2.so

# pjsip-app

The Android Java source code built from pjsip-2.13 source code which followed [Build for other architectures](https://docs.pjsip.org/en/latest/get-started/android/build_instructions.html#building-for-other-architectures)
Include: 

1. app 
    - Use package org.pjsip.pjsua2.app
2. app-kotlin
3. pjsua2
    - libpjsua2

# PJSIP
- [LIBRARIES ARCHITECTURE](https://docs.pjsip.org/en/latest/get-started/guidelines-api.html#which-api-to-use)

- [PJSUA-LIB](https://docs.pjsip.org/en/latest/api/pjsua-lib/index.html#pjsua-lib)

- [PJSUA2-High Level API](https://www.pjsip.org/docs/book-latest/html/intro_pjsua2.html)

  PJSUA2 is an object-oriented abstraction above PJSUA API. It provides high level API for constructing Session Initiation Protocol (SIP)  multimedia user agent applications (a.k.a Voice over IP/VoIP  softphones). It wraps together the signaling, media, and NAT traversal  functionality into easy to use call control API, account management,  buddy list management, presence, and instant messaging, along with  multimedia features such as local conferencing, file streaming, local  playback, and voice recording, and powerful NAT traversal techniques  utilizing STUN, TURN, and ICE.

  PJSUA2 is implemented on top of PJSUA-LIB API. The SIP and media  features and object modelling follows what PJSUA-LIB provides (for  example, we still have accounts, call, buddy, and so on), but the API to access them is different. These features will be described later in  this chapter. PJSUA2 is a C++ library, which you can find under `pjsip` directory in the PJSIP distribution. The C++ library can be used by  native C++ applications directly. *But PJSUA2 is not just a C++ library.  From the beginning, it has been designed to be accessible from high  level non-native languages such as Java and Python. This is achieved by  SWIG binding.*

```
import org.pjsip.pjsua2.*;

// Subclass to extend the Account and get notifications etc.
class MyAccount extends Account {
  @Override
  public void onRegState(OnRegStateParam prm) {
      System.out.println("*** On registration state: " + prm.getCode() + prm.getReason());
  }
}

public class test {
  static {
      System.loadLibrary("pjsua2");
      System.out.println("Library loaded");
  }

  public static void main(String argv[]) {
      try {
          // Create endpoint
          Endpoint ep = new Endpoint();
          ep.libCreate();
          // Initialize endpoint
          EpConfig epConfig = new EpConfig();
          ep.libInit( epConfig );
          // Create SIP transport. Error handling sample is shown
          TransportConfig sipTpConfig = new TransportConfig();
          sipTpConfig.setPort(5060);
          ep.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_UDP, sipTpConfig);
          // Start the library
          ep.libStart();

          AccountConfig acfg = new AccountConfig();
          acfg.setIdUri("sip:test@pjsip.org");
          acfg.getRegConfig().setRegistrarUri("sip:pjsip.org");
          AuthCredInfo cred = new AuthCredInfo("digest", "*", "test", 0, "secret");
          acfg.getSipConfig().getAuthCreds().add( cred );
          // Create the account
          MyAccount acc = new MyAccount();
          acc.create(acfg);
          // Here we don't have anything else to do..
          Thread.sleep(10000);
          /* Explicitly delete the account.
           * This is to avoid GC to delete the endpoint first before deleting
           * the account.
           */
          acc.delete();

          // Explicitly destroy and delete endpoint
          ep.libDestroy();
          ep.delete();

      } catch (Exception e) {
          System.out.println(e);
          return;
      }
  }
}
```
