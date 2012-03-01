package org.katawashoujofighter;


/** Helper class that reads XML files and helps to find data in them. */
import java.io.File;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

public class XML {

	/** Tries to parse a document for the given File. The document will be normalized*/
	public static Document parse(File file) throws Exception {
		DocumentBuilderFactory docBuilderFactory = 
				DocumentBuilderFactory.newInstance();		
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(file);
		// Normalize text representation		
        doc.getDocumentElement().normalize();
		return doc;
	}
	
	/** Tries to parse a document with the given string filename. */
	public static Document parse(String filename) throws Exception { 
		return parse(new File(filename));
	}
	
	/** Finds */
	public static Node findName(NodeList list, String name) {
		for(int index = 0; index < list.getLength(); index ++) {
			Node node = list.item(index);
			if (node.getNodeName().equals(name)) { return node; }					
		}
		return null;
	} 
	
	/** Gets the first child with the given tage name. */
	public static Node findChild(Node el, String name) {
		NodeList list = el.getChildNodes();
		return findName(list, name);
	} 
		

}
