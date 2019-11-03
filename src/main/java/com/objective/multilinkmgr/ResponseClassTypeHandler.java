package com.objective.multilinkmgr;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseClassTypeHandler extends DefaultHandler {
    boolean bfname = false;
    String fieldName = null;
    List<String> classes = new ArrayList<>();

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
            if ("classid".equalsIgnoreCase(fieldName)) {
                classes.add(sId);
            }
        }
    }

    public List<String> getClasses() {
        return classes;
    }
}
