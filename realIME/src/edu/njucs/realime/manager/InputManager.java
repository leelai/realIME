package edu.njucs.realime.manager;

import java.io.FileDescriptor;
import java.util.List;

import edu.njucs.realime.languagemodel.Candidate;
import edu.njucs.realime.languagemodel.StaticLanguageModel;
import edu.njucs.realime.lexicon.LexiconTree;

public class InputManager {
	private static InputManager instance=new InputManager();
	private LexiconTree lexicon;
    private StaticLanguageModel languageModel;
    
    static
    {
    	System.loadLibrary("language-model");
    }
	
	private InputManager()
	{
		
	}
	
	public static InputManager getInstance()
	{
		return instance;
	}

	public void setLexicon(LexiconTree lexicon) {
		this.lexicon = lexicon;
	}

	public void setLanguageModel(StaticLanguageModel languageModel) {
		this.languageModel = languageModel;
	}
	
	public List<String> split(String input)
	{
		return this.lexicon.split(input);
	}
	
	public List<Candidate> getAllCandidates(List<String> input)
	{
		return languageModel.getAllCandidates(input);
	}
	
	 /** Native methods, implemented in jni folder */
    public static native int test(FileDescriptor fd, long off, long 
    		len); 
    
    public int testJni(FileDescriptor fd,long off,long len)
    {
    	return test(fd, off, len);
    }
}
