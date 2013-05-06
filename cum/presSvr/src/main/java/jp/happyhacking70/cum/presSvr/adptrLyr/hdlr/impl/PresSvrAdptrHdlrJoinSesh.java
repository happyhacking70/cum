/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.req.impl.ReqCmdJoinSesh;
import jp.happyhacking70.cum.cmd.res.ResCmdIntf;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdJoinSesh;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdXML;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.PresSvrAdptrHdlrWithSenderIntf;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshNotExist;
import jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresSvrAdptrHdlrJoinSesh implements PresSvrAdptrHdlrWithSenderIntf {

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
			CumExcpIllegalCmdDoc, CumExcpXMLGenFailed {

		ReqCmdJoinSesh reqCmd = (ReqCmdJoinSesh) cmd;
		ResCmdJoinSesh resCmd;

		try {
			seshMgr.joinSesh(reqCmd.getSeshName(), reqCmd.getAudName(), sender);
			// sender.sendCmd(new ResCmdJoinSesh(reqCmd.getSeshName(), reqCmd
			// .getAudName(), ResCmdJoinSesh.RsltTypes.Joined));

			resCmd = new ResCmdJoinSesh(reqCmd.getSeshName(),
					reqCmd.getAudName(), ResCmdJoinSesh.RsltTypes.Joined);

		} catch (CumExcpAudExists e) {
			resCmd = new ResCmdJoinSesh(reqCmd.getSeshName(),
					reqCmd.getAudName(), ResCmdJoinSesh.RsltTypes.Exists);
		} catch (CumExcpSeshNotExist e) {
			resCmd = new ResCmdJoinSesh(reqCmd.getSeshName(),
					reqCmd.getAudName(), ResCmdJoinSesh.RsltTypes.NotExist);
		}

		return resCmd;
	}
}
