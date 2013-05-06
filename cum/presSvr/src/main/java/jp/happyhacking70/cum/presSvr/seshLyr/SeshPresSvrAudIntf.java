/**
 * 
 */
package jp.happyhacking70.cum.presSvr.seshLyr;

import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdJoinChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdJoinSesh;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdLvChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdLvSesh;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdRegChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdRjctChnl;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscNotExist;
import jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface SeshPresSvrAudIntf {

	/**
	 * <UL>
	 * <LI>add audience to session</LI>
	 * <LI>send {@link NtfyCmdJoinSesh} to <U>presenter</U></LI>
	 * <LI>send {@link NtfyCmdRegChnl} to <U>audience</U> for all existing
	 * channels</LI>
	 * </UL>
	 * 
	 * @param audName
	 * @param sender
	 * @throws CumExcpAudExists
	 * @throws CumExcpXMLGenFailed
	 */
	public void joinSesh(String audName, CmdSenderIntf sender)
			throws CumExcpAudExists, CumExcpXMLGenFailed;

	/**
	 * <UL>
	 * <LI>remove audience from session</LI>
	 * <LI>let audience leave from all the channels which audience is still
	 * joining</LI>
	 * <LI>no need to send {@link NtfyCmdLvChnl} to <U>presenter</U> because
	 * presenter takes care of it</LI>
	 * <LI>no need to send {@link NtfyCmdLvChnl} to <U>audience</U> because
	 * audience takes care of it</LI>
	 * <LI>send {@link NtfyCmdLvSesh} to <U>presenter</U></LI>
	 * </UL>
	 * 
	 * @param audName
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpXMLGenFailed
	 */
	public void lvSesh(String audName) throws CumExcpAudNotExist,
			CumExcpXMLGenFailed;

	/**
	 * <UL>
	 * <LI>add audience to channel</LI>
	 * <LI>send {@link NtfyCmdJoinChnl} to <U>presenter</U></LI>
	 * </UL>
	 * 
	 * @param chnlName
	 * @param audName
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpAudExists
	 * @throws CumExcpXMLGenFailed
	 */
	public void joinChnl(String chnlName, String audName)
			throws CumExcpChnlNotExist, CumExcpAudNotExist, CumExcpAudExists,
			CumExcpXMLGenFailed;

	/**
	 * <UL>
	 * <LI>remove audience from channel</LI>
	 * <LI>send {@link NtfyCmdLvChnl} to <U>presenter</U></LI>
	 * </UL>
	 * 
	 * @param chnlName
	 * @param audName
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpXMLGenFailed
	 */
	public void lvChnl(String chnlName, String audName)
			throws CumExcpChnlNotExist, CumExcpAudNotExist, CumExcpXMLGenFailed;

	/**
	 * <UL>
	 * <LI>checks if audience is new comer</LI>
	 * <LI>send {@link NtfyCmdRjctChnl} to <U>presenter</U></LI>
	 * </UL>
	 * 
	 * @param chnlName
	 * @param audName
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudExists
	 * @throws CumExcpXMLGenFailed
	 */
	public void rjctChnl(String chnlName, String audName)
			throws CumExcpChnlNotExist, CumExcpAudExists, CumExcpXMLGenFailed;

	/**
	 * get channel resource
	 * 
	 * @param chnlName
	 * @param rscName
	 * @return channel resource
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpRscNotExist
	 */
	public ChnlRscIntf getRsc(String chnlName, String rscName)
			throws CumExcpChnlNotExist, CumExcpRscNotExist;

}
