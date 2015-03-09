package org.cs560.lab7;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;
import opennlp.uima.tokenize.Tokenizer;

public class Chunker {
	public List<String> getChunks(String paragraph) throws IOException{
		ClassLoader classLoader = getClass().getClassLoader();
		TokenizerModel tm=new TokenizerModel(new FileInputStream(classLoader.getResource("en-token.bin").getFile()));
		TokenizerME t=new TokenizerME(tm);
		POSModel pm = new POSModel(new FileInputStream(classLoader.getResource("en-pos-maxent.bin").getFile()));
	    POSTaggerME posme = new POSTaggerME(pm);
	    InputStream modelIn = new FileInputStream(classLoader.getResource("en-chunker.bin").getFile());
	    ChunkerModel chunkerModel = new ChunkerModel(modelIn);
	    ChunkerME chunkerME = new ChunkerME(chunkerModel);
		String[] tokens=t.tokenize(paragraph);
	List<String> chunkslist=new ArrayList<String>();
		String[] posTags = posme.tag(tokens);
	    Span[] chunks = chunkerME.chunkAsSpans(tokens, posTags);
	    String[] chunkStrings = Span.spansToStrings(chunks, tokens);
	    for (int i = 0; i < chunks.length; i++) {
	        if (chunks[i].getType().equals("NP")) {
	        	chunkslist.add("NP: \n\t" + chunkStrings[i]);
	        }
	}
	    return chunkslist;
	    }
}
