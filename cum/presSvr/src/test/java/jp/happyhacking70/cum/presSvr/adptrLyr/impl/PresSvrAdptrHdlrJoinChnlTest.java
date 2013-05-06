/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import jp.happyhacking70.cum.cmd.req.impl.ReqCmdJoinChnl;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdJoinChnl;
import jp.happyhacking70.cum.presSvr.CumTestAbst;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdXML;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscNull;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl.PresSvrAdptrHdlrJoinChnl;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf;
import jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr;

import org.junit.Before;
import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresSvrAdptrHdlrJoinChnlTest extends CumTestAbst {

	/**
	 * @throws IOException
	 */
	public PresSvrAdptrHdlrJoinChnlTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
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
	 * {@link jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl.PresSvrAdptrHdlrJoinChnl#hndlCmd(jp.happyhacking70.cum.cmd.CmdAbst, jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpRscNull
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpIllegalCmdDoc
	 * @throws CumExcpIllegalCmdXML
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testHndlCmd() throws CumExcpSeshExists, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpSeshNotExist,
			CumExcpAudExists, CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc,
			CumExcpXMLGenFailed {

		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);
		seshMgr.regChnl(seshName, chnlName, rscesArray);
		seshMgr.joinSesh(seshName, audName, senderForAudA);
		senderForAudA.pollCmd();

		PresSvrAdptrHdlrJoinChnl hdlr = new PresSvrAdptrHdlrJoinChnl();

		ResCmdJoinChnl resCmd = (ResCmdJoinChnl) hdlr.hndlCmd(
				new ReqCmdJoinChnl(seshName, chnlName, audName), seshMgr);

		assertEquals(ResCmdJoinChnl.class, resCmd.getClass());
		assertEquals(seshName, resCmd.getSeshName());
		assertEquals(chnlName, resCmd.getChnlName());
		assertEquals(audName, resCmd.getAudName());
		assertEquals(ResCmdJoinChnl.RsltTypes.Joined.name(), resCmd.getRslt());

	}

	@Test
	public void testHndlCmd_NOSESH() throws CumExcpSeshExists,
			CumExcpRscExists, CumExcpChnlExists, CumExcpRscNull,
			CumExcpAudExists, CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc,
			CumExcpSeshNotExist, CumExcpXMLGenFailed {

		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);
		seshMgr.regChnl(seshName, chnlName, rscesArray);
		seshMgr.joinSesh(seshName, audName, senderForAudA);
		senderForAudA.pollCmd();

		PresSvrAdptrHdlrJoinChnl hdlr = new PresSvrAdptrHdlrJoinChnl();

		ResCmdJoinChnl resCmd = (ResCmdJoinChnl) hdlr.hndlCmd(
				new ReqCmdJoinChnl("XXX", chnlName, audName), seshMgr);

		assertEquals(ResCmdJoinChnl.class, resCmd.getClass());
		assertEquals("XXX", resCmd.getSeshName());
		assertEquals(chnlName, resCmd.getChnlName());
		assertEquals(audName, resCmd.getAudName());
		assertEquals(ResCmdJoinChnl.RsltTypes.SeshNotExist.name(),
				resCmd.getRslt());

	}

	@Test
	public void testHndlCmd_NOCHNL() throws CumExcpSeshExists,
			CumExcpRscExists, CumExcpChnlExists, CumExcpRscNull,
			CumExcpSeshNotExist, CumExcpAudExists, CumExcpIllegalCmdXML,
			CumExcpIllegalCmdDoc, CumExcpXMLGenFailed {

		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);
		seshMgr.regChnl(seshName, chnlName, rscesArray);
		seshMgr.joinSesh(seshName, audName, senderForAudA);
		senderForAudA.pollCmd();

		PresSvrAdptrHdlrJoinChnl hdlr = new PresSvrAdptrHdlrJoinChnl();

		ResCmdJoinChnl resCmd = (ResCmdJoinChnl) hdlr.hndlCmd(
				new ReqCmdJoinChnl(seshName, "XXX", audName), seshMgr);

		assertEquals(ResCmdJoinChnl.class, resCmd.getClass());
		assertEquals(seshName, resCmd.getSeshName());
		assertEquals("XXX", resCmd.getChnlName());
		assertEquals(audName, resCmd.getAudName());
		assertEquals(ResCmdJoinChnl.RsltTypes.ChnlNotExist.name(),
				resCmd.getRslt());

	}

	@Test
	public void testHndlCmd_AUDEXIST() throws CumExcpSeshExists,
			CumExcpRscExists, CumExcpChnlExists, CumExcpRscNull,
			CumExcpSeshNotExist, CumExcpAudExists, CumExcpIllegalCmdXML,
			CumExcpIllegalCmdDoc, CumExcpAudNotExist, CumExcpChnlNotExist,
			CumExcpXMLGenFailed {

		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);
		seshMgr.regChnl(seshName, chnlName, rscesArray);
		seshMgr.joinSesh(seshName, audName, senderForAudA);
		senderForAudA.pollCmd();
		seshMgr.joinChnl(seshName, chnlName, audName);

		PresSvrAdptrHdlrJoinChnl hdlr = new PresSvrAdptrHdlrJoinChnl();

		ResCmdJoinChnl resCmd = (ResCmdJoinChnl) hdlr.hndlCmd(
				new ReqCmdJoinChnl(seshName, chnlName, audName), seshMgr);

		assertEquals(ResCmdJoinChnl.class, resCmd.getClass());
		assertEquals(seshName, resCmd.getSeshName());
		assertEquals(chnlName, resCmd.getChnlName());
		assertEquals(audName, resCmd.getAudName());
		assertEquals(ResCmdJoinChnl.RsltTypes.Exists.name(), resCmd.getRslt());

	}

	@Test
	public void testHndlCmd_AUDNOTEXIST() throws CumExcpSeshExists,
			CumExcpRscExists, CumExcpChnlExists, CumExcpRscNull,
			CumExcpSeshNotExist, CumExcpAudExists, CumExcpIllegalCmdXML,
			CumExcpIllegalCmdDoc, CumExcpXMLGenFailed {

		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);
		seshMgr.regChnl(seshName, chnlName, rscesArray);

		PresSvrAdptrHdlrJoinChnl hdlr = new PresSvrAdptrHdlrJoinChnl();

		ResCmdJoinChnl resCmd = (ResCmdJoinChnl) hdlr.hndlCmd(
				new ReqCmdJoinChnl(seshName, chnlName, audName), seshMgr);

		assertEquals(ResCmdJoinChnl.class, resCmd.getClass());
		assertEquals(seshName, resCmd.getSeshName());
		assertEquals(chnlName, resCmd.getChnlName());
		assertEquals(audName, resCmd.getAudName());
		assertEquals(ResCmdJoinChnl.RsltTypes.AudNotExist.name(),
				resCmd.getRslt());

	}
}
