/**
 * CSYV1000PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package CSYV1000.services.applic.uniface;

public interface CSYV1000PortType extends java.rmi.Remote {
    public int EXTERNAL(java.lang.String REQUEST, java.lang.String REQUESTDATA, javax.xml.rpc.holders.StringHolder RESPONSE, javax.xml.rpc.holders.StringHolder RESPONSEDATA, javax.xml.rpc.holders.StringHolder RESPONSEERROR) throws java.rmi.RemoteException;
    public int LOGOFF(java.lang.String REQUEST, javax.xml.rpc.holders.StringHolder RESPONSE, javax.xml.rpc.holders.StringHolder RESPONSEERROR) throws java.rmi.RemoteException;
    public int LOGON(java.lang.String REQUEST, javax.xml.rpc.holders.StringHolder RESPONSE, javax.xml.rpc.holders.StringHolder RESPONSEERROR) throws java.rmi.RemoteException;
}
