/**
 * ALFAdminSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.eclipse.www.alf.eventManager.admin;

public class ALFAdminSoapBindingStub extends org.apache.axis.client.Stub implements org.eclipse.www.alf.eventManager.admin.ALFAdmin_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[10];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("pause");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "applicationName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFault"),
                      "org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFaultType"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidApplicationNameFault"),
                      "org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidApplicationNameFaultType"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deploy");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "eventMapXML"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "applicationName"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFault"),
                      "org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFaultType"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidApplicationNameFault"),
                      "org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidApplicationNameFaultType"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidEventMapFault"),
                      "org.eclipse.www.alf.eventManager.admin.InvalidEventMapFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidEventMapFaultType"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eventMap");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "applicationName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "eventMapXML"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFault"),
                      "org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFaultType"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidApplicationNameFault"),
                      "org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidApplicationNameFaultType"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("status");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "applicationName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "ApplicationStatus"));
        oper.setReturnClass(org.eclipse.www.alf.eventManager.admin.ApplicationStatus.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "status"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFault"),
                      "org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFaultType"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidApplicationNameFault"),
                      "org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidApplicationNameFaultType"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("allStatus");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "ApplicationStatus"));
        oper.setReturnClass(org.eclipse.www.alf.eventManager.admin.ApplicationStatus[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "getAllApplicationStatusReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFault"),
                      "org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFaultType"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("unDeploy");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "applicationName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFault"),
                      "org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFaultType"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidApplicationNameFault"),
                      "org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidApplicationNameFaultType"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("pauseAll");
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFault"),
                      "org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFaultType"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("resumeAll");
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFault"),
                      "org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFaultType"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("resume");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "applicationName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFault"),
                      "org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFaultType"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidApplicationNameFault"),
                      "org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidApplicationNameFaultType"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("replace");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "newApplicationName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "oldApplicationName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFault"),
                      "org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFaultType"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidApplicationNameFault"),
                      "org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType",
                      new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidApplicationNameFaultType"), 
                      true
                     ));
        _operations[9] = oper;

    }

    public ALFAdminSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ALFAdminSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ALFAdminSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">allStatus");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.AllStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">allStatusResponse");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.ApplicationStatus[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "ApplicationStatus");
            qName2 = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "getAllApplicationStatusReturn");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">deploy");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.Deploy.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">deployResponse");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.DeployResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">eventMap");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.EventMap.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">eventMapResponse");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.EventMapResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">pause");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.Pause.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">pauseAll");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.PauseAll.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">pauseAllResponse");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.PauseAllResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">pauseResponse");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.PauseResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">replace");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.Replace.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">replaceResponse");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.ReplaceResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">resume");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.Resume.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">resumeAll");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.ResumeAll.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">resumeAllResponse");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.ResumeAllResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">resumeResponse");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.ResumeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">status");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.Status.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">statusResponse");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.StatusResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">unDeploy");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.UnDeploy.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", ">unDeployResponse");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.UnDeployResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "ApplicationStatus");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.ApplicationStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidApplicationNameFaultType");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "InvalidEventMapFaultType");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.InvalidEventMapFaultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "statusType");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.StatusType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "UnexpectedFaultType");
            cachedSerQNames.add(qName);
            cls = org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public void pause(java.lang.String applicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "pause"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {applicationName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String deploy(java.lang.String eventMapXML) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType, org.eclipse.www.alf.eventManager.admin.InvalidEventMapFaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "deploy"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {eventMapXML});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.InvalidEventMapFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.InvalidEventMapFaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String eventMap(java.lang.String applicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "eventMap"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {applicationName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.eclipse.www.alf.eventManager.admin.ApplicationStatus status(java.lang.String applicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "status"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {applicationName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.eclipse.www.alf.eventManager.admin.ApplicationStatus) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.eclipse.www.alf.eventManager.admin.ApplicationStatus) org.apache.axis.utils.JavaUtils.convert(_resp, org.eclipse.www.alf.eventManager.admin.ApplicationStatus.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.eclipse.www.alf.eventManager.admin.ApplicationStatus[] allStatus() throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "allStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.eclipse.www.alf.eventManager.admin.ApplicationStatus[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.eclipse.www.alf.eventManager.admin.ApplicationStatus[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.eclipse.www.alf.eventManager.admin.ApplicationStatus[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void unDeploy(java.lang.String applicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "unDeploy"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {applicationName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void pauseAll() throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "pauseAll"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void resumeAll() throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "resumeAll"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void resume(java.lang.String applicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "resume"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {applicationName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void replace(java.lang.String newApplicationName, java.lang.String oldApplicationName) throws java.rmi.RemoteException, org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType, org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.eclipse.org/alf/eventManager/admin", "replace"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {newApplicationName, oldApplicationName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.UnexpectedFaultType) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType) {
              throw (org.eclipse.www.alf.eventManager.admin.InvalidApplicationNameFaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
