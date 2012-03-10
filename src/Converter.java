/*
The MIT License

Copyright (c) 2012 Ludwik Trammer <ludwik at trammer.pl>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Converter {	
	/** 
	 * Given an HTML file creates a file with <font face="Symbol">
	 * encoded characters converted to proper Unicode.
	 * 
	 * @param input name of the input HTML file. Character encoding
	 * 		will be determined from the HTML meta tag (thanks jsoup!)
	 * @param output where to save the output
	 * @param saveAsUTF8 if true will be saved as an UTF-8 file (with proper meta tags),
	 * 			if false file's encoding will be kept intact.
	 */
	public static void convertFile(File input, File output, boolean saveAsUTF8) {
		try {
			Document doc = Jsoup.parse(input, null);
			
			/* run fintAndConvertAllTextNodes() for every
			 * <font face="Symbol"> on a page
			 */
			Elements fontFaces = doc.select("font[face=Symbol]");
			for(Element fontFace : fontFaces) {
				findAndConvertAllTextNodes(fontFace);
				fontFace.unwrap();
			}
			
			if(saveAsUTF8) {
				doc.outputSettings().charset("UTF-8");
				doc.select("meta[http-equiv=Content-Type]").remove();
				doc.head().prependElement("meta")
					.attr("http-equiv", "Content-Type")
					.attr("content", "text/html; charset=UTF-8");
			}
			
			/* There is a bug in jsoup resulting in corruption of
			 * "sup1", "sup2" and "sup3" HTML entities.
			 * This is a dirty workaround that reverses the corruption.
			 */
			String outString = doc.html().replaceAll("(?:\u2283|&#8835;)([1-3]);", "&sup$1;");
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), doc.outputSettings().charset()));
			bw.write(outString);
			bw.close();			
			
		} catch (IOException e) {
			System.err.println("Problem reading/writing the file.");
		}
	}
	
	public static void convertFile(File input, File output) {
		convertFile(input, output, false);
	}
	
	/** 
	 * @param element Run fintAndConvertAllTextNodes() on all child nodes
	 * of the element
	 */
	private static void findAndConvertAllTextNodes(Element element) {
		for(Node child : element.childNodes()) {
			findAndConvertAllTextNodes(child);
		}
	}
	
	/**
	 * Recursive function that find all text
	 * in a given node and it's child nodes
	 * and converts it using symbolFontToUnicode() function.
	 * 
	 * @param n parent node that will be checked
	 */
	private static void findAndConvertAllTextNodes(Node n) {
		if (n instanceof TextNode) {
			String newText = symbolFontToUnicode(((TextNode)n).attr("text"));
			((TextNode)n).attr("text",newText);
		} else if(!n.childNodes().isEmpty()) {
			if(n.nodeName()=="font" && n.hasAttr("face")) {
				/* Font face changed.
				 * This node doesn't concern us anymore.
				 */
				return;
			}
			
			for(Node child : n.childNodes()) {
				findAndConvertAllTextNodes(child);
			}
		}
	}
	
	/**
	 * Checks every character in a String and replaces characters used 
	 * as symbols in "Symbol" font with proper Unicode characters.
	 * 
	 * @param text String to be converted. Must be a <font face="Symbol"> encoded text.
	 */
	private static String symbolFontToUnicode(String text) {
		StringBuilder sb = new StringBuilder(text.length());
		for(char c : text.toCharArray()) {
			if(CharacterMap.map.containsKey(c)) {
				sb.append(CharacterMap.map.get(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	


}
