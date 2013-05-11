/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import java.util.HashMap;

import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatuMulti;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.prestr.adptrLyr.PrestrAdptrIntf;
import jp.happyhacking70.cum.prestr.chnlLyr.PrestrChnlNotfyIntf;
import jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface PrestrSeshIntfForSeshView {

	/**
	 * register session<BR>
	 * <BR>
	 * TASKS<BR>
	 * <UL>
	 * <LI>chanege status into {@link PrestrSesh.SeshStatus#reging}</LI>
	 * <LI>invoke {@link PrestrAdptrIntf#regSesh(String)}</LI>
	 * </UL>
	 * 
	 * @throws CumExcpIllegalSeshStatus
	 *             if status is not {@link PrestrSesh.SeshStatus#init}
	 */
	void regSesh() throws CumExcpIllegalSeshStatus;

	/**
	 * close session<BR>
	 * <BR>
	 * TASKS<BR>
	 * <UL>
	 * <LI>chanege status into {@link PrestrSesh.SeshStatus#clsing}</LI>
	 * <LI>invoke {@link PrestrAdptrIntf#clsSesh(String)}</LI>
	 * <LI>notify all channels via {@link PrestrChnlNotfyIntf#seshClsing()}</LI>
	 * </UL>
	 * 
	 * 
	 * 
	 * @throws CumExcpIllegalSeshStatus
	 *             if status is not ( {@link PrestrSesh.SeshStatus#reging} or
	 *             {@link PrestrSesh.SeshStatus#reged} )
	 * @throws CumExcpIllegalChnlStatuMulti
	 *             if channel raise {@link CumExcpIllegalChnlStatus}, add to
	 *             {@link CumExcpIllegalChnlStatuMulti}. if there is more than
	 *             one, this is raised
	 */
	void clsSesh() throws CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatuMulti;

	/**
	 * register channel<BR>
	 * <BR>
	 * TASKS<BR>
	 * <UL>
	 * <LI>instantiate channel</LI>
	 * <LI>put channel into channels
	 * <LI>invoke
	 * {@link PrestrAdptrIntf#regChnl(String, String, jp.happyhacking70.cum.prestr.chnlLyr.PrestrChnlIntfFromChnlView, HashMap)}
	 * </LI>
	 * </UL>
	 * 
	 * 
	 * @param chnlName
	 *            name of channel
	 * @param rsces
	 *            channel resources
	 * @param chnlView
	 *            channel view
	 * @throws CumExcpChnlExists
	 *             when there is channle in channels
	 * @throws CumExcpIllegalSeshStatus
	 *             when status is not {@link PrestrSesh.SeshStatus#reged}
	 */
	void regChnl(String chnlName, HashMap<String, ChnlRscIntf> rsces,
			PrestrChnlViewIntf chnlView) throws CumExcpChnlExists,
			CumExcpIllegalSeshStatus;

}