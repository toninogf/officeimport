/**
 * ApplicationStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.eclipse.www.alf.eventManager.admin;

public class ApplicationStatus  implements java.io.Serializable {
    private java.lang.String applicationName;

    private java.util.Calendar deploymentTimestamp;

    private java.util.Calendar lastPausedTimestamp;

    private java.util.Calendar lastResumedTimestamp;

    private org.eclipse.www.alf.eventManager.admin.StatusType status;

    public ApplicationStatus() {
    }

    public ApplicationStatus(
           java.lang.String applicationName,
           java.util.Calendar deploymentTimestamp,
           java.util.Calendar lastPausedTimestamp,
           java.util.Calendar lastResumedTimestamp,
           org.eclipse.www.alf.eventManager.admin.StatusType status) {
           this.applicationName = applicationName;
           this.deploymentTimestamp = deploymentTimestamp;
           this.lastPausedTimestamp = lastPausedTimestamp;
           this.lastResumedTimestamp = lastResumedTimestamp;
           this.status = status;
    }


    /**
     * Gets the applicationName value for this ApplicationStatus.
     * 
     * @return applicationName
     */
    public java.lang.String getApplicationName() {
        return applicationName;
    }


    /**
     * Sets the applicationName value for this ApplicationStatus.
     * 
     * @param applicationName
     */
    public void setApplicationName(java.lang.String applicationName) {
        this.applicationName = applicationName;
    }


    /**
     * Gets the deploymentTimestamp value for this ApplicationStatus.
     * 
     * @return deploymentTimestamp
     */
    public java.util.Calendar getDeploymentTimestamp() {
        return deploymentTimestamp;
    }


    /**
     * Sets the deploymentTimestamp value for this ApplicationStatus.
     * 
     * @param deploymentTimestamp
     */
    public void setDeploymentTimestamp(java.util.Calendar deploymentTimestamp) {
        this.deploymentTimestamp = deploymentTimestamp;
    }


    /**
     * Gets the lastPausedTimestamp value for this ApplicationStatus.
     * 
     * @return lastPausedTimestamp
     */
    public java.util.Calendar getLastPausedTimestamp() {
        return lastPausedTimestamp;
    }


    /**
     * Sets the lastPausedTimestamp value for this ApplicationStatus.
     * 
     * @param lastPausedTimestamp
     */
    public void setLastPausedTimestamp(java.util.Calendar lastPausedTimestamp) {
        this.lastPausedTimestamp = lastPausedTimestamp;
    }


    /**
     * Gets the lastResumedTimestamp value for this ApplicationStatus.
     * 
     * @return lastResumedTimestamp
     */
    public java.util.Calendar getLastResumedTimestamp() {
        return lastResumedTimestamp;
    }


    /**
     * Sets the lastResumedTimestamp value for this ApplicationStatus.
     * 
     * @param lastResumedTimestamp
     */
    public void setLastResumedTimestamp(java.util.Calendar lastResumedTimestamp) {
        this.lastResumedTimestamp = lastResumedTimestamp;
    }


    /**
     * Gets the status value for this ApplicationStatus.
     * 
     * @return status
     */
    public org.eclipse.www.alf.eventManager.admin.StatusType getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ApplicationStatus.
     * 
     * @param status
     */
    public void setStatus(org.eclipse.www.alf.eventManager.admin.StatusType status) {
        this.status = status;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ApplicationStatus)) return false;
        ApplicationStatus other = (ApplicationStatus) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.applicationName==null && other.getApplicationName()==null) || 
             (this.applicationName!=null &&
              this.applicationName.equals(other.getApplicationName()))) &&
            ((this.deploymentTimestamp==null && other.getDeploymentTimestamp()==null) || 
             (this.deploymentTimestamp!=null &&
              this.deploymentTimestamp.equals(other.getDeploymentTimestamp()))) &&
            ((this.lastPausedTimestamp==null && other.getLastPausedTimestamp()==null) || 
             (this.lastPausedTimestamp!=null &&
              this.lastPausedTimestamp.equals(other.getLastPausedTimestamp()))) &&
            ((this.lastResumedTimestamp==null && other.getLastResumedTimestamp()==null) || 
             (this.lastResumedTimestamp!=null &&
              this.lastResumedTimestamp.equals(other.getLastResumedTimestamp()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getApplicationName() != null) {
            _hashCode += getApplicationName().hashCode();
        }
        if (getDeploymentTimestamp() != null) {
            _hashCode += getDeploymentTimestamp().hashCode();
        }
        if (getLastPausedTimestamp() != null) {
            _hashCode += getLastPausedTimestamp().hashCode();
        }
        if (getLastResumedTimestamp() != null) {
            _hashCode += getLastResumedTimestamp().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ApplicationStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "ApplicationStatus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "applicationName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deploymentTimestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "deploymentTimestamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastPausedTimestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "lastPausedTimestamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastResumedTimestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "lastResumedTimestamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "statusType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
