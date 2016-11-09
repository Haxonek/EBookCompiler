import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OverHead {

	private String title, author, epubName;
	
	public OverHead() {
		this("","");
	}

	public OverHead(String title, String author) {
		this.title = title;
		this.author = author;
		this.epubName = title.trim();
	}
	
	public File makeCSS() throws IOException {
		File css = new File("h1 {text-align: center;}");
		return css;
	}
	
	public File makeContent() throws IOException {
		return null;
	}
	
	public File makeToc() throws IOException {
		
		File package_opf = new File("EPUB/EPUB/package.opf");
		FileWriter f = new FileWriter(package_opf);
		f.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<package xmlns=\"http://www.idpf.org/2007/opf\" version=\"3.1\" xml:lang=\"en\" unique-identifier=\"pub-id\"\n  prefix=\"dc: http://purl.org/dc/elements/1.1/\">\n\n  <metadata xmlns:dc=\"http://purl.org/dc/elements/1.1/\">\n\n    <dc:title id=\"title\">Moby-Dick</dc:title>\n    <dc:creator id=\"creator\" file-as=\"Melville, Herman\">Herman Melville</dc:creator>\n    <dc:identifier id=\"pub-id\">idpf.epub31.samples.moby-dick.xhtml</dc:identifier>\n    <dc:language>en-US</dc:language>\n    <meta property=\"dcterms:modified\">2016-02-05T14:40:00Z</meta>\n\n    <dc:publisher>Harper &amp; Brothers, Publishers</dc:publisher>\n    <meta property=\"dc:contributor\">Dave Cramer</meta>\n\n    <!--MEDIA OVERLAY METADATA-->\n    <meta property=\"media:duration\">0:23:23.500</meta>\n    <meta property=\"media:narrator\">Stuart Wills</meta>\n\n    <meta property=\"media:active-class\">-epub-media-overlay-active</meta>\n  </metadata>\n  <manifest>\n    <item id=\"font.stix.regular\" href=\"fonts/STIXGeneral.otf\" media-type=\"application/vnd.ms-opentype\"/>\n    <item id=\"font.stix.italic\" href=\"fonts/STIXGeneralItalic.otf\" media-type=\"application/vnd.ms-opentype\"/>\n    <item id=\"font.stix.bold\" href=\"fonts/STIXGeneralBol.otf\" media-type=\"application/vnd.ms-opentype\"/>\n    <item id=\"font.stix.bold.italic\" href=\"fonts/STIXGeneralBolIta.otf\" media-type=\"application/vnd.ms-opentype\"/>\n\n    <item id=\"chapter_001_overlay\" href=\"chapter_001_overlay.smil\" duration=\"0:14:20.500\" media-type=\"application/smil+xml\"/>\n    <item id=\"chapter_002_overlay\" href=\"chapter_002_overlay.smil\" duration=\"0:09:03.000\" media-type=\"application/smil+xml\"/>\n\n    <item id=\"chapter_001_audio\" href=\"audio/mobydick_001_002_melville.mp4\" media-type=\"audio/mp4\"/>\n\n    <item id=\"cover-image\" properties=\"cover-image\" href=\"images/9780316000000.jpg\" media-type=\"image/jpeg\"/>\n\n    <item id=\"style\" href=\"css/stylesheet.css\" media-type=\"text/css\"/>\n\n    <item id=\"toc\" properties=\"nav\" href=\"toc.xhtml\" media-type=\"application/xhtml+xml\"/>\n    <item id=\"copyright\" href=\"copyright.xhtml\" media-type=\"application/xhtml+xml\"/>\n    <item id=\"titlepage\" href=\"titlepage.xhtml\" media-type=\"application/xhtml+xml\"/>\n    <item id=\"cover\" href=\"cover.xhtml\" media-type=\"application/xhtml+xml\"/>\n    <item id=\"aMoby-Dick_FE_title_page\" href=\"images/Moby-Dick_FE_title_page.jpg\" media-type=\"image/jpeg\"/>\n    <item id=\"xpreface_001\" href=\"preface_001.xhtml\" media-type=\"application/xhtml+xml\"/>\n    <item id=\"xintroduction_001\" href=\"introduction_001.xhtml\" media-type=\"application/xhtml+xml\"/>\n    <item id=\"xepigraph_001\" href=\"epigraph_001.xhtml\" media-type=\"application/xhtml+xml\"/>\n    <item id=\"xchapter_001\" href=\"chapter_001.xhtml\" media-type=\"application/xhtml+xml\" media-overlay=\"chapter_001_overlay\"/>\n    <item id=\"xchapter_002\" href=\"chapter_002.xhtml\" media-type=\"application/xhtml+xml\" media-overlay=\"chapter_002_overlay\"/>\n    <item id=\"xchapter_003\" href=\"chapter_003.xhtml\" media-type=\"application/xhtml+xml\"/>\n    <item id=\"xchapter_004\" href=\"chapter_004.xhtml\" media-type=\"application/xhtml+xml\"/>\n    <item id=\"xchapter_005\" href=\"chapter_005.xhtml\" media-type=\"application/xhtml+xml\"/>\n    <item id=\"brief-toc\" href=\"toc-short.xhtml\" media-type=\"application/xhtml+xml\"/>\n\n    <!-- \n    <item id=\"ncx\" href=\"toc.ncx\" media-type=\"application/x-dtbncx+xml\"/>\n -->\n\n  </manifest>\n  <spine>\n    <itemref idref=\"cover\" linear=\"no\"/>\n    <itemref idref=\"titlepage\" linear=\"yes\"/>\n    <itemref idref=\"brief-toc\" linear=\"yes\"/>\n    <itemref linear=\"yes\" idref=\"xpreface_001\"/>\n    <itemref linear=\"yes\" idref=\"xintroduction_001\"/>\n    <itemref linear=\"yes\" idref=\"xepigraph_001\"/>\n    <itemref linear=\"yes\" idref=\"xchapter_001\"/>\n    <itemref linear=\"yes\" idref=\"xchapter_002\"/>\n    <itemref linear=\"yes\" idref=\"xchapter_003\"/>\n    <itemref linear=\"yes\" idref=\"xchapter_004\"/>\n    <itemref linear=\"yes\" idref=\"xchapter_005\"/>\n    <itemref idref=\"copyright\" linear=\"yes\"/>\n    <itemref idref=\"toc\" linear=\"no\"/>\n  </spine>\n</package>\n");
		f.close();
		package_opf.createNewFile();
		
		return null;
	}
	
	public File makeTox() throws IOException {
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
	
}
