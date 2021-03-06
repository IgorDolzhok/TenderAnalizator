package Common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLCreator {

	public XMLCreator() {
		super();
	}
	
	public void createXMListOfLinks(List<String> links, String rootName, String fileName){
		try {
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbfactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			//create root element
			Element rootElement = doc.createElement(rootName);
			doc.appendChild(rootElement);
			//create elements with links
			for(int x=0; x<links.size(); x++) {
			Element link = doc.createElement("Item");
			rootElement.appendChild(link);
			//setting attributes to element
			Attr id = doc.createAttribute("id");
			id.setValue(""+x);
			link.setAttributeNode(id);
			link.appendChild(doc.createTextNode(links.get(x)));			
			}
			//write content into xml-file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("Files/"+fileName));
			transformer.transform(source, result);
			//output to console for testing
			//StreamResult consoleResult = new StreamResult(System.out);
			//transformer.transform(source, consoleResult);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createXMLfinished(List<String> links) {
		String rootName = "FinishedBargains";
		String filename = "Finished.xml";
		createXMListOfLinks(links, rootName, filename);
	}
	
	public void createXMLQualified(List<String> links) {
		String rootName = "QualifiedBargains";
		String filename = "Qualified.xml";
		createXMListOfLinks(links, rootName, filename);		 
	}
	
	public void createXMLPropositionsViewed(List<String> links) {
		String rootName = "Viewedpropositions";
		String filename = "ViewedPropositions.xml";
		createXMListOfLinks(links, rootName, filename);		 
	}
	
	public List<String> readXMLfinished(){
		String filename="Finished.xml";
		return readXMLinks(filename);
	}
	
	public List<String> readXMLQualified(){
		String filename="Qualified.xml";
		return readXMLinks(filename);
	}
	
	public List<String> readXMLPropositionsViewed(){
		String filename="ViewedPropositions.xml";
		return readXMLinks(filename);
	}	 
	
	public List<String> readXMLinks(String fileName) {
		List<String> results = new ArrayList(); 
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File("Files/"+fileName));
			doc.getDocumentElement().normalize();			
			//System.out.println("Root element: "+doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Item");	
			for(int b= 0; b<nList.getLength(); b++) {
			Node oneItem = nList.item(b);
			results.add(oneItem.getTextContent().trim());
			
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return results;
	}
	

}
