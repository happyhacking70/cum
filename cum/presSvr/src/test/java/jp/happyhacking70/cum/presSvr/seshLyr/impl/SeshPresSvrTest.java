/**
 * 
 */
package jp.happyhacking70.cum.presSvr.seshLyr.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdClsChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdClsSesh;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdJoinChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdJoinSesh;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdLvChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdLvSesh;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdRegChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdRjctChnl;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscImg;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.DummySender;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpComError;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscNull;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class SeshPresSvrTest {
	public static final String seshName = "testSession";
	public static final String chnlName = "testChannel";
	public static final String chnlType = "testChannelType";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#getSeshName()}
	 * .
	 */
	@Test
	public void testGetSeshName() {
		SeshPresSvr sesh = new SeshPresSvr(seshName, new DummySender());
		assertEquals(sesh.getSeshName(), seshName);
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#regChnl(java.lang.String, java.util.ArrayList)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpRscExists
	 * @throws CumExcpChnlExists
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudExists
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testRegChnl_OK() throws CumExcpChnlExists, CumExcpRscExists,
			CumExcpRscNull, CumExcpChnlNotExist, CumExcpAudNotExist,
			CumExcpAudExists, CumExcpComError, CumExcpXMLGenFailed {
		DummySender senderForPresenter = new DummySender();

		DummySender senderForAudA = new DummySender();
		DummySender senderForAudB = new DummySender();

		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPresenter);

		sesh.joinSesh("audA", senderForAudA);
		sesh.joinSesh("audB", senderForAudB);

		sesh.regChnl(chnlType, chnlName, new ArrayList<ChnlRscIntf>());

		NtfyCmdRegChnl cmd = new NtfyCmdRegChnl(seshName, chnlType, chnlName);

		assertEquals(cmd.toXmlStr(), senderForAudA.pollCmd());
		assertEquals(cmd.toXmlStr(), senderForAudB.pollCmd());

		// (NtfyCmdRegChnl) senderForAudA.pollCmd();
		// assertEquals(cmd.getActionName(), "RegChnl");
		// assertEquals(cmd.getChnlName(), chnlName);
		// assertEquals(cmd.getCmdType(), CmdTypes.NTFY);
		// assertEquals(cmd.getSeshName(), seshName);

		// cmd = (NtfyCmdRegChnl) senderForAudB.pollCmd();
		// assertEquals(cmd.getActionName(), "RegChnl");
		// assertEquals(cmd.getChnlName(), chnlName);
		// assertEquals(cmd.getCmdType(), CmdTypes.NTFY);
		// assertEquals(cmd.getSeshName(), seshName);
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#regChnl(java.lang.String, java.util.ArrayList)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpRscExists
	 * @throws CumExcpChnlExists
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test(expected = CumExcpChnlExists.class)
	public void testRegChnl_ChnlExists() throws CumExcpChnlExists,
			CumExcpRscExists, CumExcpRscNull, CumExcpChnlNotExist,
			CumExcpAudNotExist, CumExcpComError, CumExcpXMLGenFailed {
		SeshPresSvr sesh = new SeshPresSvr(seshName, new DummySender());
		sesh.regChnl(chnlType, chnlName, new ArrayList<ChnlRscIntf>());
		sesh.regChnl(chnlType, chnlName, new ArrayList<ChnlRscIntf>());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#regChnl(java.lang.String, java.util.ArrayList)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpRscExists
	 * @throws CumExcpChnlExists
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test(expected = CumExcpRscNull.class)
	public void testRegChnl_NulRsce() throws CumExcpChnlExists,
			CumExcpRscExists, CumExcpRscNull, CumExcpChnlNotExist,
			CumExcpAudNotExist, CumExcpComError, CumExcpXMLGenFailed {
		SeshPresSvr sesh = new SeshPresSvr(seshName, new DummySender());
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		rsces.add(new ChnlRscImg("rscA", null));
		rsces.add(null);
		rsces.add(new ChnlRscImg("rscB", null));

		sesh.regChnl(chnlType, chnlName, rsces);
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#regChnl(java.lang.String, java.util.ArrayList)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpRscExists
	 * @throws CumExcpChnlExists
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test(expected = CumExcpRscExists.class)
	public void testRegChnl_rscExists() throws CumExcpChnlExists,
			CumExcpRscExists, CumExcpRscNull, CumExcpChnlNotExist,
			CumExcpAudNotExist, CumExcpComError, CumExcpXMLGenFailed {
		SeshPresSvr sesh = new SeshPresSvr(seshName, new DummySender());
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		rsces.add(new ChnlRscImg("rscA", null));
		rsces.add(new ChnlRscImg("rscA", null));

		sesh.regChnl(chnlType, chnlName, rsces);
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#getRsc(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpRscExists
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscNotExist
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testGetRsc_OK() throws CumExcpChnlExists, CumExcpRscExists,
			CumExcpRscNull, CumExcpChnlNotExist, CumExcpRscNotExist,
			CumExcpComError, CumExcpXMLGenFailed {
		SeshPresSvr sesh = new SeshPresSvr(seshName, new DummySender());
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();

		ChnlRscImg a = new ChnlRscImg("rscA", null);
		ChnlRscImg b = new ChnlRscImg("rscB", null);
		rsces.add(a);
		rsces.add(b);

		sesh.regChnl(chnlType, chnlName, rsces);

		ChnlRscIntf rsc = sesh.getRsc(chnlName, "rscA");
		assertEquals(a, rsc);
		rsc = sesh.getRsc(chnlName, "rscB");
		assertEquals(b, rsc);

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#getRsc(java.lang.String, java.lang.String)}
	 * 
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcptNullRsces
	 * @throws CumExcpRscNull
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 * @throws CumExcpRscNotExist
	 */
	@Test
	public void testGetRsc_NoRsc() throws CumExcpChnlExists, CumExcpRscExists,
			CumExcpRscNull, CumExcpChnlNotExist, CumExcpComError,
			CumExcpXMLGenFailed {
		SeshPresSvr sesh = new SeshPresSvr(seshName, new DummySender());
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();

		ChnlRscImg a = new ChnlRscImg("rscA", null);
		ChnlRscImg b = new ChnlRscImg("rscB", null);
		rsces.add(a);
		rsces.add(b);

		sesh.regChnl(chnlType, chnlName, rsces);

		boolean ok = false;
		try {
			sesh.getRsc(chnlName, "NORSC");
		} catch (CumExcpRscNotExist e) {

			assertEquals(e.getRscName(), "NORSC");
			assertEquals(e.getChnlName(), chnlName);
			assertEquals(e.getSeshName(), seshName);
			ok = true;
		}

		if (ok == false) {
			fail("CumExcpRscNotExist was not caught");
		}
	}

	@Test
	public void testGetRsc_NoChnl() throws CumExcpRscNotExist {
		SeshPresSvr sesh = new SeshPresSvr(seshName, new DummySender());

		boolean ok = false;

		try {
			sesh.getRsc("NOCHNL", "junk");
		} catch (CumExcpChnlNotExist e) {
			assertEquals(seshName, e.getSeshName());
			assertEquals("NOCHNL", e.getChnlName());
			ok = true;
		}

		if (ok == false) {
			fail("CumExcpChnlNotExist was not caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#sendChnlCmd(jp.happyhacking70.cum.cmd.CmdChnlAbst, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testSendChnlCmdToAud() throws CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpAudExists,
			CumExcpAudNotExist, CumExcpChnlNotExist, CumExcpComError,
			CumExcpXMLGenFailed {

		DummySender senderForPrestr = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPrestr);
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		DummySender senderForAud = new DummySender();
		sesh.joinSesh("audA", senderForAud);
		sesh.joinChnl(chnlName, "audA");

		NtfyCmdRegChnl cmd = new NtfyCmdRegChnl(seshName, chnlType, chnlName);
		for (ChnlRscIntf rsc : rsces) {
			cmd.addRscData(rsc);
		}
		sesh.sendChnlCmd(cmd, "audA");

		// XMLableCmdIntf cmdSent = senderForAud.pollCmd();
		// cmdSent = senderForAud.pollCmd();
		// assertEquals(cmdSent, cmd);
		senderForAud.pollCmd();
		assertEquals(cmd.toXmlStr(), senderForAud.pollCmd());

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#sendChnlCmd(jp.happyhacking70.cum.cmd.CmdChnlAbst, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testSendChnlCmdToAudNoChnl() throws CumExcpAudNotExist,
			CumExcpComError, CumExcpXMLGenFailed {

		DummySender senderForPrestr = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPrestr);
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();

		NtfyCmdRegChnl cmd = new NtfyCmdRegChnl(seshName, chnlType, "NOCHNL");

		for (ChnlRscIntf rsc : rsces) {
			cmd.addRscData(rsc);
		}
		boolean ok = false;
		try {
			sesh.sendChnlCmd(cmd, "audA");

		} catch (CumExcpChnlNotExist e) {

			assertEquals(seshName, e.getSeshName());
			assertEquals("NOCHNL", e.getChnlName());

			ok = true;
		}
		if (ok == false) {
			fail("CumExcpChnlNotExist not caught");
		}

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#sendChnlCmd(jp.happyhacking70.cum.cmd.CmdChnlAbst, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 * @throws CumExcpAudNotExist
	 */
	@Test
	public void testSendChnlCmdToAudNoAud() throws CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpAudExists,
			CumExcpChnlNotExist, CumExcpComError, CumExcpXMLGenFailed {
		DummySender senderForPrestr = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPrestr);
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		// DummySender senderForAud = new DummySender( );
		// sesh.joinSesh("audA", senderForAud);

		boolean ok = false;
		NtfyCmdRegChnl cmd = new NtfyCmdRegChnl(seshName, chnlType, chnlName);
		for (ChnlRscIntf rsc : rsces) {
			cmd.addRscData(rsc);
		}
		try {
			sesh.sendChnlCmd(cmd, "audB");
		} catch (CumExcpAudNotExist e) {
			assertEquals(seshName, e.getSeshName());
			assertEquals(chnlName, e.getChnlName());
			assertEquals("audB", e.getAudName());
			ok = true;
		}

		if (ok == false) {
			fail("CumExcpAudNotExist not caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#sendChnlCmd(jp.happyhacking70.cum.cmd.CmdChnlAbst)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testSendChnlCmdToAuds_OK() throws CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpAudExists,
			CumExcpAudNotExist, CumExcpChnlNotExist, CumExcpComError,
			CumExcpXMLGenFailed {
		DummySender senderForPrestr = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPrestr);
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		DummySender senderForAudA = new DummySender();
		sesh.joinSesh("audA", senderForAudA);
		senderForAudA.pollCmd();
		sesh.joinChnl(chnlName, "audA");

		DummySender senderForAudB = new DummySender();
		sesh.joinSesh("audB", senderForAudB);
		senderForAudB.pollCmd();
		sesh.joinChnl(chnlName, "audB");

		NtfyCmdRegChnl cmd = new NtfyCmdRegChnl(seshName, chnlType, chnlName);
		for (ChnlRscIntf rsc : rsces) {
			cmd.addRscData(rsc);
		}
		sesh.sendChnlCmd(cmd);

		assertEquals(cmd.toXmlStr(), senderForAudA.pollCmd());
		assertNull(senderForAudA.pollCmd());
		assertEquals(cmd.toXmlStr(), senderForAudB.pollCmd());
		assertNull(senderForAudB.pollCmd());

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#sendChnlCmd(jp.happyhacking70.cum.cmd.CmdChnlAbst)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testSendChnlCmdToAuds_NOCHNL() throws CumExcpAudNotExist,
			CumExcpComError, CumExcpXMLGenFailed {
		DummySender senderForPrestr = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPrestr);
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();

		NtfyCmdRegChnl cmd = new NtfyCmdRegChnl(seshName, chnlType, "NOCHNL");
		for (ChnlRscIntf rsc : rsces) {
			cmd.addRscData(rsc);
		}
		boolean ok = false;
		try {
			sesh.sendChnlCmd(cmd);

		} catch (CumExcpChnlNotExist e) {

			assertEquals(seshName, e.getSeshName());
			assertEquals("NOCHNL", e.getChnlName());

			ok = true;
		}
		if (ok == false) {
			fail("CumExcpChnlNotExist not caught");
		}

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#clsChnl(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testClsChnl_OK() throws CumExcpRscExists, CumExcpChnlExists,
			CumExcpRscNull, CumExcpAudExists, CumExcpChnlNotExist,
			CumExcpAudNotExist, CumExcpComError, CumExcpXMLGenFailed {

		DummySender senderForPrestr = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPrestr);
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();

		sesh.regChnl(chnlType, chnlName, rsces);
		DummySender senderForAud = new DummySender();
		DummySender senderForAudB = new DummySender();

		sesh.joinSesh("audA", senderForAud);
		senderForAud.pollCmd();
		sesh.joinSesh("audB", senderForAudB);
		senderForAudB.pollCmd();

		sesh.joinChnl(chnlName, "audA");
		senderForAud.pollCmd();

		sesh.clsChnl(chnlName);

		NtfyCmdClsChnl cmd = new NtfyCmdClsChnl(seshName, chnlType, chnlName);
		assertEquals(cmd.toXmlStr(), senderForAud.pollCmd());
		assertNull(senderForAud.pollCmd());
		assertEquals(cmd.toXmlStr(), senderForAudB.pollCmd());
		assertNull(senderForAudB.pollCmd());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#clsChnl(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 * @throws CumExcpChnlNotExist
	 */
	@Test
	public void testClsChnl_NOCHNL() throws CumExcpAudExists, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpComError,
			CumExcpXMLGenFailed {
		DummySender senderForPrestr = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPrestr);
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();

		sesh.regChnl(chnlType, chnlName, rsces);
		DummySender senderForAud = new DummySender();

		sesh.joinSesh("audA", senderForAud);
		senderForAud.pollCmd();

		boolean ok = false;
		try {
			sesh.clsChnl("NOCHNL");
		} catch (CumExcpChnlNotExist e) {
			assertEquals(seshName, e.getSeshName());
			assertEquals("NOCHNL", e.getChnlName());

			ok = true;
		}

		if (ok == false) {
			fail("CumExcpChnlNotExist not caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#joinSesh(java.lang.String, jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testJoinSesh_OK() throws CumExcpRscExists, CumExcpChnlExists,
			CumExcpRscNull, CumExcpAudExists, CumExcpComError,
			CumExcpXMLGenFailed {
		DummySender senderForPrestr = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPrestr);
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		DummySender senderForAudA = new DummySender();
		sesh.joinSesh("audA", senderForAudA);

		NtfyCmdRegChnl cmd = new NtfyCmdRegChnl(seshName, chnlType, chnlName);

		assertEquals(cmd.toXmlStr(), senderForAudA.pollCmd());
		assertNull(senderForAudA.pollCmd());

		NtfyCmdJoinSesh cmdP = new NtfyCmdJoinSesh(seshName, "audA");
		assertEquals(cmdP.toXmlStr(), senderForPrestr.pollCmd());
		assertNull(senderForPrestr.pollCmd());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#joinSesh(java.lang.String, jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testJoinSesh_AUDEXISTS() throws CumExcpAudExists,
			CumExcpRscExists, CumExcpChnlExists, CumExcpRscNull,
			CumExcpComError, CumExcpXMLGenFailed {

		DummySender senderForPrestr = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPrestr);
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		DummySender senderForAudA = new DummySender();
		sesh.joinSesh("audA", senderForAudA);

		boolean ok = false;
		try {
			sesh.joinSesh("audA", senderForAudA);
		} catch (CumExcpAudExists e) {
			assertEquals(seshName, e.getSeshName());
			assertEquals("", e.getChnlName());
			assertEquals("audA", e.getAudName());
			ok = true;
		}

		if (ok == false) {
			fail("CumExcpAudExists not caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#joinChnl(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testJoinChnl_OK() throws CumExcpRscExists, CumExcpChnlExists,
			CumExcpRscNull, CumExcpAudExists, CumExcpAudNotExist,
			CumExcpChnlNotExist, CumExcpComError, CumExcpXMLGenFailed {
		DummySender senderForPrestr = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPrestr);
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		DummySender senderForAudA = new DummySender();
		sesh.joinSesh("audA", senderForAudA);
		senderForPrestr.pollCmd();
		senderForAudA.pollCmd();

		sesh.joinChnl(chnlName, "audA");

		NtfyCmdJoinChnl cmd = new NtfyCmdJoinChnl(seshName, chnlType, chnlName,
				"audA");
		assertEquals(cmd.toXmlStr(), senderForPrestr.pollCmd());
		assertNull(senderForPrestr.pollCmd());

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#joinChnl(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testJoinChnl_NOCHNL() throws CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpAudExists,
			CumExcpAudNotExist, CumExcpComError, CumExcpXMLGenFailed {

		DummySender senderForPrestr = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPrestr);
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		DummySender senderForAudA = new DummySender();
		sesh.joinSesh("audA", senderForAudA);
		senderForPrestr.pollCmd();
		senderForAudA.pollCmd();

		boolean ok = false;
		try {
			sesh.joinChnl("NOCHNL", "audA");
		} catch (CumExcpChnlNotExist e) {
			assertEquals(seshName, e.getSeshName());
			assertEquals("NOCHNL", e.getChnlName());
			ok = true;
		}
		if (ok == false) {
			fail("CumExcpChnlNotExist no caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#joinChnl(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 * @throws CumExcpAudNotExist
	 */
	@Test
	public void testJoinChnl_NOAUD() throws CumExcpAudExists,
			CumExcpChnlNotExist, CumExcpRscExists, CumExcpChnlExists,
			CumExcpRscNull, CumExcpComError, CumExcpXMLGenFailed {

		DummySender senderForPrestr = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPrestr);
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		DummySender senderForAudA = new DummySender();
		sesh.joinSesh("audA", senderForAudA);
		senderForPrestr.pollCmd();
		senderForAudA.pollCmd();

		boolean ok = false;
		try {
			sesh.joinChnl(chnlName, "NOAUD");
		} catch (CumExcpAudNotExist e) {
			assertEquals(seshName, e.getSeshName());
			assertEquals(chnlName, e.getChnlName());
			assertEquals("NOAUD", e.getAudName());
			ok = true;
		}

		if (ok == false) {
			fail("CumExcpAudNotExist no caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#joinChnl(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testJoinChnl_AUDEXISTS() throws CumExcpAudNotExist,
			CumExcpChnlNotExist, CumExcpAudExists, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpComError,
			CumExcpXMLGenFailed {
		DummySender senderForPrestr = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPrestr);
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		DummySender senderForAudA = new DummySender();
		sesh.joinSesh("audA", senderForAudA);
		senderForPrestr.pollCmd();
		senderForAudA.pollCmd();

		sesh.joinChnl(chnlName, "audA");

		boolean ok = false;
		try {
			sesh.joinChnl(chnlName, "audA");
		} catch (CumExcpAudExists e) {
			assertEquals(seshName, e.getSeshName());
			assertEquals(chnlName, e.getChnlName());
			assertEquals("audA", e.getAudName());
			ok = true;
		}
		if (ok == false) {
			fail("CumExcpAudExists no caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#lvChnl(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testLvChnl() throws CumExcpRscExists, CumExcpChnlExists,
			CumExcpRscNull, CumExcpAudExists, CumExcpAudNotExist,
			CumExcpChnlNotExist, CumExcpComError, CumExcpXMLGenFailed {

		DummySender senderForPresenter = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPresenter);

		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		DummySender senderForAudA = new DummySender();
		sesh.joinSesh("audA", senderForAudA);
		senderForPresenter.pollCmd();
		senderForAudA.pollCmd();

		sesh.joinChnl(chnlName, "audA");
		senderForPresenter.pollCmd();

		sesh.lvChnl(chnlName, "audA");
		NtfyCmdLvChnl cmd = new NtfyCmdLvChnl(seshName, chnlType, chnlName,
				"audA");
		assertEquals(cmd.toXmlStr(), senderForPresenter.pollCmd());
		assertNull(senderForPresenter.pollCmd());

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#lvChnl(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testLvChnl_NOCHNL() throws CumExcpAudNotExist,
			CumExcpAudExists, CumExcpChnlNotExist, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpComError,
			CumExcpXMLGenFailed {

		DummySender senderForPresenter = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPresenter);

		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		DummySender senderForAudA = new DummySender();
		sesh.joinSesh("audA", senderForAudA);
		senderForPresenter.pollCmd();
		senderForAudA.pollCmd();

		sesh.joinChnl(chnlName, "audA");

		senderForPresenter.pollCmd();

		boolean ok = false;
		try {
			sesh.lvChnl("NOCHNL", "audA");
		} catch (CumExcpChnlNotExist e) {
			assertEquals(seshName, e.getSeshName());
			assertEquals("NOCHNL", e.getChnlName());
			ok = true;
		}

		if (ok == false) {
			fail("CumExcpChnlNotExist not caught");
		}

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#lvChnl(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testLvChnl_NOAUD() throws CumExcpChnlNotExist,
			CumExcpAudExists, CumExcpAudNotExist, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpComError,
			CumExcpXMLGenFailed {

		DummySender senderForPresenter = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPresenter);

		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		DummySender senderForAudA = new DummySender();
		sesh.joinSesh("audA", senderForAudA);
		senderForPresenter.pollCmd();
		senderForAudA.pollCmd();

		sesh.joinChnl(chnlName, "audA");

		senderForPresenter.pollCmd();

		boolean ok = false;

		try {
			sesh.lvChnl(chnlName, "NOAUD");
		} catch (CumExcpAudNotExist e) {
			assertEquals(seshName, e.getSeshName());
			assertEquals(chnlName, e.getChnlName());
			assertEquals("NOAUD", e.getAudName());
			ok = true;
		}

		if (ok == false) {
			fail("CumExcpChnlNotExist not caught");
		}

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#lvSesh(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testLvSesh() throws CumExcpRscExists, CumExcpChnlExists,
			CumExcpRscNull, CumExcpAudExists, CumExcpAudNotExist,
			CumExcpChnlNotExist, CumExcpComError, CumExcpXMLGenFailed {
		DummySender senderForPresenter = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPresenter);

		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		DummySender senderForAudA = new DummySender();
		sesh.joinSesh("audA", senderForAudA);
		senderForPresenter.pollCmd();
		senderForAudA.pollCmd();

		sesh.joinChnl(chnlName, "audA");
		senderForPresenter.pollCmd();

		sesh.lvSesh("audA");
		NtfyCmdLvSesh cmd = new NtfyCmdLvSesh(seshName, "audA");

		assertEquals(cmd.toXmlStr(), senderForPresenter.pollCmd());
		assertNull(senderForAudA.pollCmd());
		assertNull(senderForPresenter.pollCmd());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#lvSesh(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testLvSesh_NOAUD() throws CumExcpAudExists, CumExcpAudNotExist,
			CumExcpChnlNotExist, CumExcpRscExists, CumExcpChnlExists,
			CumExcpRscNull, CumExcpComError, CumExcpXMLGenFailed {
		DummySender senderForPresenter = new DummySender();
		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPresenter);

		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		DummySender senderForAudA = new DummySender();
		// sesh.joinSesh("audA", senderForAudA, sesh);
		sesh.joinSesh("audA", senderForAudA);
		senderForPresenter.pollCmd();
		senderForAudA.pollCmd();

		sesh.joinChnl(chnlName, "audA");
		senderForPresenter.pollCmd();

		boolean ok = false;
		try {
			sesh.lvSesh("NOAUD");
		} catch (CumExcpAudNotExist e) {
			assertEquals(seshName, e.getSeshName());
			assertEquals("", e.getChnlName());
			assertEquals("NOAUD", e.getAudName());
			ok = true;
		}
		if (ok == false) {
			fail("CumExcpAudNotExist not caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#clsSesh()}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testClsSesh() throws CumExcpRscExists, CumExcpChnlExists,
			CumExcpRscNull, CumExcpAudExists, CumExcpAudNotExist,
			CumExcpChnlNotExist, CumExcpXMLGenFailed {
		DummySender senderForPresenter = new DummySender();

		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPresenter);

		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		DummySender senderForAudA = new DummySender();
		sesh.joinSesh("audA", senderForAudA);
		senderForAudA.pollCmd();

		sesh.joinChnl(chnlName, "audA");

		DummySender senderForAudB = new DummySender();
		sesh.joinSesh("audB", senderForAudB);
		senderForAudB.pollCmd();

		sesh.rjctChnl(chnlName, "audB");

		sesh.clsSesh();

		NtfyCmdClsSesh cmd = new NtfyCmdClsSesh(seshName);

		assertEquals(cmd.toXmlStr(), senderForAudA.pollCmd());
		assertNull(senderForAudA.pollCmd());

		assertEquals(cmd.toXmlStr(), senderForAudB.pollCmd());
		assertNull(senderForAudB.pollCmd());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#rjctChnl(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testRjctChnl() throws CumExcpRscExists, CumExcpChnlExists,
			CumExcpRscNull, CumExcpAudExists, CumExcpChnlNotExist,
			CumExcpXMLGenFailed {
		DummySender senderForPresenter = new DummySender();

		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPresenter);

		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		DummySender senderForAudA = new DummySender();
		sesh.joinSesh("audA", senderForAudA);
		senderForAudA.pollCmd();
		senderForPresenter.pollCmd();

		sesh.rjctChnl(chnlName, "audA");
		NtfyCmdRjctChnl cmd = new NtfyCmdRjctChnl(seshName, chnlType, chnlName,
				"audA");
		assertEquals(cmd.toXmlStr(), senderForPresenter.pollCmd());

		assertNull(senderForPresenter.pollCmd());
		assertNull(senderForAudA.pollCmd());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#rjctChnl(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testRjctChnl_NOCHNL() throws CumExcpAudExists,
			CumExcpChnlNotExist, CumExcpAudNotExist, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpXMLGenFailed {
		DummySender senderForPresenter = new DummySender();

		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPresenter);

		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		DummySender senderForAudA = new DummySender();
		sesh.joinSesh("audA", senderForAudA);
		senderForAudA.pollCmd();
		senderForPresenter.pollCmd();

		boolean ok = false;
		try {
			sesh.rjctChnl("ZZZ", "audA");
		} catch (CumExcpChnlNotExist e) {
			assertEquals(seshName, e.getSeshName());
			assertEquals("ZZZ", e.getChnlName());
			ok = true;
		}

		assertNull(senderForPresenter.pollCmd());
		if (ok == false) {
			fail("CumExcpChnlNotExist not caught");

		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshPresSvr#rjctChnl(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testRjctChnl_AUD_EXISTS() throws CumExcpChnlNotExist,
			CumExcpAudExists, CumExcpRscExists, CumExcpChnlExists,
			CumExcpRscNull, CumExcpAudNotExist, CumExcpXMLGenFailed {
		DummySender senderForPresenter = new DummySender();

		SeshPresSvr sesh = new SeshPresSvr(seshName, senderForPresenter);
		ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();
		sesh.regChnl(chnlType, chnlName, rsces);

		DummySender senderForAudA = new DummySender();
		sesh.joinSesh("audA", senderForAudA);
		senderForAudA.pollCmd();
		senderForPresenter.pollCmd();
		sesh.joinChnl(chnlName, "audA");
		senderForPresenter.pollCmd();
		boolean ok = false;
		try {
			sesh.rjctChnl(chnlName, "audA");

		} catch (CumExcpAudExists e) {
			assertEquals(seshName, e.getSeshName());
			assertEquals(chnlName, e.getChnlName());
			assertEquals("audA", e.getAudName());
			ok = true;
		}
		assertNull(senderForPresenter.pollCmd());
		assertNull(senderForAudA.pollCmd());
		if (ok == false) {
			fail("CumExcpAudExists not caught");

		}
	}
	// TODO aud exists
}
