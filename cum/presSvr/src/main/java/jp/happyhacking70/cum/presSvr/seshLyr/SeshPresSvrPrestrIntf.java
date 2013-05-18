/**
 * 
 */
package jp.happyhacking70.cum.presSvr.seshLyr;

import java.util.List;

import jp.happyhacking70.cum.cmd.CmdChnlAbst;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdClsChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdClsSesh;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdRegChnl;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscNull;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface SeshPresSvrPrestrIntf {

	/**
	 * <UL>
	 * <LI>send {@link NtfyCmdClsSesh} to all <U>session</U> audiences</LI>
	 * <LI>no need to send {@link NtfyCmdClsChnl} to presenter because presenter
	 * takes care of closing channels when session is closed</LI>
	 * <LI>no need to send {@link NtfyCmdClsChnl} to audiences because audience
	 * takes care of closing channels when session is closed</LI>
	 * </UL>
	 * 
	 * @throws CumExcpXMLGenFailed
	 * 
	 * 
	 */
	public void clsSesh() throws CumExcpXMLGenFailed;

	/**
	 * <UL>
	 * <LI>Create new channel</LI>
	 * <LI>Send {@link NtfyCmdRegChnl} to <U>session</U> audiences</LI>
	 * </UL>
	 * 
	 * @param chnlType
	 * @param chnlName
	 * @param chnlRsces
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcptNullRsces
	 * @throws CumExcpRscNull
	 * @throws CumExcpXMLGenFailed
	 */

	public void regChnl(String chnlType, String chnlName,
			List<ChnlRscIntf> chnlRsces) throws CumExcpChnlExists,
			CumExcpRscExists, CumExcpRscNull, CumExcpXMLGenFailed;

	/**
	 * <UL>
	 * <LI>send {@link NtfyCmdClsChnl} to all <U>session</U> audiences</LI>
	 * <LI>close channel</LI>
	 * </UL>
	 * Even audience rejected channel, audience will maintain channel so that
	 * he/she can join later. Audience should be notified when channel is
	 * closed.
	 * 
	 * @param chnlName
	 * @throws CumExcpXMLGenFailed
	 * 
	 */
	public void clsChnl(String chnlName) throws CumExcpChnlNotExist,
			CumExcpXMLGenFailed;

	/**
	 * <UL>
	 * <LI>send command to <U>specific</U> audience</LI>
	 * </UL>
	 * 
	 * @param cmd
	 * @param audName
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpXMLGenFailed
	 */
	public void sendChnlCmd(CmdChnlAbst cmd, String audName)
			throws CumExcpChnlNotExist, CumExcpAudNotExist, CumExcpXMLGenFailed;

	/**
	 * <UL>
	 * <LI>send command to all <U>channel</U> audiences</LI>
	 * </UL>
	 * 
	 * @param cmd
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpXMLGenFailed
	 */
	public void sendChnlCmd(CmdChnlAbst cmd) throws CumExcpChnlNotExist,
			CumExcpAudNotExist, CumExcpXMLGenFailed;

}
