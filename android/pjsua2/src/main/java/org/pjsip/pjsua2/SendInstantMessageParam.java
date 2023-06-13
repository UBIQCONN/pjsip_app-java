/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.1
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.pjsip.pjsua2;

public class SendInstantMessageParam {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SendInstantMessageParam(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SendInstantMessageParam obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  @SuppressWarnings("deprecation")
  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pjsua2JNI.delete_SendInstantMessageParam(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setContentType(String value) {
    pjsua2JNI.SendInstantMessageParam_contentType_set(swigCPtr, this, value);
  }

  public String getContentType() {
    return pjsua2JNI.SendInstantMessageParam_contentType_get(swigCPtr, this);
  }

  public void setContent(String value) {
    pjsua2JNI.SendInstantMessageParam_content_set(swigCPtr, this, value);
  }

  public String getContent() {
    return pjsua2JNI.SendInstantMessageParam_content_get(swigCPtr, this);
  }

  public void setTxOption(SipTxOption value) {
    pjsua2JNI.SendInstantMessageParam_txOption_set(swigCPtr, this, SipTxOption.getCPtr(value), value);
  }

  public SipTxOption getTxOption() {
    long cPtr = pjsua2JNI.SendInstantMessageParam_txOption_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SipTxOption(cPtr, false);
  }

  public void setUserData(SWIGTYPE_p_void value) {
    pjsua2JNI.SendInstantMessageParam_userData_set(swigCPtr, this, SWIGTYPE_p_void.getCPtr(value));
  }

  public SWIGTYPE_p_void getUserData() {
    long cPtr = pjsua2JNI.SendInstantMessageParam_userData_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public SendInstantMessageParam() {
    this(pjsua2JNI.new_SendInstantMessageParam(), true);
  }

}
