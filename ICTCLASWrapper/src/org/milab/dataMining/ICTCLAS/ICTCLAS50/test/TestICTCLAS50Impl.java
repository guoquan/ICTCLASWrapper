/**
 * 
 */
package org.milab.dataMining.ICTCLAS.ICTCLAS50.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.milab.dataMining.ICTCLAS.ICTCLASResult;
import org.milab.dataMining.ICTCLAS.ICTCLASWrapper;
import org.milab.dataMining.ICTCLAS.ICTCLAS50.ICTCLAS50Impl;
import org.milab.dataMining.ICTCLAS.ICTCLAS50.WordResultHelper;

/**
 * @author Guoquan
 *
 */
public class TestICTCLAS50Impl {
	private static ICTCLASWrapper wrapper;
	public static final String TESTSOURCE = "帮助您手上的 iPhone 4 进行更新、越狱。手上有 iPhone 4 的朋友有福了。使用 Picasa 与 Flicker 储存、分享相片给他人，永久记录自己的成长历程。可以真正地隐藏自己的 IP 地址，达到最好的网上匿名效果。可以使用全套的 Google 服务，包括线上办公软体 Google Docs 和查看网页快照（经常 Google 搜到却不能显示的朋友有福了）。可以登记、使用 Blogger 与 MySpace 网站的服务，拥有属于自己的永久免费博客。可以登上 Facebook 与 Twitter 全球最大的交友网站，与全球超过 5 亿的朋友为邻。利用他国的 IP 地址，申请一些必须在本土才能申请的服务（例如 Google Voice 语音服务）。如果你身处一个公共网路环境，可以利用 VPN 加密保证上网资料不被任何人窃取。能够在 Android 的 Market （市场）上下载软体程式。支援 MSN 登录，他人看上去就像在美国本土上 MSN 一样。可以观看 YouTube 的视频，并可上载自己的短片与亲朋好友分享。还有更多更多好玩的，坐上开心直通车，我们一起来玩吧！";
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ICTCLAS50Impl instance = new ICTCLAS50Impl();
		instance.setDir("./config/");
		instance.setHelper(new WordResultHelper());
		wrapper = instance;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.milab.dataMining.ICTCLAS.ICTCLAS50.ICTCLAS50Impl#split(java.lang.String)}.
	 */
	@Test
	public void testSplit() {
		try {
			List<ICTCLASResult> results = wrapper.split(TESTSOURCE);
			assertNotNull(results);
			assertTrue(!results.isEmpty());
			for(ICTCLASResult result : results)
			{
				assertNotNull(results);
				System.out.println(result);
			}
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
	}

}
