import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OverHead {

	// default epub name is equal to title
	private String title, author, epubName;
	
	public OverHead() {
		this("","","");
	}

	public OverHead(String title, String author) {
		this(title, author, title);
	}
	
	public OverHead(String title, String author, String epubName) {
		this.title = title;
		this.author = author;
		this.epubName = title.trim();
	}
	
	public void makeOverhead() {
		try {
			makeContent();
			makeCSS();
			makeToc();
			makeTox();
			makeContainer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void makeCSS() throws IOException {
		// Make CSS folder
		File CSS = new File("EPUB/OEPBS/CSS");
		CSS.mkdir();
		
		// make css template
		File css = new File("EPUB/OEPBS/CSS/template.css");
		FileWriter f = new FileWriter(css);
		f.write("h1 {text-align: center;}");
		f.close();
		css.createNewFile();
	}
	
	public void makeContent() throws IOException {
		File content_opf = new File("EPUB/OEPBS/content.opf");
		FileWriter f = new FileWriter(content_opf);
		f.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<package xmlns=\"http://www.idpf.org/2007/opf\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" unique-identifier=\"db-id\" version=\"3.0\">\n\n\t<metadata>\n	    <dc:title id=\"t1\">Sample .epub Book</dc:title>\n	    <dc:creator opf:role=\"aut\">Thomas Hansen</dc:creator>\n	    <dc:identifier id=\"db-id\">isbn</dc:identifier>\n	    <meta property=\"dcterms:modified\">2014-03-27T09:14:09Z</meta>\n	    <dc:language>en</dc:language>\n	</metadata>\n\n	<manifest>\n	    <item id=\"toc\" properties=\"nav\" href=\"toc.xhtml\" media-type=\"application/xhtml+xml\" />\n	    <item id=\"ncx\" href=\"toc.ncx\" media-type=\"application/x-dtbncx+xml\" />\n	    <item id=\"template_css\" href=\"template.css\" media-type=\"text/css\" />\n	    <item id=\"toc\" href=\"toc.xhtml\" media-type=\"application/xhtml+xml\" />\n	    <item id=\"chapter_1\" href=\"chapter_1.xhtml\" media-type=\"application/xhtml+xml\" />\n	    <item id=\"chapter_2\" href=\"chapter_2.xhtml\" media-type=\"application/xhtml+xml\" />\n	    <item id=\"copyright\" href=\"copyright.xhtml\" media-type=\"application/xhtml+xml\" />\n	</manifest>\n\n	<spine toc=\"ncx\">\n		<itemref idref=\"toc\" />\n	    <itemref idref=\"chapter_1\" />\n	    <itemref idref=\"chapter_2\" />\n	    <itemref idref=\"copyright\" />\n	</spine>\n\n</package>");
		f.close();
		content_opf.createNewFile();
	}
	
	public void makeToc() throws IOException {
		
		File toc_xhtml = new File("EPUB/OEPBS/toc.xhtml");
		FileWriter f = new FileWriter(toc_xhtml);
		f.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:epub=\"http://www.idpf.org/2007/ops\">\n<head>\n<title>toc.xhtml</title>\n<link href=\"template.css\" rel=\"stylesheet\" type=\"text/css\" />\n</head>\n<body>\n    <nav id=\"toc\" epub:type=\"toc\">\n        <h1 class=\"frontmatter\">Table of Contents</h1>\n        <ol class=\"contents\">\n               <li><a href=\"chapter_1.xhtml\">Chapter 1</a></li>\n               <li><a href=\"chapter_2.xhtml\">Chapter 2</a></li>\n               <li><a href=\"copyright.xhtml\">Copyright</a></li>\n        </ol>\n    </nav>\n</body>\n</html>");
		f.close();
		toc_xhtml.createNewFile();
		
	}
	
	public File makeTox() throws IOException {
		File toc_ncx = new File("EPUB/OEPBS/toc.ncx");
		FileWriter f = new FileWriter(toc_ncx);
		f.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<ncx version=\"2005-1\" xml:lang=\"en\" xmlns=\"http://www.daisy.org/z3986/2005/ncx/\">\n\n<head>\n    <meta name=\"dtb:uid\" content=\"isbn\"/>\n    <meta name=\"dtb:depth\" content=\"1\"/>\n</head>\n\n<docTitle>\n    <text>Sample .epub Book</text>\n</docTitle>\n\n<navMap>\n    <navPoint id=\"chapter_1\" playOrder=\"1\">\n        <navLabel><text>Chapter 1</text></navLabel>\n        <content src=\"chapter_1.xhtml\" />\n    </navPoint>\n</navMap>\n\n<navMap>\n    <navPoint id=\"chapter_2\" playOrder=\"2\">\n        <navLabel><text>Chapter 2</text></navLabel>\n        <content src=\"chapter_2.xhtml\" />\n    </navPoint>\n</navMap>\n\n<navMap>\n    <navPoint id=\"copyright\" playOrder=\"3\">\n        <navLabel><text>Copyright</text></navLabel>\n        <content src=\"copyright.xhtml\" />\n    </navPoint>\n</navMap>\n\n</ncx>");
		f.close();
		toc_ncx.createNewFile();
		
		return null;
	}

	public File makeContainer() throws IOException {
		File container = new File("EPUB/META-INF/container.xml");
		FileWriter f = new FileWriter(container);
		// Content does NOT rely on chapters
		f.write("<?xml version=\"1.0\"?>\n<container version=\"1.0\" xmlns=\"urn:oasis:names:tc:opendocument:xmlns:container\">\n  <rootfiles>\n    <rootfile full-path=\"OEBPS/content.opf\" media-type=\"application/oebps-package+xml\"/>\n  </rootfiles>\n</container>");
		f.close();
		return container;

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
		return title;
	}
	
	public void setEpubName(String epubName) {
		this.epubName = epubName;
	}
	
	public String getEpubName() {
		return epubName;
	}
	
}
