/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import jp.happyhacking70.cum.cmd.req.impl.ReqCmdLvSesh;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdLvSesh;
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
import jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl.PresSvrAdptrHdlrLvSesh;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf;
import jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresSvrAdptrHdlrLvSeshTest extends CumTestAbst {

	/**
	 * @throws IOException
	 */
	public PresSvrAdptrHdlrLvSeshTest() throws IOException {
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
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testHndlCmd() throws CumExcpSeshExists, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpSeshNotExist,
			CumExcpAudExists, CumExcpAudNotExist, CumExcpChnlNotExist,
			CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc, CumExcpXMLGenFailed {

		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);

		seshMgr.joinSesh(seshName, audName, senderForAudA);

		PresSvrAdptrHdlrLvSesh hdlr = new PresSvrAdptrHdlrLvSesh();

		ResCmdLvSesh resCmd = (ResCmdLvSesh) hdlr.hndlCmd(new ReqCmdLvSesh(
				seshName, audName), seshMgr);

		assertEquals(ResCmdLvSesh.class, resCmd.getClass());
		assertEquals(ResCmdLvSesh.RsltTypes.Left.name(), resCmd.getRslt());
	}

	@Test
	public void testHndlCmd_NOSESH() throws CumExcpSeshExists,
			CumExcpRscExists, CumExcpChnlExists, CumExcpRscNull,
			CumExcpSeshNotExist, CumExcpAudExists, CumExcpAudNotExist,
			CumExcpChnlNotExist, CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc,
			CumExcpXMLGenFailed {

		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);

		seshMgr.joinSesh(seshName, audName, senderForAudA);

		PresSvrAdptrHdlrLvSesh hdlr = new PresSvrAdptrHdlrLvSesh();

		ResCmdLvSesh resCmd = (ResCmdLvSesh) hdlr.hndlCmd(new ReqCmdLvSesh(
				"XXX", audName), seshMgr);

		assertEquals(ResCmdLvSesh.class, resCmd.getClass());
		assertEquals(ResCmdLvSesh.RsltTypes.SeshNotExist.name(),
				resCmd.getRslt());
	}

	@Test
	public void testHndlCmd_NOAUD() throws CumExcpSeshExists, CumExcpRscExists,
			CumExcpChnlExists, CumExcpRscNull, CumExcpSeshNotExist,
			CumExcpAudExists, CumExcpAudNotExist, CumExcpChnlNotExist,
			CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc, CumExcpXMLGenFailed {

		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);

		seshMgr.joinSesh(seshName, audName, senderForAudA);

		PresSvrAdptrHdlrLvSesh hdlr = new PresSvrAdptrHdlrLvSesh();

		ResCmdLvSesh resCmd = (ResCmdLvSesh) hdlr.hndlCmd(new ReqCmdLvSesh(
				seshName, "XXX"), seshMgr);

		assertEquals(ResCmdLvSesh.class, resCmd.getClass());
		assertEquals(ResCmdLvSesh.RsltTypes.NotExist.name(), resCmd.getRslt());
	}
}
