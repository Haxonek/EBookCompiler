EBook Compiler
=
Welcome to my EBook Compiler, it's intended to be an easy solution to creating .epub files.  It's in early production and currently only translates .txt files representing each chapter into an .epub, however more usability is expected in the future.

File Structure
-
The source is of course located in /src, however there is an example ebook written in /bin/title.epub, as well as the source text (Lorem Ipsum) in the folder /bin/example.  Lastly when you create an ebook, it also creates the files compressed inside the ebook and places them in a folder titles EPUB.  If you would like to make changes, you may do so in that folder and then simply re-zip and rename the file using the .epub extension.

Getting started - compiling an .epub from .txt files
-
Although at the time of writing this I have yet to upload the .jar file, you may compile and run the program as you normally would.

In the working directory, you'll want to run 

	$ java EBook example/text_1.txt example/text_2.txt example/text_3.txt

Or whatever the path to the correct files are, the names of course don't matter.  You'll then be prompted for a Title for your book, and then the authors name.  These will of course be included in the books metadata.

Using this in another program
-
Although at the time of writing this I have yet to upload the .jar file, you may compile and run the program as you normally would.

You'll want to create an EBook object by either setting the title, author and filename at start or setting them later.  You may create the ebook with no author name or title, however it is not recommended and I have not yet tested those edge cases.

You will then want to run the createEbook(String []files), which should contain a list of the locations of your txt files.

So say you have a string array, you'll run:

	String fn = {"example/text_1.txt", "example/text_2.txt", "example/text_3.txt"};

	EBook eb = new EBook("Title", "Author", "filename");
	eb.createEbook(fn);

And that should be it!  This is still in development, however, so everything is not perfect but I hope you enjoy it so far. :)