/**
 * ALFAdmin_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.eclipse.www.alf.eventManager.admin;

public class ALFAdmin_ServiceLocator extends org.apache.axis.client.Service implements org.eclipse.www.alf.eventManager.admin.ALFAdmin_Service {

    public ALFAdmin_ServiceLocator() {
    }


    public ALFAdmin_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ALFAdmin_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ALFAdmin
    private java.lang.String ALFAdmin_address = "http://133.133.133.57:8080/ALFEventManager/services/ALFAdmin";

    public java.lang.String getALFAdminAddress() {
        return ALFAdmin_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ALFAdminWSDDServiceName = "ALFAdmin";

    public java.lang.String getALFAdminWSDDServiceName() {
        return ALFAdminWSDDServiceName;
    }

    public void setALFAdminWSDDServiceName(java.lang.String name) {
        ALFAdminWSDDServiceName = name;
    }

    public org.eclipse.www.alf.eventManager.admin.ALFAdmin_PortType getALFAdmin() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ALFAdmin_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getALFAdmin(endpoint);
    }

    public org.eclipse.www.alf.eventManager.admin.ALFAdmin_PortType getALFAdmin(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.eclipse.www.alf.eventManager.admin.ALFAdminSoapBindingStub _stub = new org.eclipse.www.alf.eventManager.admin.ALFAdminSoapBindingStub(portAddress, this);
            _stub.setPortName(getALFAdminWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setALFAdminEndpointAddress(java.lang.String address) {
        ALFAdmin_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.eclipse.www.alf.eventManager.admin.ALFAdmin_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                org.eclipse.www.alf.eventManager.admin.ALFAdminSoapBindingStub _stub = new org.eclipse.www.alf.eventManager.admin.ALFAdminSoapBindingStub(new java.net.URL(ALFAdmin_address), this);
                _stub.setPortName(getALFAdminWSDDServiceName());
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
        if ("ALFAdmin".equals(inputPortName)) {
            return getALFAdmin();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "ALFAdmin");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "ALFAdmin"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ALFAdmin".equals(portName)) {
            setALFAdminEndpointAddress(address);
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
