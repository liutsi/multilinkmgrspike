package com.objective.multilinkmgr;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

public class ResponseLogonHandler extends DefaultHandler {
    boolean bfname = false;
    String fieldName = null;
    Map<String, String> pairs = new HashMap<>();

    public void startElement(String uri, String localName, String qName,
                    Attributes attributes) throws SAXException {
        System.out.println("Start Element :" + qName + "," + localName);
        bfname = true;
        fieldName = qName;
    }

    public void endElement(String uri, String localName,
                    String qName) throws SAXException {
        System.out.println("End Element :" + qName + "," + localName);
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        if (bfname) {
            String sId = new String(ch, start, length);
            System.out.println("Setting: " + fieldName + ": " + sId);
            bfname = false;
            pairs.put(fieldName, sId);
        }
    }

    public Map<String, String> getPairs() {
        return pairs;
    }

    public void setPairs(Map<String, String> pairs) {
        this.pairs = pairs;
    }
}
