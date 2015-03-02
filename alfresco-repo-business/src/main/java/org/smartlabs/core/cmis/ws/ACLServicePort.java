package org.smartlabs.core.cmis.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.4
 * 2015-03-01T13:47:38.029-04:30
 * Generated source version: 2.7.4
 * 
 */
@WebService(targetNamespace = "http://docs.oasis-open.org/ns/cmis/ws/200908/", name = "ACLServicePort")
@XmlSeeAlso({org.smartlabs.core.cmis.core.ObjectFactory.class, org.smartlabs.core.cmis.messaging.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ACLServicePort {

    @WebResult(name = "applyACLResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", partName = "parameters")
    @WebMethod
    public org.smartlabs.core.cmis.messaging.ApplyACLResponse applyACL(
        @WebParam(partName = "parameters", name = "applyACL", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.smartlabs.core.cmis.messaging.ApplyACL parameters
    ) throws CmisException;

    @WebResult(name = "getACLResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", partName = "parameters")
    @WebMethod
    public org.smartlabs.core.cmis.messaging.GetACLResponse getACL(
        @WebParam(partName = "parameters", name = "getACL", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.smartlabs.core.cmis.messaging.GetACL parameters
    ) throws CmisException;
}