package org.cs560.lab7;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class Tokens {
	public  String[] getTokens(String paragraph) throws IOException{
		ClassLoader classLoader = getClass().getClassLoader();
		TokenizerModel tm=new TokenizerModel(new FileInputStream(classLoader.getResource("en-token.bin").getFile()));
		TokenizerME t=new TokenizerME(tm);
		String[] tokens=t.tokenize(paragraph);
		return tokens;
	}

}
