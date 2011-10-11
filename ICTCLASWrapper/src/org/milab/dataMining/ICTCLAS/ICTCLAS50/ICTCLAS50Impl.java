package org.milab.dataMining.ICTCLAS.ICTCLAS50;

import java.io.IOException;
import java.util.List;

import org.milab.dataMining.ICTCLAS.ICTCLASResult;
import org.milab.dataMining.ICTCLAS.ICTCLASWrapper;
import org.milab.dataMining.ICTCLAS.ICTCLAS50.util.Converter;

import ICTCLAS.I3S.AC.ICTCLAS50;

/**
 * A wrapper implement by invoking ICTCLAS50.
 * @author Guoquan
 *
 */
public class ICTCLAS50Impl implements ICTCLASWrapper, Constants {
	/**
	 * <p>
	 * directory of configuration file, license file, log file, and
	 * dictionaries(in "./Data").
	 * </p>
	 * <p>
	 * Default <b>"."</b>.
	 * </p>
	 */
	private String dir = ".";
	/**
	 * <p>
	 * whether pos tag enable.
	 * </p>
	 * <p>
	 * Default <b>true</b>.
	 * </p>
	 */
	private boolean isPosTagged = true;
	/**
	 * <p>
	 * pos map used for pos tag. Null value for using configure file setting.
	 * </p>
	 * <p>
	 * Default <b>null</b>.
	 * </p>
	 */
	private PosMap posMap = null;
	/**
	 * <p>
	 * user dictionary path. Null value for loading no additional user
	 * dictionary, except saved one if "UserDict" in "configure.xml" is on.
	 * </p>
	 * <p>
	 * Default <b>null</b>.
	 * </p>
	 */
	private String userDict = null;
	/**
	 * <p>
	 * code type used.
	 * </p>
	 * <p>
	 * Default <b>{@link CodeType.DEFAULT}</b>.
	 * </p>
	 */
	private CodeType codeType = CodeType.DEFAULT;
	/**
	 * <p>
	 * ResultHelper to generate result object from bytes results.
	 * </p>
	 * <p>
	 * Default <b>new {@link ResultHelper}</b>.
	 * </p>
	 */
	private ResultHelper helper = new ResultHelper();

	/**
	 * Lazy constructor, doing nothing.
	 */
	public ICTCLAS50Impl() {
		// nothing to be done
	}
	
	/**
	 * Full constructor using all fields. Null if default value is prefer.
	 * @param dir {@link #dir}
	 * @param isPosTagged {@link #isPosTagged}
	 * @param posMap {@link #posMap}
	 * @param userDict {@link #userDict}
	 * @param codeType {@link #codeType}
	 * @param helper {@link #helper}
	 */
	public ICTCLAS50Impl(String dir, Boolean isPosTagged, PosMap posMap,
			String userDict, CodeType codeType, ResultHelper helper) {
		this.dir = dir;
		this.isPosTagged = isPosTagged;
		this.posMap = posMap;
		this.userDict = userDict;
		this.codeType = codeType;
		this.helper = helper;
	}

	/**
	 * The split method implementing wrapper interface by ICTCLAS50.  
	 */
	public List<ICTCLASResult> split(String source) throws IOException {
		// create instance
		ICTCLAS50 instance = new ICTCLAS50();
		
		// initialize
		if (!instance.ICTCLAS_Init(Converter.stringToSystemBytes(dir))) {
			throw new IOException("\"" + dir+ "\" is not valid config directory.");
		}
		
		// if posMap is defined, use the new value
		if(null != posMap)
			instance.ICTCLAS_SetPOSmap(POS_MAP_IDS[posMap.ordinal()]);

		// if userDict is defined, load it.
		// user dictionary file format:
		//  * a word a line
		//  * use "@@" between word and its pos string, e.g. "中科院@@nr"
		//  * pos string is an option
		if(null != userDict && !userDict.isEmpty())
		{
			instance.ICTCLAS_ImportUserDictFile(Converter.stringToSystemBytes(userDict), CodeType.UNKNOWN.ordinal());
		}

		// ensure codeType is available
		if(null == codeType)
			codeType = CodeType.DEFAULT;
		
		// process, save bytes results
		byte nativeBytes[] = instance.nativeProcAPara(Converter.stringToBytes(source, codeType), codeType.ordinal(), isPosTagged ? 1 : 0);

		// save user dictionary is used. necessary?
		// generate/overwrite UserDict.map、UserDict.pos、UserDict.pdat. can load by
		// configuring "configure.xml" 
		//if(null != userDict && !userDict.isEmpty())
		//	instance.ICTCLAS_SaveTheUsrDic();
		
		// release ICTCLAS.
		instance.ICTCLAS_Exit();
		
		// return result list process by ResultHelper
		return helper.bytesToResults(this, source, nativeBytes);
	}

	/**
	 * <p>
	 * Import and save user dictionary. If "UserDict" in "configure.xml" is on,
	 * ICTCLAS50 will load this user dictionary automatically.
	 * </p>
	 * <p>
	 * ICTCLAS50 user dictionary contains lines for each word who is optionally
	 * followed by "@@" than the word's position, e.g.:
	 * <pre>
	 * 四川大学@@nr
	 * 机器智能实验室@@nr
	 * </pre>
	 * </p>
	 * 
	 * @param userDictPath
	 *            path to the dictionary to be imported.
	 * @throws IOException
	 *             if fail to initial ICTCLAS50 (failed to load license or
	 *             configure file) or user dictionary is no accessible.
	 */
	public void saveUserDict(String userDictPath) throws IOException {
		// create instance
		ICTCLAS50 instance = new ICTCLAS50();
		
		// initialize
		if (!instance.ICTCLAS_Init(Converter.stringToSystemBytes(dir))) {
			throw new IOException("\"" + dir+ "\" is not valid config directory.");
		}
		
		// if userDict is defined, load it.
		// user dictionary file format:
		//  * a word a line
		//  * use "@@" between word and its pos string, e.g. "中科院@@nr"
		//  * pos string is an option
		if(null != userDict && !userDict.isEmpty())
		{
			instance.ICTCLAS_ImportUserDictFile(Converter.stringToSystemBytes(userDict), CodeType.UNKNOWN.ordinal());
		}
		
		if(null != userDict && !userDict.isEmpty())
			instance.ICTCLAS_SaveTheUsrDic();
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public boolean isPosTagged() {
		return isPosTagged;
	}

	public void setPosTagged(boolean isPosTagged) {
		this.isPosTagged = isPosTagged;
	}

	public PosMap getPosMap() {
		return posMap;
	}

	public void setPosMap(PosMap posMap) {
		this.posMap = posMap;
	}

	public String getUserDict() {
		return userDict;
	}

	public void setUserDict(String userDict) {
		this.userDict = userDict;
	}

	public CodeType getCodeType() {
		return codeType;
	}

	public void setCodeType(CodeType codeType) {
		if(null != codeType)
			this.codeType = codeType;
	}

	public ResultHelper getHelper() {
		return helper;
	}

	public void setHelper(ResultHelper helper) {
		this.helper = helper;
	}
}
