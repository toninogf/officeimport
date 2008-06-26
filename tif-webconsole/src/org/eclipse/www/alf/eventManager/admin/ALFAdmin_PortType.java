/**
 * ALFAdmin_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.eclipse.www.alf.eventManager.admin;

public interface ALFAdmin_PortType extends java.rmi.Remote {
    public void pause(java.lang.String applicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType;
    public java.lang.String deploy(java.lang.String eventMapXML) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType, org.eclipse.www.alf.eventManager.admin.InvalidEventMapFaultType;
    public java.lang.String eventMap(java.lang.String applicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType;
    public org.eclipse.www.alf.eventManager.admin.ApplicationStatus status(java.lang.String applicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType;
    public org.eclipse.www.alf.eventManager.admin.ApplicationStatus[] allStatus() throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType;
    public void unDeploy(java.lang.String applicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType;
    public void pauseAll() throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType;
    public void resumeAll() throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType;
    public void resume(java.lang.String applicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType;
    public void replace(java.lang.String newApplicationName, java.lang.String oldApplicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType;
}
