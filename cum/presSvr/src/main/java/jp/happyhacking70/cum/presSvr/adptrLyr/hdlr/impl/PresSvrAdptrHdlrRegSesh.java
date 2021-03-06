/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.req.impl.ReqCmdRegSesh;
import jp.happyhacking70.cum.cmd.res.ResCmdIntf;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdRegSesh;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdXML;
import jp.happyhacking70.cum.presSvr.adptrLyr.PresSvrAdptrHdlrWithSenderIntf;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshExists;
import jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresSvrAdptrHdlrRegSesh implements PresSvrAdptrHdlrWithSenderIntf {

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.presSvr.comLyr.adaptor.
	 * PresSvrAdaptorHdlrWithSenderIntf
	 * #hndlCmd(jp.happyhacking70.cum3.cmd.CmdAbst,
	 * jp.happyhacking70.cum3.presSvr.comLyr.CmdSenderIntf,
	 * jp.happyhacking70.cum3.presSvr.seshLyr.SeshMgrPresSvrAllIntf)
	 */
	@Override
	public ResCmdIntf hndlCmd(CmdAbst cmd, CmdSenderIntf sender,
			SeshMgrPresSvrAllIntf seshMgr) throws CumExcpIllegalCmdXML,
			CumExcpIllegalCmdDoc {

		ReqCmdRegSesh reqCmd = (ReqCmdRegSesh) cmd;
		ResCmdRegSesh resCmd;

		try {
			seshMgr.regSesh(reqCmd.getSeshName(), sender);
			// sender.sendCmd(new ResCmdRegSesh(reqCmd.getSeshName(),
			// ResCmdRegSesh.RsltTypes.Reged));

			resCmd = new ResCmdRegSesh(reqCmd.getSeshName(),
					ResCmdRegSesh.RsltTypes.Reged);

		} catch (CumExcpSeshExists e) {
			resCmd = new ResCmdRegSesh(reqCmd.getSeshName(),
					ResCmdRegSesh.RsltTypes.Exists);
		}

		return resCmd;
	}
}
