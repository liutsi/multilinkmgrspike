package com.objective.multilinkmgr;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ResponseHandler extends DefaultHandler {
    boolean bfname = false;
    String sessionId = null;

    public void startElement(String uri, String localName, String qName,
                    Attributes attributes) throws SAXException {
        System.out.println("Start Element :" + qName+","+localName);
        if (qName.equalsIgnoreCase("sessionId")) {
            bfname = true;
        }
    }

    public void endElement(String uri, String localName,
                    String qName) throws SAXException {
        System.out.println("End Element :" + qName+","+localName);
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        if (bfname) {
            String sId = new String(ch, start, length);
            System.out.println("sessionId:" + sId);
            bfname = false;
            this.sessionId = sId;
        }
    }

    public String getSessionId() {
        return sessionId;
    }
}
