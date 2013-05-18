/**
 * 
 */
package jp.happyhacking70.cum.presSvr.seshLyr.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import jp.happyhacking70.cum.cmd.CmdChnlAbst;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscNull;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshNotExist;
import jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAudIntf;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrPrestrIntf;

/**
 * <UL>
 * <LI>SeshMgrPresSvr maintains all sessions on presentation server</LI>
 * <LI>All the detailed management is delegated to {@link SeshPresSvr}</LI>
 * </UL>
 * 
 * @author happyhacking70@gmail.com
 * 
 */
/**
 * @author happyhacking70@gmail.com
 * 
 */
public class SeshMgrPresSvr implements SeshMgrPresSvrAudIntf,
		SeshMgrPresSvrPrestrIntf, SeshMgrPresSvrAllIntf {
	protected ConcurrentHashMap<String, SeshPresSvr> seshes = new ConcurrentHashMap<String, SeshPresSvr>();

	/**
	 * @param seshName
	 * @return session
	 * @throws CumExcpSeshNotExist
	 */
	protected SeshPresSvr getSesh(String seshName) throws CumExcpSeshNotExist {
		SeshPresSvr sesh = seshes.get(seshName);
		if (sesh == null) {
			throw new CumExcpSeshNotExist(seshName);
		}
		return sesh;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrPrestrIntf#regSesh
	 * (java.lang.String, jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf)
	 */
	@Override
	public void regSesh(String seshName, CmdSenderIntf sender)
			throws CumExcpSeshExists {

		if (seshes.containsKey(seshName) == true) {
			throw new CumExcpSeshExists(seshName);
		}

		SeshPresSvr sesh = new SeshPresSvr(seshName, sender);

		seshes.put(seshName, sesh);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrPrestrIntf#regChnl
	 * (java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public void regChnl(String seshName, String chnlType, String chnlName,
			List<ChnlRscIntf> chnlRsces) throws CumExcpSeshNotExist,
			CumExcpRscExists, CumExcpChnlExists, CumExcpRscNull,
			CumExcpXMLGenFailed {

		SeshPresSvr sesh = getSesh(seshName);
		sesh.regChnl(chnlType, chnlName, chnlRsces);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrPrestrIntf#clsSesh
	 * (java.lang.String)
	 */
	@Override
	public void clsSesh(String seshName) throws CumExcpSeshNotExist,
			CumExcpXMLGenFailed {
		SeshPresSvr sesh = getSesh(seshName);
		sesh.clsSesh();
		seshes.remove(seshName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrPrestrIntf#sendChnlCmd
	 * (jp.happyhacking70.cum.cmd.CmdChnlAbst, java.lang.String)
	 */
	@Override
	public void sendChnlCmd(CmdChnlAbst cmd, String audName)
			throws CumExcpSeshNotExist, CumExcpChnlNotExist,
			CumExcpAudNotExist, CumExcpXMLGenFailed {
		SeshPresSvr sesh = getSesh(cmd.getSeshName());
		sesh.sendChnlCmd(cmd, audName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrPrestrIntf#sendChnlCmd
	 * (jp.happyhacking70.cum.cmd.CmdChnlAbst)
	 */
	@Override
	public void sendChnlCmd(CmdChnlAbst cmd) throws CumExcpSeshNotExist,
			CumExcpChnlNotExist, CumExcpAudNotExist, CumExcpXMLGenFailed {
		SeshPresSvr sesh = getSesh(cmd.getSeshName());
		sesh.sendChnlCmd(cmd);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrPrestrIntf#clsChnl
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public void clsChnl(String seshName, String chnlName)
			throws CumExcpSeshNotExist, CumExcpChnlNotExist,
			CumExcpXMLGenFailed {

		SeshPresSvr sesh = getSesh(seshName);
		sesh.clsChnl(chnlName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAudIntf#joinSesh(
	 * java.lang.String, java.lang.String,
	 * jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf)
	 */
	@Override
	public void joinSesh(String seshName, String audName, CmdSenderIntf sender)
			throws CumExcpSeshNotExist, CumExcpAudExists, CumExcpXMLGenFailed {
		SeshPresSvr sesh = getSesh(seshName);
		// sesh.joinSesh(audName, sender, haudDisconnedAcpter);
		sesh.joinSesh(audName, sender);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAudIntf#joinChnl(
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void joinChnl(String seshName, String chnlName, String audName)
			throws CumExcpSeshNotExist, CumExcpChnlNotExist, CumExcpAudExists,
			CumExcpAudNotExist, CumExcpXMLGenFailed {
		SeshPresSvr sesh = getSesh(seshName);
		sesh.joinChnl(chnlName, audName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAudIntf#rjctChnl(
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void rjctChnl(String seshName, String chnlName, String audName)
			throws CumExcpSeshNotExist, CumExcpChnlNotExist, CumExcpAudExists,
			CumExcpXMLGenFailed {
		SeshPresSvr sesh = getSesh(seshName);
		sesh.rjctChnl(chnlName, audName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAudIntf#lvChnl(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void lvChnl(String seshName, String chnlName, String audName)
			throws CumExcpSeshNotExist, CumExcpChnlNotExist,
			CumExcpAudNotExist, CumExcpXMLGenFailed {
		SeshPresSvr sesh = getSesh(seshName);
		sesh.lvChnl(chnlName, audName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAudIntf#lvSesh(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public void lvSesh(String seshName, String audName)
			throws CumExcpSeshNotExist, CumExcpAudNotExist, CumExcpXMLGenFailed {
		SeshPresSvr sesh = getSesh(seshName);
		sesh.lvSesh(audName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAudIntf#getRsc(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ChnlRscIntf getRsc(String seshName, String chnlName, String rscName)
			throws CumExcpSeshNotExist, CumExcpChnlNotExist, CumExcpRscNotExist {
		SeshPresSvr sesh = getSesh(seshName);
		ChnlRscIntf rsc = sesh.getRsc(chnlName, rscName);
		return rsc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrDisconIntf#audDisconned
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public void audDisconned(String seshName, String audName)
			throws CumExcpSeshNotExist, CumExcpXMLGenFailed {
		// TODO Auto-generated method stub
		SeshPresSvr sesh = getSesh(seshName);
		sesh.audDisconned(audName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrDisconIntf#
	 * prestrDisconned(java.lang.String)
	 */
	@Override
	public void prestrDisconned(String seshName) throws CumExcpSeshNotExist,
			CumExcpXMLGenFailed {
		// TODO Auto-generated method stub
		SeshPresSvr sesh = getSesh(seshName);
		sesh.prestrDisconned();
	}

}
