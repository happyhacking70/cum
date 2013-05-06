/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.req.impl.ReqCmdLvSesh;
import jp.happyhacking70.cum.cmd.res.ResCmdIntf;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdLvSesh;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdXML;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.PresSvrAdptrHdlrIntf;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshNotExist;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresSvrAdptrHdlrLvSesh implements PresSvrAdptrHdlrIntf {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.comLyr.adaptor.PresSvrAdaptorHdlrIntf#
	 * hndlCmd(java.lang.String)
	 */
	@Override
	public ResCmdIntf hndlCmd(CmdAbst cmd, SeshMgrPresSvrAllIntf seshMgr)
			throws CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc,
			CumExcpXMLGenFailed {

		ReqCmdLvSesh reqCmd = (ReqCmdLvSesh) cmd;
		ResCmdLvSesh resCmd;

		try {
			seshMgr.lvSesh(reqCmd.getSeshName(), reqCmd.getAudName());
			resCmd = new ResCmdLvSesh(reqCmd.getSeshName(),
					reqCmd.getAudName(), ResCmdLvSesh.RsltTypes.Left.name());

		} catch (CumExcpAudNotExist e) {
			resCmd = new ResCmdLvSesh(reqCmd.getSeshName(),
					reqCmd.getAudName(), ResCmdLvSesh.RsltTypes.NotExist.name());

		} catch (CumExcpSeshNotExist e) {
			resCmd = new ResCmdLvSesh(reqCmd.getSeshName(),
					reqCmd.getAudName(),
					ResCmdLvSesh.RsltTypes.SeshNotExist.name());
		}

		return resCmd;
	}
}
