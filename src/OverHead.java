import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OverHead {

	String title, author;
	
	public OverHead() {
		this("","");
	}

	public OverHead(String title, String author) {
		this.title = title;
		this.author = author;
	}
	
	public File makeCSS() {
		File css = new File("h1 {text-align: center;}");
		return css;
	}
	
	public File makeContent() {
		return null;
	}
	
	public File makeToc() {
		return null;
	}
	
	public File makeTox() {
		return null;
	}

	public File makeContainer() {
		try {
			File container = new File("EPUB/META-INF/container.xml");
			FileWriter f = new FileWriter(container);
			// Content does NOT rely on chapters
			f.write("<?xml version=\"1.0\"?>\n<container version=\"1.0\" xmlns=\"urn:oasis:names:tc:opendocument:xmlns:container\">\n  <rootfiles>\n    <rootfile full-path=\"OEBPS/content.opf\" media-type=\"application/oebps-package+xml\"/>\n  </rootfiles>\n</container>");
			f.close();
			return container;
		
		} catch (IOException ie) {
			ie.printStackTrace();
			return null;
		}
	}
	
	/*
	 * General Setter and getter methods
	 * 
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setTitle(String title) {
		this.author = title;
	}
	
	public String getTitle() {
		return author;
	}
	
}
