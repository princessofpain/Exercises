package fop;

public class XMLToPDF {
	public static void main(String[] args) {
		XMLToPDF xml = new XMLToPDF();
		
		try {
			xml.toPDF();
		} catch(FOPException e) {
			e.printStackTrace();
		}
	}
}
