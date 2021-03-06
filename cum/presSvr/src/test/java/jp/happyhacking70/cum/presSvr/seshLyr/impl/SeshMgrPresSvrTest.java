/**
 * 
 */
package jp.happyhacking70.cum.presSvr.seshLyr.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdClsChnl;
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
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshNotExist;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class SeshMgrPresSvrTest {
	protected static String seshName = "testSession";
	protected static String chnlName = "testChannel";
	protected static String chnlType = "testChannelType";
	protected static String audName = "testAudience";
	protected static ArrayList<ChnlRscIntf> rsces = new ArrayList<ChnlRscIntf>();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#regSesh(java.lang.String, jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 */
	@Test
	public void testRegSesh_OK() throws CumExcpSeshExists {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#regSesh(java.lang.String, jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 */
	@Test
	public void testRegSesh_DUP() throws CumExcpSeshExists {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());

		boolean ok = false;
		try {
			seshMgr.regSesh(seshName, new DummySender());
		} catch (CumExcpSeshExists e) {
			assertEquals(seshName, e.getSeshName());
			ok = true;
		}
		if (ok == false) {
			fail("CumExcpSeshExists not caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#regChnl(java.lang.String, java.lang.String, java.util.ArrayList)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testRegChnl() throws CumExcpSeshExists, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpSeshNotExist,
			CumExcpComError, CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());

		seshMgr.regChnl(seshName, chnlType, chnlName, rsces);

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#regChnl(java.lang.String, java.lang.String, java.util.ArrayList)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testRegChnl_NOSESH() throws CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpSeshExists,
			CumExcpComError, CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());

		boolean ok = false;
		try {
			seshMgr.regChnl("NOSESH", chnlType, chnlName, rsces);
		} catch (CumExcpSeshNotExist e) {
			assertEquals("NOSESH", e.getSeshName());

			ok = true;
		}
		if (ok == false) {
			fail("CumExcpSeshNotExist not caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#clsSesh(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testClsSesh() throws CumExcpSeshExists, CumExcpSeshNotExist,
			CumExcpComError, CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		seshMgr.clsSesh(seshName);

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#clsSesh(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testClsSesh_NOSESH() throws CumExcpSeshExists,
			CumExcpSeshNotExist, CumExcpComError, CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());

		boolean ok = false;
		try {
			seshMgr.clsSesh("NOSESH");
		} catch (CumExcpSeshNotExist e) {
			assertEquals("NOSESH", e.getSeshName());

			ok = true;
		}
		if (ok == false) {
			fail("CumExcpSeshNotExist not caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#sendChnlCmd(jp.happyhacking70.cum.cmd.CmdChnlAbst, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testSendChnlCmdToAud() throws CumExcpSeshExists,
			CumExcpAudNotExist, CumExcpChnlNotExist, CumExcpSeshNotExist,
			CumExcpRscExists, CumExcpChnlExists, CumExcpRscNull,
			CumExcpAudExists, CumExcpComError, CumExcpXMLGenFailed {

		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		seshMgr.regChnl(seshName, chnlType, chnlName, rsces);
		seshMgr.joinSesh(seshName, audName, new DummySender());
		seshMgr.joinChnl(seshName, chnlName, audName);
		seshMgr.sendChnlCmd(new NtfyCmdClsChnl(seshName, chnlType, chnlName),
				audName);

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#sendChnlCmd(jp.happyhacking70.cum.cmd.CmdChnlAbst, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testSendChnlCmdToAud_NOSESH() throws CumExcpSeshExists,
			CumExcpAudNotExist, CumExcpChnlNotExist, CumExcpSeshNotExist,
			CumExcpRscExists, CumExcpChnlExists, CumExcpRscNull,
			CumExcpComError, CumExcpXMLGenFailed {

		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		seshMgr.regChnl(seshName, chnlType, chnlName, rsces);

		boolean ok = false;
		try {

			seshMgr.sendChnlCmd(
					new NtfyCmdClsChnl("NOSESH", chnlType, chnlName), audName);
		} catch (CumExcpSeshNotExist e) {
			assertEquals("NOSESH", e.getSeshName());

			ok = true;
		}
		if (ok == false) {
			fail("CumExcpSeshNotExist not caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#sendChnlCmd(jp.happyhacking70.cum.cmd.CmdChnlAbst)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testSendChnlCmdToAuds() throws CumExcpSeshExists,
			CumExcpRscExists, CumExcpChnlExists, CumExcpRscNull,
			CumExcpSeshNotExist, CumExcpAudNotExist, CumExcpChnlNotExist,
			CumExcpComError, CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		seshMgr.regChnl(seshName, chnlType, chnlName, rsces);
		seshMgr.sendChnlCmd(new NtfyCmdClsChnl(seshName, chnlType, chnlName));

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#sendChnlCmd(jp.happyhacking70.cum.cmd.CmdChnlAbst)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testSendChnlCmdToAuds_NOSESH() throws CumExcpSeshExists,
			CumExcpRscExists, CumExcpChnlExists, CumExcpRscNull,
			CumExcpSeshNotExist, CumExcpAudNotExist, CumExcpChnlNotExist,
			CumExcpComError, CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		seshMgr.regChnl(seshName, chnlType, chnlName, rsces);

		boolean ok = false;
		try {

			seshMgr.sendChnlCmd(new NtfyCmdClsChnl("NOSESH", chnlType, chnlName));
		} catch (CumExcpSeshNotExist e) {
			assertEquals("NOSESH", e.getSeshName());

			ok = true;
		}
		if (ok == false) {
			fail("CumExcpSeshNotExist not caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#clsChnl(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testClsChnl() throws CumExcpSeshExists, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpSeshNotExist,
			CumExcpChnlNotExist, CumExcpComError, CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		seshMgr.regChnl(seshName, chnlType, chnlName, rsces);
		seshMgr.clsChnl(seshName, chnlName);
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#clsChnl(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testClsChnl_NOSESH() throws CumExcpSeshExists,
			CumExcpRscExists, CumExcpChnlExists, CumExcpRscNull,
			CumExcpSeshNotExist, CumExcpChnlNotExist, CumExcpComError,
			CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		seshMgr.regChnl(seshName, chnlType, chnlName, rsces);

		boolean ok = false;
		try {
			seshMgr.clsChnl("NOSESH", chnlName);
		} catch (CumExcpSeshNotExist e) {
			assertEquals("NOSESH", e.getSeshName());

			ok = true;
		}
		if (ok == false) {
			fail("CumExcpSeshNotExist not caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#joinSesh(java.lang.String, java.lang.String, jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpAudExists
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testJoinSesh() throws CumExcpSeshExists, CumExcpAudExists,
			CumExcpSeshNotExist, CumExcpComError, CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		seshMgr.joinSesh(seshName, audName, new DummySender());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#joinSesh(java.lang.String, java.lang.String, jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpAudExists
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testJoinSesh_NOSESH() throws CumExcpSeshExists,
			CumExcpAudExists, CumExcpSeshNotExist, CumExcpComError,
			CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());

		boolean ok = false;
		try {
			seshMgr.joinSesh("NOSESH", audName, new DummySender());
		} catch (CumExcpSeshNotExist e) {
			assertEquals("NOSESH", e.getSeshName());

			ok = true;
		}
		if (ok == false) {
			fail("CumExcpSeshNotExist not caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#joinChnl(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testJoinChnl() throws CumExcpSeshExists, CumExcpAudExists,
			CumExcpSeshNotExist, CumExcpAudNotExist, CumExcpChnlNotExist,
			CumExcpRscExists, CumExcpChnlExists, CumExcpRscNull,
			CumExcpComError, CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		seshMgr.regChnl(seshName, chnlType, chnlName, rsces);
		seshMgr.joinSesh(seshName, audName, new DummySender());
		seshMgr.joinChnl(seshName, chnlName, audName);
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#joinChnl(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testJoinChnl_NOSESH() throws CumExcpSeshExists,
			CumExcpAudExists, CumExcpSeshNotExist, CumExcpAudNotExist,
			CumExcpChnlNotExist, CumExcpComError, CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		seshMgr.joinSesh(seshName, audName, new DummySender());

		boolean ok = false;
		try {
			seshMgr.joinChnl("NOSESH", chnlName, audName);
		} catch (CumExcpSeshNotExist e) {
			assertEquals("NOSESH", e.getSeshName());

			ok = true;
		}
		if (ok == false) {
			fail("CumExcpSeshNotExist not caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#rjctChnl(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testRjctChnl() throws CumExcpSeshExists, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpSeshNotExist,
			CumExcpAudExists, CumExcpChnlNotExist, CumExcpComError,
			CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		seshMgr.regChnl(seshName, chnlType, chnlName, rsces);
		seshMgr.joinSesh(seshName, audName, new DummySender());
		seshMgr.rjctChnl(seshName, chnlName, audName);
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#rjctChnl(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testRjctChnl_NOSESH() throws CumExcpSeshExists,
			CumExcpRscExists, CumExcpChnlExists, CumExcpRscNull,
			CumExcpSeshNotExist, CumExcpAudExists, CumExcpChnlNotExist,
			CumExcpComError, CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		seshMgr.regChnl(seshName, chnlType, chnlName, rsces);
		seshMgr.joinSesh(seshName, audName, new DummySender());

		boolean ok = false;
		try {
			seshMgr.rjctChnl("NOSESH", chnlName, audName);
		} catch (CumExcpSeshNotExist e) {
			assertEquals("NOSESH", e.getSeshName());

			ok = true;
		}
		if (ok == false) {
			fail("CumExcpSeshNotExist not caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#lvChnl(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
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
	public void testLvChnl() throws CumExcpSeshExists, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpSeshNotExist,
			CumExcpAudExists, CumExcpAudNotExist, CumExcpChnlNotExist,
			CumExcpComError, CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		seshMgr.regChnl(seshName, chnlType, chnlName, rsces);
		seshMgr.joinSesh(seshName, audName, new DummySender());
		seshMgr.joinChnl(seshName, chnlName, audName);

		seshMgr.lvChnl(seshName, chnlName, audName);
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#lvChnl(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
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
	public void testLvChnl_NOSESH() throws CumExcpSeshExists, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpSeshNotExist,
			CumExcpAudExists, CumExcpAudNotExist, CumExcpChnlNotExist,
			CumExcpComError, CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		seshMgr.regChnl(seshName, chnlType, chnlName, rsces);
		seshMgr.joinSesh(seshName, audName, new DummySender());
		seshMgr.joinChnl(seshName, chnlName, audName);

		boolean ok = false;
		try {
			seshMgr.lvChnl("NOSESH", chnlName, audName);
		} catch (CumExcpSeshNotExist e) {
			assertEquals("NOSESH", e.getSeshName());

			ok = true;
		}
		if (ok == false) {
			fail("CumExcpSeshNotExist not caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#lvSesh(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testLvSesh() throws CumExcpSeshExists, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpSeshNotExist,
			CumExcpAudExists, CumExcpAudNotExist, CumExcpComError,
			CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		seshMgr.regChnl(seshName, chnlType, chnlName, rsces);
		seshMgr.joinSesh(seshName, audName, new DummySender());
		seshMgr.lvSesh(seshName, audName);

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#lvSesh(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testLvSesh_NOSESH() throws CumExcpSeshExists, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpSeshNotExist,
			CumExcpAudExists, CumExcpAudNotExist, CumExcpComError,
			CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		seshMgr.regChnl(seshName, chnlType, chnlName, rsces);
		seshMgr.joinSesh(seshName, audName, new DummySender());

		boolean ok = false;
		try {
			seshMgr.lvSesh("NOSESH", audName);
		} catch (CumExcpSeshNotExist e) {
			assertEquals("NOSESH", e.getSeshName());

			ok = true;
		}
		if (ok == false) {
			fail("CumExcpSeshNotExist not caught");
		}
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#getRsc(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpRscNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testGetRsc() throws CumExcpSeshExists, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpSeshNotExist,
			CumExcpRscNotExist, CumExcpChnlNotExist, CumExcpComError,
			CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		ArrayList<ChnlRscIntf> chnlRsces = new ArrayList<ChnlRscIntf>();
		chnlRsces.add(new ChnlRscImg("testRsc", null));

		seshMgr.regChnl(seshName, chnlType, chnlName, chnlRsces);

		seshMgr.getRsc(seshName, chnlName, "testRsc");

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr#getRsc(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpRscNotExist
	 * @throws CumExcpComError
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testGetRsc_NOSESH() throws CumExcpSeshExists, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpSeshNotExist,
			CumExcpRscNotExist, CumExcpChnlNotExist, CumExcpComError,
			CumExcpXMLGenFailed {
		SeshMgrPresSvr seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, new DummySender());
		ArrayList<ChnlRscIntf> chnlRsces = new ArrayList<ChnlRscIntf>();
		chnlRsces.add(new ChnlRscImg("testRsc", null));

		seshMgr.regChnl(seshName, chnlType, chnlName, chnlRsces);

		boolean ok = false;
		try {
			seshMgr.getRsc("NOSESH", chnlName, "testRsc");
		} catch (CumExcpSeshNotExist e) {
			assertEquals("NOSESH", e.getSeshName());

			ok = true;
		}
		if (ok == false) {
			fail("CumExcpSeshNotExist not caught");
		}
	}
}
