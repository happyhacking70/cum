/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.req.impl.ReqCmdRjctChnl;
import jp.happyhacking70.cum.cmd.res.ResCmdIntf;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdRjctChnl;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdXML;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.PresSvrAdptrHdlrIntf;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshNotExist;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresSvrAdptrHdlrRjctChnl implements PresSvrAdptrHdlrIntf {

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

		ReqCmdRjctChnl reqCmd = (ReqCmdRjctChnl) cmd;
		ResCmdRjctChnl resCmd;

		try {
			seshMgr.rjctChnl(reqCmd.getSeshName(), reqCmd.getChnlName(),
					reqCmd.getAudName());
			resCmd = new ResCmdRjctChnl(reqCmd.getSeshName(),
					reqCmd.getChnlType(), reqCmd.getChnlName(),
					reqCmd.getAudName(), ResCmdRjctChnl.RsltTypes.Rjcted.name());

		} catch (CumExcpAudExists e) {
			resCmd = new ResCmdRjctChnl(reqCmd.getSeshName(),
					reqCmd.getChnlType(), reqCmd.getChnlName(),
					reqCmd.getAudName(), ResCmdRjctChnl.RsltTypes.Exists.name());

		} catch (CumExcpChnlNotExist e) {
			resCmd = new ResCmdRjctChnl(reqCmd.getSeshName(),
					reqCmd.getChnlType(), reqCmd.getChnlName(),
					reqCmd.getAudName(),
					ResCmdRjctChnl.RsltTypes.ChnlNotExist.name());

		} catch (CumExcpSeshNotExist e) {
			resCmd = new ResCmdRjctChnl(reqCmd.getSeshName(),
					reqCmd.getChnlType(), reqCmd.getChnlName(),
					reqCmd.getAudName(),
					ResCmdRjctChnl.RsltTypes.SeshNotExist.name());

		}

		return resCmd;
	}
}
