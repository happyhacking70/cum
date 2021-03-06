/**
 * 
 */
package jp.happyhacking70.cum.presSvr.chnlLyr;

import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdJoinChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdLvChnl;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscNotExist;
import jp.happyhacking70.cum.presSvr.audLyr.AudIntf;

/**
 * Add audience to the channel. Notification to Presenter is supposed to be sent
 * by sesh. This should not send notification.
 * 
 * @author happyhacking70@gmail.com
 * 
 */
public interface ChnlPresSvrAudIntf {

	/**
	 * <UL>
	 * <LI>add audience to channel</LI>
	 * <LI>return NtfyCmdJoinChnl so that session can sent it to presenter</LI>
	 * </UL>
	 * 
	 * @param aud
	 * @return NtfyCmdJoinChnl
	 * @throws CumExcpAudExists
	 */
	public NtfyCmdJoinChnl joinChnl(AudIntf aud) throws CumExcpAudExists;

	/**
	 * <UL>
	 * <LI>remove audience from channel</LI>
	 * <LI>return NtfyCmdLvChnl so that session can sent it to presenter</LI>
	 * </UL>
	 * 
	 * @param aud
	 * @return NtfyCmdLvChnl
	 * @throws CumExcpAudNotExist
	 */
	public NtfyCmdLvChnl lvChnl(AudIntf aud) throws CumExcpAudNotExist;

	/**
	 * <UL>
	 * <LI>get channel resource</LI>
	 * </UL>
	 * 
	 * @param rscName
	 * @return ChnlRscIntf
	 * @throws CumExcpRscNotExist
	 */
	public ChnlRscIntf getRsc(String rscName) throws CumExcpRscNotExist;

	/**
	 * <UL>
	 * <LI>just check if audience is new comer</LI>
	 * </UL>
	 * 
	 * @param audName
	 * @throws CumExcpAudExists
	 */
	public void rjctChnl(String audName) throws CumExcpAudExists;

}
