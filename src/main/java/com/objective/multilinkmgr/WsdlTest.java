package com.objective.multilinkmgr;

import CSYV1000.services.applic.uniface.CSYV1000PortType;
import CSYV1000.services.applic.uniface.CSYV1000ServiceLocator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.rpc.holders.StringHolder;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class WsdlTest {
    public static void main(String[] args) {
        System.out.println("----------------------start WsdlTest at " + new Date() + "----------------------");
        WsdlTest wsdlTest = new WsdlTest();
        wsdlTest.queryApplication();
    }

    private void queryApplication() {

        // connect to the crowd server, using the supplied service URL, similar to http://localhost:8095/crowd/services/SecurityServer?wsdl
        CSYV1000ServiceLocator secServer = new CSYV1000ServiceLocator();
        //secServer.setSecurityServerHttpPortEndpointAddress(secServer.getSecurityServerHttpPortAddress());

        // obtain a reference to the SOAP service, which axis manages.
        CSYV1000PortType stub = null;
        try {
            stub = (CSYV1000PortType) secServer.getPort(CSYV1000PortType.class);

            String reqLogon = "<root>\n"
                            + "    <request>\n"
                            + "        <service>CSYV1000</service>\n"
                            + "        <userId>Pathway-Demo\\NHogg</userId>\n"
                            + "        <password>Infor2010</password>\n"
                            + "    </request>\n"
                            + "</root>";
            StringHolder respLogon = new StringHolder();
            StringHolder faultLogon = new StringHolder();
            int retInt = 0; //stub.LOGON(reqLogon, respLogon, faultLogon);
            System.out.println("retInt: " + retInt);
            //System.out.println("respLogon: " + respLogon.value);
            System.out.println("faultLogon: " + faultLogon.value);

//            Map<String, String> pairs = readSessionIdFromResponse(respLogon);
            String sessionId = "07088C63-BEB7-48FA-969D-68B4A9A861B4"; //pairs.get("sessionId");
            System.out.println("logon.ret.sessionId:" + sessionId);

            List<String> classes = getClassType(stub, sessionId);

            for (String classId : classes) {
                findApplications(stub, sessionId, classId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Map<String, String> readSessionIdFromResponse(StringHolder resp) {
        Map<String, String> ret = null;

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            ResponseLogonHandler handler = new ResponseLogonHandler();
            saxParser.parse(new StringBufferInputStream(resp.value), handler);
            ret = handler.getPairs();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    private List<String> getClassType(CSYV1000PortType stub, String sessionId)
                    throws IOException, SAXException, ParserConfigurationException {
        System.out.println("-------------------------------begin getClasses-------------------------");

        String req = "<root>\n"
                        + "    <request>\n"
                        + "        <service>CIFV5010</service>\n"
                        + "        <sessionId>" + sessionId + "</sessionId>\n"
                        + "        <method>ClassType</method>"
                        + "    </request>\n"
                        + "</root>\n"
                        + "\n";

        String reqDate = "<root>\n"
                        + "    <request>\n"
                        + "        <applicationcode>LLC</applicationcode>\n"
                        + "    </request>\n"
                        + "</root>\n";

        StringHolder resp = new StringHolder();
        StringHolder respData = new StringHolder();
        StringHolder fault = new StringHolder();

        System.out.println("req:" + req);
        System.out.println("reqDate:" + reqDate);
        int retInt = stub.EXTERNAL(req, reqDate, resp, respData, fault);
        System.out.println("retInt:" + retInt);
        System.out.println("resp:" + resp.value);
        System.out.println("respData:" + respData.value);
        System.out.println("fault:" + fault.value);

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        ResponseClassTypeHandler handler = new ResponseClassTypeHandler();
        saxParser.parse(new StringBufferInputStream(respData.value), handler);
        return handler.getClasses();
    }

    private List<String> findApplications(CSYV1000PortType stub, String sessionId, String classId)
                    throws RemoteException {
        System.out.println("-------------------------------begin findApplications " + classId + "-------------------------");

        String req = "<root>\n"
                        + "    <request>\n"
                        + "        <service>CIFV5550</service>\n"
                        + "        <sessionId>" + sessionId + "</sessionId>\n"
                        + "        <method>FindApplications</method>\n"
                        + "    </request>\n"
                        + "</root>";

        String reqDate = "<root>\n"
                        + " <request>\n"
                        + "  <retrieveCount>10</retrieveCount>\n"
                        + "  <responseCount>10</responseCount>\n"
                        + "  <applicationClassId>" + classId + "</applicationClassId>\n"
                        + "  <fromApplicationId></fromApplicationId>\n"
                        + "  <toApplicationId></toApplicationId>\n"
                        + "  <applicationTypeId></applicationTypeId>\n"
                        + "  <applicationStatusId></applicationStatusId>\n"
                        + "  <fromApplicationNumber></fromApplicationNumber>\n"
                        + "  <toApplicationNumber></toApplicationNumber>\n"
                        + "  <fromApplicationYear></fromApplicationYear>\n"
                        + "  <toApplicationYear></toApplicationYear>\n"
                        + "  <fromLodgementDate></fromLodgementDate>\n"
                        + "  <toLodgementDate></toLodgementDate>\n"
                        + "  <locationSummaryRequired></locationSummaryRequired>\n"
                        + "  <applicationSummaryRequired></applicationSummaryRequired>\n"
                        + " </request>\n"
                        + "</root>\n";

        StringHolder resp = new StringHolder();
        StringHolder respData = new StringHolder();
        StringHolder fault = new StringHolder();

        System.out.println("req:" + req);
        System.out.println("reqDate:" + reqDate);
        int retInt = stub.EXTERNAL(req, reqDate, resp, respData, fault);
        System.out.println("retInt:" + retInt);
        System.out.println("resp:" + resp.value);
        System.out.println("respData:" + respData.value);
        System.out.println("fault:" + fault.value);
        return null;
    }
}

