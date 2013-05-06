/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import jp.happyhacking70.cum.cmd.req.impl.ReqCmdRegChnl;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdRegChnl;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdXML;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.CumTestAbst;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscNull;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl.PresSvrAdptrHdlrRegChnl;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf;
import jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr;

import org.junit.Before;
import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresSvrAdptrrHdlrRegChnlTest extends CumTestAbst {

	/**
	 * @throws IOException
	 */
	public PresSvrAdptrrHdlrRegChnlTest() throws IOException {
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
	 * {@link jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl.PresSvrAdptrHdlrRegChnl#hndlCmd(jp.happyhacking70.cum.cmd.CmdAbst, jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf, java.util.ArrayList)}
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

		PresSvrAdptrHdlrRegChnl hdlr = new PresSvrAdptrHdlrRegChnl();
		ReqCmdRegChnl reqCmd = new ReqCmdRegChnl(seshName, chnlName);
		for (ChnlRscIntf rsc : rscesArray) {
			reqCmd.addRscData(rsc);
		}

		ResCmdRegChnl resCmd = null;
		resCmd = (ResCmdRegChnl) hdlr.hndlCmd(reqCmd, seshMgr);
		assertEquals(ResCmdRegChnl.class, resCmd.getClass());
		assertEquals(seshName, resCmd.getSeshName());
		assertEquals(chnlName, resCmd.getChnlName());
		assertEquals(ResCmdRegChnl.RsltTypes.Reged.name(), resCmd.getRslt());
		assertEquals(rscNameA, resCmd.getRscData().get(0).getName());
		assertEquals(rscNameB, resCmd.getRscData().get(1).getName());

	}

	@Test
	public void testHndlCmd_CHNLEXIST() throws CumExcpSeshExists,
			CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpSeshNotExist,
			CumExcpXMLGenFailed {
		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);
		senderForPrestr.pollCmd();
		seshMgr.regChnl(seshName, chnlName, rscesArray);
		senderForPrestr.pollCmd();

		PresSvrAdptrHdlrRegChnl hdlr = new PresSvrAdptrHdlrRegChnl();
		ReqCmdRegChnl reqCmd = new ReqCmdRegChnl(seshName, chnlName);
		for (ChnlRscIntf rsc : rscesArray) {
			reqCmd.addRscData(rsc);
		}

		ResCmdRegChnl resCmd = null;
		resCmd = (ResCmdRegChnl) hdlr.hndlCmd(reqCmd, seshMgr);
		assertEquals(ResCmdRegChnl.class, resCmd.getClass());
		assertEquals(seshName, resCmd.getSeshName());
		assertEquals(chnlName, resCmd.getChnlName());
		assertEquals(ResCmdRegChnl.RsltTypes.Exists.name(), resCmd.getRslt());
		assertEquals(rscNameA, resCmd.getRscData().get(0).getName());
		assertEquals(rscNameB, resCmd.getRscData().get(1).getName());
		assertNull(senderForPrestr.pollCmd());

	}

	@Test
	public void testHndlCmd_RSCEXIST() throws CumExcpSeshExists,
			CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpSeshNotExist,
			CumExcpXMLGenFailed {
		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);
		senderForPrestr.pollCmd();

		PresSvrAdptrHdlrRegChnl hdlr = new PresSvrAdptrHdlrRegChnl();
		ReqCmdRegChnl reqCmd = new ReqCmdRegChnl(seshName, chnlName);
		for (ChnlRscIntf rsc : rscesArray) {
			reqCmd.addRscData(rsc);
		}
		reqCmd.addRscData(rscImgA);

		ResCmdRegChnl resCmd = null;
		resCmd = (ResCmdRegChnl) hdlr.hndlCmd(reqCmd, seshMgr);
		assertEquals(ResCmdRegChnl.class, resCmd.getClass());
		assertEquals(seshName, resCmd.getSeshName());
		assertEquals(chnlName, resCmd.getChnlName());
		assertEquals(ResCmdRegChnl.RsltTypes.DupRsc.name(), resCmd.getRslt());
		assertEquals(rscNameA, resCmd.getRscData().get(0).getName());
		assertEquals(rscNameB, resCmd.getRscData().get(1).getName());
		assertEquals(rscNameA, resCmd.getRscData().get(2).getName());
		assertNull(senderForPrestr.pollCmd());

	}

	@Test
	public void testHndlCmd_RSCNULL() throws CumExcpSeshExists,
			CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpSeshNotExist,
			CumExcpXMLGenFailed {
		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);
		senderForPrestr.pollCmd();

		PresSvrAdptrHdlrRegChnl hdlr = new PresSvrAdptrHdlrRegChnl();
		ReqCmdRegChnl reqCmd = new ReqCmdRegChnl(seshName, chnlName);
		for (ChnlRscIntf rsc : rscesArray) {
			reqCmd.addRscData(rsc);
		}
		reqCmd.addRscData(rscImgA);

		ResCmdRegChnl resCmd = null;
		resCmd = (ResCmdRegChnl) hdlr.hndlCmd(reqCmd, seshMgr);
		assertEquals(ResCmdRegChnl.class, resCmd.getClass());
		assertEquals(seshName, resCmd.getSeshName());
		assertEquals(chnlName, resCmd.getChnlName());
		assertEquals(ResCmdRegChnl.RsltTypes.DupRsc.name(), resCmd.getRslt());
		assertEquals(rscNameA, resCmd.getRscData().get(0).getName());
		assertEquals(rscNameB, resCmd.getRscData().get(1).getName());
		assertEquals(rscNameA, resCmd.getRscData().get(2).getName());
		assertNull(senderForPrestr.pollCmd());

	}
}
