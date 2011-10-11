package org.milab.dataMining.ICTCLAS.ICTCLAS50;

/**
 * Result with original word
 * @author Guoquan
 *
 */
public class WordResult extends Result {
	/**
	 * original word
	 */
	private String word;

	/**
	 * Construct from a result together with original {@link #word}.
	 * @param result
	 * the result of the word.
	 * @param word
	 * the original {@link #word} string.
	 */
	public WordResult(Result result, String word) {
		super(result);
		this.word = word;
	}

	/**
	 * Append original {@link #word} string in result.
	 */
	public String toString() {
		return super.toString() + ", word="	+ this.word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}

	public String getWord() {
		return word;
	}
}
