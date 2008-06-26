package org.eclipse.www.alf.eventManager.admin;

public class ALFAdminProxy implements org.eclipse.www.alf.eventManager.admin.ALFAdmin_PortType {
  private String _endpoint = null;
  private org.eclipse.www.alf.eventManager.admin.ALFAdmin_PortType aLFAdmin_PortType = null;
  
  public ALFAdminProxy() {
    _initALFAdminProxy();
  }
  
  public ALFAdminProxy(String endpoint) {
    _endpoint = endpoint;
    _initALFAdminProxy();
  }
  
  private void _initALFAdminProxy() {
    try {
      aLFAdmin_PortType = (new org.eclipse.www.alf.eventManager.admin.ALFAdmin_ServiceLocator()).getALFAdmin();
      if (aLFAdmin_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)aLFAdmin_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)aLFAdmin_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (aLFAdmin_PortType != null)
      ((javax.xml.rpc.Stub)aLFAdmin_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.eclipse.www.alf.eventManager.admin.ALFAdmin_PortType getALFAdmin_PortType() {
    if (aLFAdmin_PortType == null)
      _initALFAdminProxy();
    return aLFAdmin_PortType;
  }
  
  public void pause(java.lang.String applicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType{
    if (aLFAdmin_PortType == null)
      _initALFAdminProxy();
    aLFAdmin_PortType.pause(applicationName);
  }
  
  public java.lang.String deploy(java.lang.String eventMapXML) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType, org.eclipse.www.alf.eventManager.admin.InvalidEventMapFaultType{
    if (aLFAdmin_PortType == null)
      _initALFAdminProxy();
    return aLFAdmin_PortType.deploy(eventMapXML);
  }
  
  public java.lang.String eventMap(java.lang.String applicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType{
    if (aLFAdmin_PortType == null)
      _initALFAdminProxy();
    return aLFAdmin_PortType.eventMap(applicationName);
  }
  
  public org.eclipse.www.alf.eventManager.admin.ApplicationStatus status(java.lang.String applicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType{
    if (aLFAdmin_PortType == null)
      _initALFAdminProxy();
    return aLFAdmin_PortType.status(applicationName);
  }
  
  public org.eclipse.www.alf.eventManager.admin.ApplicationStatus[] allStatus() throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType{
    if (aLFAdmin_PortType == null)
      _initALFAdminProxy();
    return aLFAdmin_PortType.allStatus();
  }
  
  public void unDeploy(java.lang.String applicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType{
    if (aLFAdmin_PortType == null)
      _initALFAdminProxy();
    aLFAdmin_PortType.unDeploy(applicationName);
  }
  
  public void pauseAll() throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType{
    if (aLFAdmin_PortType == null)
      _initALFAdminProxy();
    aLFAdmin_PortType.pauseAll();
  }
  
  public void resumeAll() throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType{
    if (aLFAdmin_PortType == null)
      _initALFAdminProxy();
    aLFAdmin_PortType.resumeAll();
  }
  
  public void resume(java.lang.String applicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType{
    if (aLFAdmin_PortType == null)
      _initALFAdminProxy();
    aLFAdmin_PortType.resume(applicationName);
  }
  
  public void replace(java.lang.String newApplicationName, java.lang.String oldApplicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType{
    if (aLFAdmin_PortType == null)
      _initALFAdminProxy();
    aLFAdmin_PortType.replace(newApplicationName, oldApplicationName);
  }
  
  
}