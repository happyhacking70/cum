/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl;

import java.util.HashMap;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.req.impl.ReqCmdClsChnl;
import jp.happyhacking70.cum.cmd.req.impl.ReqCmdClsSesh;
import jp.happyhacking70.cum.cmd.req.impl.ReqCmdJoinChnl;
import jp.happyhacking70.cum.cmd.req.impl.ReqCmdJoinSesh;
import jp.happyhacking70.cum.cmd.req.impl.ReqCmdLvChnl;
import jp.happyhacking70.cum.cmd.req.impl.ReqCmdLvSesh;
import jp.happyhacking70.cum.cmd.req.impl.ReqCmdRegChnl;
import jp.happyhacking70.cum.cmd.req.impl.ReqCmdRegSesh;
import jp.happyhacking70.cum.cmd.req.impl.ReqCmdRjctChnl;
import jp.happyhacking70.cum.presSvr.adptrLyr.PresSvrAdptrHdlrIntfBase;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcptAdptrHdlrNotFound;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresSvrAdptrHdlrFactory {
	HashMap<Class<? extends CmdAbst>, PresSvrAdptrHdlrIntfBase> hdlrs = new HashMap<Class<? extends CmdAbst>, PresSvrAdptrHdlrIntfBase>();

	public PresSvrAdptrHdlrFactory() {
		super();
		setHdlrs();
	}

	/**
	 * set up hasmMap
	 */
	protected void setHdlrs() {

		hdlrs.put(ReqCmdClsSesh.class, new PresSvrAdptrHdlrClsSesh());
		hdlrs.put(ReqCmdRegChnl.class, new PresSvrAdptrHdlrRegChnl());
		hdlrs.put(ReqCmdClsChnl.class, new PresSvrAdptrHdlrClsChnl());
		hdlrs.put(ReqCmdJoinChnl.class, new PresSvrAdptrHdlrJoinChnl());
		hdlrs.put(ReqCmdLvChnl.class, new PresSvrAdptrHdlrLvChnl());
		hdlrs.put(ReqCmdLvSesh.class, new PresSvrAdptrHdlrLvSesh());
		hdlrs.put(ReqCmdRjctChnl.class, new PresSvrAdptrHdlrRjctChnl());
		hdlrs.put(ReqCmdRegSesh.class, new PresSvrAdptrHdlrRegSesh());
		hdlrs.put(ReqCmdJoinSesh.class, new PresSvrAdptrHdlrJoinSesh());
	}

	public PresSvrAdptrHdlrIntfBase getHdlr(CmdAbst cmd)
			throws CumExcptAdptrHdlrNotFound {
		PresSvrAdptrHdlrIntfBase hdlr = hdlrs.get(cmd.getClass());
		if (hdlr == null) {
			throw new CumExcptAdptrHdlrNotFound(cmd);
		}
		return hdlr;
	}

}
