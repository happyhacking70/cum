/**
 * 
 */
package jp.happyhacking70.cum.presSvr.audLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdRegChnl;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.DummySender;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudTest {
	static final String audName = "testAudience";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Aud Test");
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.audLyr.Aud#getAudName()}.
	 */
	@Test
	public void testGetAudName() {
		DummySender sender = new DummySender();
		Aud aud = new Aud(audName, sender);

		assertEquals(audName, aud.getAudName());

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.audLyr.Aud#sendCmd(jp.happyhacking70.cum.cmd.CmdAbst)}
	 * .
	 * 
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testSendCmdOk() throws CumExcpXMLGenFailed {
		DummySender sender = new DummySender();
		Aud aud = new Aud(audName, sender);

		NtfyCmdRegChnl cmd = new NtfyCmdRegChnl("testSession", "testChnl");
		aud.sendCmd(cmd);
		assertEquals(cmd.toXmlStr(), sender.pollCmd());
		assertNull(sender.pollCmd());
	}

}
