package org.milab.dataMining.ICTCLAS.ICTCLAS50;

import org.milab.dataMining.ICTCLAS.ICTCLASResult;

/**
 * The Result class for ICTCLAS50.
 * @author Guoquan
 *
 */
public class Result implements ICTCLASResult{
	/**
	 * byte-wise start position of split word 
	 */
	private int byteStart;
	/**
	 * byte-wise word length of split word 
	 */
	private int byteLength;
	/**
	 * pos string of word
	 */
	private String posStr;
	/**
	 * pos id of word
	 */
	private int posId;
	/**
	 * word id
	 */
	private int wordId;
	/**
	 * whether this is a word from user dictionary
	 */
	private boolean isUserDict;
	/**
	 * weight of word
	 */
	private int weight;
	
	/**
	 * Copy constructor of the class.
	 * @param result the result to be copied
	 */
	public Result(Result result) {
		this.byteStart = result.getByteStart();
		this.byteLength = result.getByteLength();
		this.posStr = result.getPosStr();
		this.posId = result.getPosId();
		this.wordId = result.getWordId();
		this.isUserDict = result.isUserDict();
		this.weight = result.getWeight();
	}
	
	/**
	 * Full constructor of the class.
	 * @param byteStart {@link #byteStart}
	 * @param byteLength {@link #byteLength}
	 * @param posStr {@link #posStr}
	 * @param posId {@link #posId}
	 * @param wordId {@link #wordId}
	 * @param isUserDict {@link #isUserDict}
	 * @param weight {@link #weight}
	 */
	public Result(int byteStart, int byteLength, String posStr, int posId, int wordId,
			boolean isUserDict, int weight) {
		this.byteStart = byteStart;
		this.byteLength = byteLength;
		this.posStr = posStr;
		this.posId = posId;
		this.wordId = wordId;
		this.isUserDict = isUserDict;
		this.weight = weight;
	}

	/**
	 * Return a string with information of the result. 
	 * @return string with all information of the result, including
	 *         {@link #byteStart}, {@link #byteLength}, {@link #posStr},
	 *         {@link #wordId}, {@link #isUserDict}, {@link #weight}.
	 */
	public String toString() {
		return "start=" + this.byteStart + 
				", length=" + this.byteLength + 
				", pos=" + this.posStr + 
				", word=" + this.wordId + 
				", user=" + this.isUserDict + 
				", weight="	+ this.weight + "";
	}

	public int getByteStart() {
		return byteStart;
	}

	public void setByteStart(int start) {
		this.byteStart = start;
	}

	public int getByteLength() {
		return byteLength;
	}

	public void setByteLength(int length) {
		this.byteLength = length;
	}

	public String getPosStr() {
		return posStr;
	}

	public void setPosStr(String posStr) {
		this.posStr = posStr;
	}

	public int getPosId() {
		return posId;
	}

	public void setPosId(int posId) {
		this.posId = posId;
	}

	public int getWordId() {
		return wordId;
	}

	public void setWordId(int wordId) {
		this.wordId = wordId;
	}

	public boolean isUserDict() {
		return isUserDict;
	}

	public void setUserDict(boolean isUserDict) {
		this.isUserDict = isUserDict;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
