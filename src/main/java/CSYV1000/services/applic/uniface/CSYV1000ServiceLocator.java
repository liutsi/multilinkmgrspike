/**
 * CSYV1000ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package CSYV1000.services.applic.uniface;

public class CSYV1000ServiceLocator extends org.apache.axis.client.Service implements CSYV1000.services.applic.uniface.CSYV1000Service {

    public CSYV1000ServiceLocator() {
    }


    public CSYV1000ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CSYV1000ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CSYV1000
    private java.lang.String CSYV1000_address = "http://localhost/pathway/environment.pathway";

    public java.lang.String getCSYV1000Address() {
        return CSYV1000_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CSYV1000WSDDServiceName = "CSYV1000";

    public java.lang.String getCSYV1000WSDDServiceName() {
        return CSYV1000WSDDServiceName;
    }

    public void setCSYV1000WSDDServiceName(java.lang.String name) {
        CSYV1000WSDDServiceName = name;
    }

    public CSYV1000.services.applic.uniface.CSYV1000PortType getCSYV1000() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CSYV1000_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCSYV1000(endpoint);
    }

    public CSYV1000.services.applic.uniface.CSYV1000PortType getCSYV1000(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            CSYV1000.services.applic.uniface.CSYV1000BindingStub _stub = new CSYV1000.services.applic.uniface.CSYV1000BindingStub(portAddress, this);
            _stub.setPortName(getCSYV1000WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCSYV1000EndpointAddress(java.lang.String address) {
        CSYV1000_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (CSYV1000.services.applic.uniface.CSYV1000PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                CSYV1000.services.applic.uniface.CSYV1000BindingStub _stub = new CSYV1000.services.applic.uniface.CSYV1000BindingStub(new java.net.URL(CSYV1000_address), this);
                _stub.setPortName(getCSYV1000WSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CSYV1000".equals(inputPortName)) {
            return getCSYV1000();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:uniface:applic:services:CSYV1000", "CSYV1000Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:uniface:applic:services:CSYV1000", "CSYV1000"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CSYV1000".equals(portName)) {
            setCSYV1000EndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
