/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.req.impl.ReqCmdJoinChnl;
import jp.happyhacking70.cum.cmd.res.ResCmdIntf;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdJoinChnl;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdXML;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.PresSvrAdptrHdlrIntf;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshNotExist;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresSvrAdptrHdlrJoinChnl implements PresSvrAdptrHdlrIntf {

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

		ReqCmdJoinChnl reqCmd = (ReqCmdJoinChnl) cmd;
		ResCmdJoinChnl resCmd;

		try {
			seshMgr.joinChnl(reqCmd.getSeshName(), reqCmd.getChnlName(),
					reqCmd.getAudName());
			resCmd = new ResCmdJoinChnl(reqCmd.getSeshName(),
					reqCmd.getChnlName(), reqCmd.getAudName(),
					ResCmdJoinChnl.RsltTypes.Joined.name());

		} catch (CumExcpAudExists e) {
			resCmd = new ResCmdJoinChnl(reqCmd.getSeshName(),
					reqCmd.getChnlName(), reqCmd.getAudName(),
					ResCmdJoinChnl.RsltTypes.Exists.name());
		} catch (CumExcpAudNotExist e) {
			resCmd = new ResCmdJoinChnl(reqCmd.getSeshName(),
					reqCmd.getChnlName(), reqCmd.getAudName(),
					ResCmdJoinChnl.RsltTypes.AudNotExist.name());
		} catch (CumExcpChnlNotExist e) {
			resCmd = new ResCmdJoinChnl(reqCmd.getSeshName(),
					reqCmd.getChnlName(), reqCmd.getAudName(),
					ResCmdJoinChnl.RsltTypes.ChnlNotExist.name());
		} catch (CumExcpSeshNotExist e) {
			resCmd = new ResCmdJoinChnl(reqCmd.getSeshName(),
					reqCmd.getChnlName(), reqCmd.getAudName(),
					ResCmdJoinChnl.RsltTypes.SeshNotExist.name());
		}

		return resCmd;
	}
}
