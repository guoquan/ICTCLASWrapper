package org.milab.dataMining.ICTCLAS.ICTCLAS50;

import java.nio.charset.Charset;

/**
 * Constants used in ICTCLAS50.
 * @author Guoquan
 *
 */
public interface Constants {
	/**
	 * size of a single result in byte
	 */
	public static final int RESULT_SIZE = 32;
	
	/**
	 * number of pos map
	 */
	public static final int POS_MAP_NUMBER = 4;

	/**
	 * max size of pos string, in byte
	 */
	public static final int POS_SIZE = 8;
	
	/**
	 * enum type of code types(charsets) available or used
	 * @author Guoquan
	 *
	 */
	public enum CodeType {
		/**
		 * unknown code type
		 */
		UNKNOWN,
		/**
		 * ASCII
		 */
		ASCII,
		/**
		 * GB2312, GBK, or GB10380
		 */
		GB,
		/**
		 * UTF-8
		 */
		UTF8,
		/**
		 * BIG5, for traditional Chinese
		 */
		BIG5,
		/**
		 * System specified default
		 */
		DEFAULT,
		/**
		 * code type used amount ICTCLAS50 internal invokes
		 */
		SYSTEM
	}
	
	/**
	 * map enum ordinal of code types to {@link java.nio.charset.Charset}
	 */
	public static Charset[] CHARSETS = {
		null,
		Charset.forName("ASCII"),
		Charset.forName("GBK"),
		Charset.forName("UTF8"),
		Charset.forName("BIG5"),
		Charset.defaultCharset(),
		Charset.forName("GBK")
	};
	
	/**
	 * pos maps available
	 * @author Guoquan
	 *
	 */
	public enum PosMap {
		/**
		 * Secondary pos map from ICT
		 */
		ICT_POS_MAP_SECOND,
		/**
		 * First pos map from ICT
		 */
		ICT_POS_MAP_FIRST,
		/**
		 * Secondary pos map from PKU
		 */
		PKU_POS_MAP_SECOND,
		/**
		 * First pos map from PKU
		 */
		PKU_POS_MAP_FIRST
	}
	
	/**
	 * map enum ordinal of pos map to id specified by ICTCLAS50
	 */
	public static int[] POS_MAP_IDS = {
		0,
		1,
		2,
		3
	};
}
