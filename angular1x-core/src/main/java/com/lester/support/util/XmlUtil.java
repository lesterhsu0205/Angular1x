package com.lester.support.util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.CharacterData;

public class XmlUtil {
    public static String getCharacterDataFromElement(Element e) {
        if (e != null) {
            Node child = e.getFirstChild();
            if (child instanceof CharacterData) {
                CharacterData cd = (CharacterData) child;
                return cd.getData();
            }
        }
        return "";
    }
}
