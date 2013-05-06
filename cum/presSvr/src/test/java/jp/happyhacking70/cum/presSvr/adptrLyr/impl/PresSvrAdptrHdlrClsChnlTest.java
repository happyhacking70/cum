/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import jp.happyhacking70.cum.cmd.req.impl.ReqCmdClsChnl;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdClsChnl;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdXML;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.CumTestAbst;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscNull;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl.PresSvrAdptrHdlrClsChnl;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf;
import jp.happyhacking70.cum.presSvr.seshLyr.impl.SeshMgrPresSvr;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresSvrAdptrHdlrClsChnlTest extends CumTestAbst {

	/**
	 * @throws IOException
	 */
	public PresSvrAdptrHdlrClsChnlTest() throws IOException {
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
	 * @throws CumExcpXMLGenFailed
	 */
	@Test
	public void testHndlCmd() throws CumExcpSeshExists, CumExcpIllegalCmdXML,
			CumExcpIllegalCmdDoc, CumExcpRscExists, CumExcpChnlExists,
			CumExcpRscNull, CumExcpSeshNotExist, CumExcpXMLGenFailed {
		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);
		seshMgr.regChnl(seshName, chnlName, rscesArray);

		PresSvrAdptrHdlrClsChnl hdlr = new PresSvrAdptrHdlrClsChnl();

		ResCmdClsChnl resCmd = (ResCmdClsChnl) hdlr.hndlCmd(new ReqCmdClsChnl(
				seshName, chnlName), seshMgr);

		assertEquals(ResCmdClsChnl.class, resCmd.getClass());
		assertEquals(ResCmdClsChnl.RsltTypes.Clsed.name(), resCmd.getRslt());
	}

	@Test
	public void testHndlCmd_NOSESH() throws CumExcpSeshExists,
			CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc, CumExcpXMLGenFailed {
		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		PresSvrAdptrHdlrClsChnl hdlr = new PresSvrAdptrHdlrClsChnl();

		ResCmdClsChnl resCmd = (ResCmdClsChnl) hdlr.hndlCmd(new ReqCmdClsChnl(
				seshName, chnlName), seshMgr);

		assertEquals(ResCmdClsChnl.class, resCmd.getClass());
		assertEquals(ResCmdClsChnl.RsltTypes.SeshNotExist.name(),
				resCmd.getRslt());
	}

	@Test
	public void testHndlCmd_NOCHNL() throws CumExcpSeshExists,
			CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc, CumExcpXMLGenFailed {
		SeshMgrPresSvrAllIntf seshMgr = new SeshMgrPresSvr();

		seshMgr.regSesh(seshName, senderForPrestr);

		PresSvrAdptrHdlrClsChnl hdlr = new PresSvrAdptrHdlrClsChnl();

		ResCmdClsChnl resCmd = (ResCmdClsChnl) hdlr.hndlCmd(new ReqCmdClsChnl(
				seshName, chnlName), seshMgr);

		assertEquals(ResCmdClsChnl.class, resCmd.getClass());
		assertEquals(ResCmdClsChnl.RsltTypes.NotExist.name(), resCmd.getRslt());
	}
}
