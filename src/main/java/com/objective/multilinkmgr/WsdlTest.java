package com.objective.multilinkmgr;

import CSYV1000.services.applic.uniface.CSYV1000PortType;
import CSYV1000.services.applic.uniface.CSYV1000Service;
import CSYV1000.services.applic.uniface.CSYV1000ServiceLocator;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.rpc.holders.StringHolder;
import java.io.BufferedInputStream;
import java.io.StringBufferInputStream;

public class WsdlTest {
    public static void main(String[] args) {
        WsdlTest wsdlTest = new WsdlTest();
        wsdlTest.queryApplication();
    }

    private void queryApplication() {

        // connect to the crowd server, using the supplied service URL, similar to http://localhost:8095/crowd/services/SecurityServer?wsdl
        CSYV1000ServiceLocator secServer = new CSYV1000ServiceLocator();
        //secServer.setSecurityServerHttpPortEndpointAddress(secServer.getSecurityServerHttpPortAddress());

        String reqLogon = "<root>\n"
                        + "    <request>\n"
                        + "        <service>CSYV1000</service>\n"
                        + "        <userId>Pathway-Demo\\NHogg</userId>\n"
                        + "        <password>Infor2010</password>\n"
                        + "    </request>\n"
                        + "</root>";
        StringHolder respLogon = new StringHolder();
        StringHolder faultLogon = new StringHolder();

        StringHolder respExternal = new StringHolder();
        StringHolder respDataExternal = new StringHolder();
        StringHolder faultExternal = new StringHolder();

        String reqData = "<root>\n"
                        + " <request>\n"
                        + "  <retrieveCount>10</retrieveCount>\n"
                        + "  <responseCount>10</responseCount>\n"
                        + "  <applicationClassId></applicationClassId>\n"
                        + "  <fromApplicationId>1</fromApplicationId>\n"
                        + "  <toApplicationId>8</toApplicationId>\n"
                        + "  <applicationTypeId></applicationTypeId>\n"
                        + "  <applicationStatusId></applicationStatusId>\n"
                        + "  <fromApplicationNumber></fromApplicationNumber>\n"
                        + "  <toApplicationNumber></toApplicationNumber>\n"
                        + "  <fromApplicationYear></fromApplicationYear>\n"
                        + "  <toApplicationYear></toApplicationYear>\n"
                        + "  <fromLodgementDate></fromLodgementDate>\n"
                        + "  <toLodgementDate></toLodgementDate>\n"
                        + "  <locationSummaryRequired>true</locationSummaryRequired>\n"
                        + "  <applicationSummaryRequired>true</applicationSummaryRequired>\n"
                        + " </request>\n"
                        + "</root>\n";

        // obtain a reference to the SOAP service, which axis manages.
        CSYV1000PortType stub = null;
        try {
            stub = (CSYV1000PortType) secServer.getPort(CSYV1000PortType.class);

            int token = stub.LOGON(reqLogon, respLogon, faultLogon);

            System.out.println("return: " + token);
            System.out.println("return2: " + respLogon.value);
            System.out.println("return3: " + faultLogon.value);

            String sessionId = readSessionIdFromResponse(respLogon);
            System.out.println("logon.sessionId:" + sessionId);

            System.out.println("-------------------------------begin EXTERNAL-------------------------");

            String regExternal = "<root>\n"
                            + "    <request>\n"
                            + "        <service>CIFV5550</service>\n"
                            + "        <sessionId>" + sessionId + "</sessionId>\n"
                            + "        <method>FindApplications</method>\n"
                            + "    </request>\n"
                            + "</root>";

            int tokenEternal = stub.EXTERNAL(regExternal, reqData, respExternal, respDataExternal, faultExternal);
            System.out.println("tokenExternal:"+tokenEternal);
            System.out.println("respExternal:"+respExternal.value);
            System.out.println("respDataExternal:"+respDataExternal.value);
            System.out.println("faultExternal:"+faultExternal.value);
        } catch (Exception e) {
            System.out.println("error12341234:" + respLogon);
            e.printStackTrace();
        }

    }

    private String readSessionIdFromResponse(StringHolder resp) {
        String ret = null;

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            ResponseHandler handler = new ResponseHandler();

            saxParser.parse(new StringBufferInputStream(resp.value), handler);
            ret = handler.getSessionId();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}

