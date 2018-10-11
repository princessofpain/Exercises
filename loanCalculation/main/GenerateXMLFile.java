package main;

import java.io.File;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GenerateXMLFile {
	public void generateXML(String type, Loan[] calculation) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("LoanRepayment");
			doc.appendChild(rootElement);
			rootElement.setAttribute("type", type);
			
			for(Loan monthlyCalculation: calculation) {
				Element month = doc.createElement("month");
				rootElement.appendChild(month);
				int currentIndex = Arrays.asList(calculation).indexOf(monthlyCalculation) + 1;
				month.setAttribute("id", String.valueOf(currentIndex));
				
				Element rest = doc.createElement("rest");
				month.appendChild(rest);
				rest.setTextContent(String.valueOf(monthlyCalculation.getRestBefore()));
				
				Element rate = doc.createElement("rate");
				month.appendChild(rate);
				rate.setTextContent(String.valueOf(monthlyCalculation.getRate()));
				
				Element interest = doc.createElement("interest");
				month.appendChild(interest);
				interest.setTextContent(String.valueOf(monthlyCalculation.getRate()));
				
				Element totalPay = doc.createElement("totalPay");
				month.appendChild(totalPay);
				totalPay.setTextContent(String.valueOf(monthlyCalculation.getTotalPay()));

				Element newRest = doc.createElement("newRest");
				month.appendChild(newRest);
				newRest.setTextContent(String.valueOf(monthlyCalculation.getRestAfter()));
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("D:\\Java\\ExercisesGithub\\Exercises\\loanCalculation\\loanCalculation.xml"));
			
			transformer.transform(source, result);
			
		} catch(ParserConfigurationException e) {
			e.printStackTrace();
		} catch(TransformerException e) {
			e.printStackTrace();
		}
	}
}
