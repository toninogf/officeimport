/**
 * Replace.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.eclipse.www.alf.eventManager.admin;

public class Replace  implements java.io.Serializable {
    private java.lang.String newApplicationName;

    private java.lang.String oldApplicationName;

    public Replace() {
    }

    public Replace(
           java.lang.String newApplicationName,
           java.lang.String oldApplicationName) {
           this.newApplicationName = newApplicationName;
           this.oldApplicationName = oldApplicationName;
    }


    /**
     * Gets the newApplicationName value for this Replace.
     * 
     * @return newApplicationName
     */
    public java.lang.String getNewApplicationName() {
        return newApplicationName;
    }


    /**
     * Sets the newApplicationName value for this Replace.
     * 
     * @param newApplicationName
     */
    public void setNewApplicationName(java.lang.String newApplicationName) {
        this.newApplicationName = newApplicationName;
    }


    /**
     * Gets the oldApplicationName value for this Replace.
     * 
     * @return oldApplicationName
     */
    public java.lang.String getOldApplicationName() {
        return oldApplicationName;
    }


    /**
     * Sets the oldApplicationName value for this Replace.
     * 
     * @param oldApplicationName
     */
    public void setOldApplicationName(java.lang.String oldApplicationName) {
        this.oldApplicationName = oldApplicationName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Replace)) return false;
        Replace other = (Replace) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.newApplicationName==null && other.getNewApplicationName()==null) || 
             (this.newApplicationName!=null &&
              this.newApplicationName.equals(other.getNewApplicationName()))) &&
            ((this.oldApplicationName==null && other.getOldApplicationName()==null) || 
             (this.oldApplicationName!=null &&
              this.oldApplicationName.equals(other.getOldApplicationName())));
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
        if (getNewApplicationName() != null) {
            _hashCode += getNewApplicationName().hashCode();
        }
        if (getOldApplicationName() != null) {
            _hashCode += getOldApplicationName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Replace.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">replace"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newApplicationName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "newApplicationName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oldApplicationName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "oldApplicationName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
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
