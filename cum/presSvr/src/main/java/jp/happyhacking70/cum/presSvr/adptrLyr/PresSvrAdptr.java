/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr;

import java.util.ArrayList;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.CmdFactory;
import jp.happyhacking70.cum.cmd.CmdWithRscIntf;
import jp.happyhacking70.cum.cmd.req.ReqCmdFromAudIntf;
import jp.happyhacking70.cum.cmd.req.ReqCmdFromPrestrIntf;
import jp.happyhacking70.cum.cmd.res.ResCmdIntf;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdIllegalXML;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdUnknowCmd;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdXMLGenFailed;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdXML;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.discnHdlr.DiscnHdlrAbst;
import jp.happyhacking70.cum.presSvr.adptrLyr.discnHdlr.DiscnHdlrAud;
import jp.happyhacking70.cum.presSvr.adptrLyr.discnHdlr.DiscnHdlrPrestr;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcptAdptrHdlrNotFound;
import jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl.PresSvrAdptrHdlrFactory;
import jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf;

import org.javatuples.Pair;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresSvrAdptr implements PresSvrAdptrIntf {
	protected PresSvrAdptrHdlrFactory hdlrFactory = new PresSvrAdptrHdlrFactory();
	protected CmdFactory cmdFactory = new CmdFactory();

	static protected ResCmdIllegalXML resCmdIllegalXML = new ResCmdIllegalXML();
	static protected ResCmdUnknowCmd resCmdUnknowCmd = new ResCmdUnknowCmd();
	protected SeshMgrPresSvrAllIntf seshMgr;

	public PresSvrAdptr(SeshMgrPresSvrAllIntf seshMgr) {
		super();
		this.seshMgr = seshMgr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.presSvr.adptrLyr.PresSvrAdptrIntf#hndlCmd(jp.
	 * happyhacking70.cum3.cmd.ReqCmdIntf)
	 */
	@Override
	public Pair<String, DiscnHdlrAbst> hndlCmd(String xml)
			throws CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc {
		ResCmdIntf resCmd;
		CmdAbst cmd = getCmdIntsnace(xml);
		DiscnHdlrAbst discnHdlr = getDisconHdlr(cmd);

		if (cmd instanceof ResCmdIllegalXML) {
			resCmd = (ResCmdIntf) cmd;
		} else {
			resCmd = hndlCmd(cmd);
		}

		return new Pair<String, DiscnHdlrAbst>(toString(resCmd), discnHdlr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.presSvr.adptrLyr.PresSvrAdptrIntf#hndlCmd(jp.
	 * happyhacking70.cum3.cmd.ReqCmdIntf,
	 * jp.happyhacking70.cum3.presSvr.comLyr.CmdSenderIntf)
	 */
	@Override
	public Pair<String, DiscnHdlrAbst> hndlCmd(String xml, CmdSenderIntf sender)
			throws CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc {
		ResCmdIntf resCmd;
		CmdAbst cmd = getCmdIntsnace(xml);
		DiscnHdlrAbst discnHdlr = getDisconHdlr(cmd);
		if (cmd instanceof ResCmdIllegalXML) {
			resCmd = (ResCmdIntf) cmd;
		} else {
			PresSvrAdptrHdlrWithSenderIntf hdlr = null;
			try {
				hdlr = (PresSvrAdptrHdlrWithSenderIntf) hdlrFactory
						.getHdlr(cmd);
				if (hdlr instanceof PresSvrAdptrHdlrWithSenderIntf) {
					resCmd = hdlr.hndlCmd(cmd, sender, seshMgr);
				} else {
					resCmd = new ResCmdUnknowCmd();
				}
			} catch (CumExcptAdptrHdlrNotFound e) {
				resCmd = resCmdUnknowCmd;
			} catch (CumExcpIllegalCmdXML e) {
				resCmd = resCmdIllegalXML;
			} catch (CumExcpIllegalCmdDoc e) {
				resCmd = resCmdIllegalXML;
			} catch (CumExcpXMLGenFailed e) {
				resCmd = resCmdIllegalXML;
			}

		}

		return new Pair<String, DiscnHdlrAbst>(toString(resCmd), discnHdlr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.presSvr.adptrLyr.PresSvrAdptrIntf#hndlCmd(jp.
	 * happyhacking70.cum3.cmd.ReqCmdIntf, java.util.ArrayList)
	 */
	@Override
	public Pair<String, DiscnHdlrAbst> hndlCmd(String xml,
			ArrayList<ChnlRscIntf> rsces) throws CumExcpIllegalCmdXML,
			CumExcpIllegalCmdDoc {
		CmdAbst cmd = getCmdIntsnace(xml);
		DiscnHdlrAbst discnHdlr = getDisconHdlr(cmd);

		ResCmdIntf resCmd;

		if (cmd instanceof ResCmdIllegalXML) {
			resCmd = (ResCmdIntf) cmd;
		} else if (!(cmd instanceof CmdWithRscIntf)) {
			resCmd = resCmdIllegalXML;
		} else {
			CmdWithRscIntf cmdWithRsc = (CmdWithRscIntf) cmd;
			for (ChnlRscIntf rsc : rsces) {
				cmdWithRsc.addRscData(rsc);
			}
			resCmd = hndlCmd(cmd);
		}

		return new Pair<String, DiscnHdlrAbst>(toString(resCmd), discnHdlr);
	}

	/**
	 * @param cmd
	 * @return response command
	 */
	protected ResCmdIntf hndlCmd(CmdAbst cmd) {
		ResCmdIntf resCmd;
		PresSvrAdptrHdlrIntf hdlr = null;
		try {
			hdlr = (PresSvrAdptrHdlrIntf) hdlrFactory.getHdlr(cmd);
			resCmd = hdlr.hndlCmd(cmd, seshMgr);
		} catch (CumExcptAdptrHdlrNotFound e) {
			resCmd = resCmdUnknowCmd;
		} catch (CumExcpIllegalCmdXML e) {
			resCmd = resCmdIllegalXML;
		} catch (CumExcpIllegalCmdDoc e) {
			resCmd = resCmdIllegalXML;
		} catch (CumExcpXMLGenFailed e) {
			resCmd = resCmdIllegalXML;
		}

		return resCmd;
	}

	protected CmdAbst getCmdIntsnace(String xml) throws CumExcpIllegalCmdXML,
			CumExcpIllegalCmdDoc {
		CmdAbst cmd;
		cmd = cmdFactory.getCmdInstance(xml);

		return cmd;
	}

	protected String toString(ResCmdIntf cmd) {
		String ret;
		try {
			ret = cmd.toXmlStr();
		} catch (CumExcpXMLGenFailed e) {
			ret = ResCmdXMLGenFailed.xml;
		}
		return ret;
	}

	protected DiscnHdlrAbst getDisconHdlr(CmdAbst cmd) {
		DiscnHdlrAbst discnHdlr = null;
		if (cmd instanceof ReqCmdFromAudIntf) {
			ReqCmdFromAudIntf cmdFromAud = (ReqCmdFromAudIntf) cmd;
			discnHdlr = new DiscnHdlrAud(seshMgr, cmdFromAud.getSeshName(),
					cmdFromAud.getAudName());
		} else if (cmd instanceof ReqCmdFromPrestrIntf) {
			ReqCmdFromPrestrIntf cmdFromPrestr = (ReqCmdFromPrestrIntf) cmd;
			discnHdlr = new DiscnHdlrPrestr(seshMgr,
					cmdFromPrestr.getSeshName());
		}
		return discnHdlr;
	}
}
