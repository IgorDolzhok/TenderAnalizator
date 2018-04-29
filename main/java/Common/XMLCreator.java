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
	
	public void createXMLCompletedTendersLinks(List<String> links) {
		try {
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbfactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			//create root element
			Element rootElement = doc.createElement("LinksCompleted");
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
			StreamResult result = new StreamResult(new File("Files/Links"));
			transformer.transform(source, result);
			//output to console for testing
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> readXMLsavedCompletedtendersLinks() {
		List<String> results = new ArrayList(); 
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File("Files/Links.xml"));
			doc.getDocumentElement().normalize();			
			System.out.println("Root element: "+doc.getDocumentElement().getNodeName());
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
