/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import jp.happyhacking70.cum.cmd.req.impl.ReqCmdRjctChnl;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdRjctChnl;
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
import jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl.PresSvrAdptrHdlrRjctChnl;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf;
import jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresSvrAdptrrHdlrRjctChnlTest extends CumTestAbst {

	/**
	 * @throws IOException
	 */
	public PresSvrAdptrrHdlrRjctChnlTest() throws IOException {
		super();

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl.PresSvrAdptrHdlrClsSesh#hndlCmd(jp.happyhacking70.cum.cmd.CmdAbst, jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf)}
	 * .
	 * 
	 * @throws CumExcpSeshExists
	 * @throws CumExcpIllegalCmdDoc
	 * @throws CumExcpIllegalCmdXML
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpRscNull
	 * @throws CumExcptNullRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcpAudExists
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testHndlCmd() throws CumExcpSeshExists, CumExcpIllegalCmdXML,
			CumExcpIllegalCmdDoc, CumExcpRscExists, CumExcpChnlExists,
			CumExcpRscNull, CumExcpSeshNotExist, CumExcpAudExists,
			CumExcpXMLGenFailed {
		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);
		seshMgr.regChnl(seshName, chnlName, rscesArray);
		seshMgr.joinSesh(seshName, audName, senderForAudA);

		PresSvrAdptrHdlrRjctChnl hdlr = new PresSvrAdptrHdlrRjctChnl();

		ResCmdRjctChnl resCmd = (ResCmdRjctChnl) hdlr.hndlCmd(
				new ReqCmdRjctChnl(seshName, chnlName, audName), seshMgr);

		assertEquals(ResCmdRjctChnl.class, resCmd.getClass());
		assertEquals(ResCmdRjctChnl.RsltTypes.Rjcted.name(), resCmd.getRslt());
	}

	@Test
	public void testHndlCmd_NOSESH() throws CumExcpSeshExists,
			CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpSeshNotExist,
			CumExcpAudExists, CumExcpXMLGenFailed {
		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();
		seshMgr.regSesh(seshName, senderForPrestr);
		seshMgr.regChnl(seshName, chnlName, rscesArray);
		seshMgr.joinSesh(seshName, audName, senderForAudA);
		PresSvrAdptrHdlrRjctChnl hdlr = new PresSvrAdptrHdlrRjctChnl();

		ResCmdRjctChnl resCmd = (ResCmdRjctChnl) hdlr.hndlCmd(
				new ReqCmdRjctChnl("XXX", chnlName, audName), seshMgr);

		assertEquals(ResCmdRjctChnl.class, resCmd.getClass());
		assertEquals(ResCmdRjctChnl.RsltTypes.SeshNotExist.name(),
				resCmd.getRslt());
	}

	@Test
	public void testHndlCmd_NOCHNL() throws CumExcpSeshExists,
			CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc, CumExcpAudExists,
			CumExcpSeshNotExist, CumExcpRscExists, CumExcpChnlExists,
			CumExcpRscNull, CumExcpXMLGenFailed {
		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);
		seshMgr.regChnl(seshName, chnlName, rscesArray);
		seshMgr.joinSesh(seshName, audName, senderForAudA);

		PresSvrAdptrHdlrRjctChnl hdlr = new PresSvrAdptrHdlrRjctChnl();

		ResCmdRjctChnl resCmd = (ResCmdRjctChnl) hdlr.hndlCmd(
				new ReqCmdRjctChnl(seshName, "XXX", audName), seshMgr);

		assertEquals(ResCmdRjctChnl.class, resCmd.getClass());
		assertEquals(ResCmdRjctChnl.RsltTypes.ChnlNotExist.name(),
				resCmd.getRslt());
	}

	@Test
	public void testHndlCmd_AUDEXIST() throws CumExcpSeshExists,
			CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc, CumExcpAudExists,
			CumExcpSeshNotExist, CumExcpRscExists, CumExcpChnlExists,
			CumExcpRscNull, CumExcpAudNotExist, CumExcpChnlNotExist,
			CumExcpXMLGenFailed {
		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);
		seshMgr.regChnl(seshName, chnlName, rscesArray);
		seshMgr.joinSesh(seshName, audName, senderForAudA);
		seshMgr.joinChnl(seshName, chnlName, audName);

		PresSvrAdptrHdlrRjctChnl hdlr = new PresSvrAdptrHdlrRjctChnl();

		ResCmdRjctChnl resCmd = (ResCmdRjctChnl) hdlr.hndlCmd(
				new ReqCmdRjctChnl(seshName, chnlName, audName), seshMgr);

		assertEquals(ResCmdRjctChnl.class, resCmd.getClass());
		assertEquals(ResCmdRjctChnl.RsltTypes.Exists.name(), resCmd.getRslt());
	}
}
