/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import jp.happyhacking70.cum.cmd.req.impl.ReqCmdJoinSesh;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdJoinSesh;
import jp.happyhacking70.cum.presSvr.CumTestAbst;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdXML;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl.PresSvrAdptrHdlrJoinSesh;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf;
import jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr;

import org.junit.Before;
import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresSvrAdptrHdlrJoinSeshTest extends CumTestAbst {

	/**
	 * @throws IOException
	 */
	public PresSvrAdptrHdlrJoinSeshTest() throws IOException {
		super();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		senderForAudA.clearQueue();
		senderForAudB.clearQueue();
		senderForPrestr.clearQueue();

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl.PresSvrAdptrHdlrJoinSesh#hndlCmd(jp.happyhacking70.cum.cmd.CmdAbst, jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf, jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpIllegalCmdDoc
	 * @throws CumExcpIllegalCmdXML
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testHndlCmd() throws CumExcpSeshExists, CumExcpIllegalCmdXML,
			CumExcpIllegalCmdDoc, CumExcpXMLGenFailed {
		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);

		PresSvrAdptrHdlrJoinSesh hdlr = new PresSvrAdptrHdlrJoinSesh();
		ReqCmdJoinSesh reqCmd = new ReqCmdJoinSesh(seshName, audName);
		ResCmdJoinSesh resCmd = null;
		resCmd = (ResCmdJoinSesh) hdlr.hndlCmd(reqCmd, senderForAudA, seshMgr);
		// assertNull(resCmd);
		// String resCmdXML = senderForAudA.pollCmd();
		// ResCmdJoinSesh resCmdShouldBe = new ResCmdJoinSesh(seshName, audName,
		// ResCmdJoinSesh.RsltTypes.Joined);
		//
		// assertEquals(resCmdShouldBe.toXmlStr(), resCmdXML);
		assertEquals(seshName, resCmd.getSeshName());
		assertEquals(audName, resCmd.getAudName());
		assertEquals(ResCmdJoinSesh.RsltTypes.Joined.name(), resCmd.getRslt());
	}

	@Test
	public void testHndlCmd_NOSESH() throws CumExcpSeshExists,
			CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc, CumExcpXMLGenFailed {
		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);

		PresSvrAdptrHdlrJoinSesh hdlr = new PresSvrAdptrHdlrJoinSesh();
		ReqCmdJoinSesh reqCmd = new ReqCmdJoinSesh("XXX", audName);
		ResCmdJoinSesh resCmd = null;
		resCmd = (ResCmdJoinSesh) hdlr.hndlCmd(reqCmd, senderForAudA, seshMgr);
		assertEquals(ResCmdJoinSesh.class, resCmd.getClass());
		assertEquals(resCmd.getSeshName(), "XXX");
		assertEquals(ResCmdJoinSesh.RsltTypes.NotExist.name(), resCmd.getRslt());

	}

	@Test
	public void testHndlCmd_AUDEXISTS() throws CumExcpSeshExists,
			CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc, CumExcpXMLGenFailed,
			CumExcpAudExists, CumExcpSeshNotExist {
		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);
		seshMgr.joinSesh(seshName, audName, senderForAudA);

		PresSvrAdptrHdlrJoinSesh hdlr = new PresSvrAdptrHdlrJoinSesh();
		ReqCmdJoinSesh reqCmd = new ReqCmdJoinSesh(seshName, audName);
		ResCmdJoinSesh resCmd = null;
		resCmd = (ResCmdJoinSesh) hdlr.hndlCmd(reqCmd, senderForAudA, seshMgr);
		assertEquals(ResCmdJoinSesh.class, resCmd.getClass());
		assertEquals(resCmd.getSeshName(), seshName);
		assertEquals(ResCmdJoinSesh.RsltTypes.Exists.name(), resCmd.getRslt());

	}
}
