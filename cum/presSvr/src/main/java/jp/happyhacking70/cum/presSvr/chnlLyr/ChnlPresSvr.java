/**
 * 
 */
package jp.happyhacking70.cum.presSvr.chnlLyr;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import jp.happyhacking70.cum.cmd.CmdChnlAbst;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdClsChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdJoinChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdLvChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdRegChnl;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscNull;
import jp.happyhacking70.cum.presSvr.audLyr.AudIntf;

/**
 * Channel in Presentation Server<BR>
 * Maintains:
 * <UL>
 * <LI>name of session which this channel belongs to</LI>
 * <LI>name of this channel</LI>
 * <LI>channel resources</LI>
 * <LI>audiences</LI>
 * </UL>
 * 
 * @author happyhacking70@gmail.com
 * 
 */
public class ChnlPresSvr implements ChnlPresSvrAudIntf, ChnlPresSvrPrestrIntf {
	protected String seshName;
	protected String chnlName;
	protected String chnlType;
	protected ConcurrentHashMap<String, ChnlRscIntf> rsces = new ConcurrentHashMap<String, ChnlRscIntf>();
	protected ConcurrentHashMap<String, AudIntf> auds = new ConcurrentHashMap<String, AudIntf>();

	/**
	 * @param seshName
	 * @param chnlType
	 * @param chnlName
	 * @param rsces
	 * @throws CumExcpRscExists
	 * @throws CumExcptNullRsces
	 * @throws CumExcpRscNull
	 */
	public ChnlPresSvr(String seshName, String chnlType, String chnlName,
			List<ChnlRscIntf> rsces) throws CumExcpRscExists, CumExcpRscNull {
		super();
		this.seshName = seshName;
		this.chnlName = chnlName;
		this.chnlType = chnlType;
		addRsces(rsces);
	}

	/**
	 * add resources
	 * 
	 * @param rsces
	 * @throws CumExcpRscExists
	 * @throws CumExcptNullRsces
	 * @throws CumExcpRscNull
	 */
	protected void addRsces(List<ChnlRscIntf> rsces) throws CumExcpRscExists,
			CumExcpRscNull {

		if (rsces != null) {
			for (ChnlRscIntf rsc : rsces) {
				addRsc(rsc);
			}
		}
	}

	/**
	 * add resource
	 * 
	 * @param rsc
	 * @throws CumExcpRscExists
	 * @throws CumExcpRscNull
	 */
	protected void addRsc(ChnlRscIntf rsc) throws CumExcpRscExists,
			CumExcpRscNull {
		if (rsc == null) {
			throw new CumExcpRscNull(chnlName);
		}
		if (rsces.containsKey(rsc.getName())) {
			throw new CumExcpRscExists(chnlName, rsc.getName());
		}

		rsces.put(rsc.getName(), rsc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.chnlLyr.ChnlPrestrIntf#sendChnlCmd(jp.
	 * happyhacking70.cum3.cmd.CmdChnlAbst,
	 * jp.happyhacking70.cum3.presSvr.audLyr.AudIntf)
	 */
	@Override
	synchronized public void sendChnlCmd(CmdChnlAbst cmd, String audName)
			throws CumExcpAudNotExist, CumExcpXMLGenFailed {

		if (auds.containsKey(audName) == false) {
			throw new CumExcpAudNotExist(chnlName, audName);
		}
		AudIntf aud = auds.get(audName);
		aud.sendCmd(cmd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.chnlLyr.ChnlPrestrIntf#sendChnlCmd(jp.
	 * happyhacking70.cum3.cmd.CmdChnlAbst)
	 */
	@Override
	synchronized public void sendChnlCmd(CmdChnlAbst cmd)
			throws CumExcpAudNotExist, CumExcpXMLGenFailed {
		for (AudIntf aud : auds.values()) {
			sendChnlCmd(cmd, aud.getAudName());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.chnlLyr.ChnlAudIntf#getRsc(java.lang.String
	 * )
	 */
	@Override
	synchronized public ChnlRscIntf getRsc(String rscName)
			throws CumExcpRscNotExist {
		ChnlRscIntf rsc = rsces.get(rscName);

		if (rsc == null) {
			throw new CumExcpRscNotExist(chnlName, rscName);
		}

		return rsc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.chnlLyr.ChnlAudIntf#joinChnl(jp.happyhacking70
	 * .cum3.presSvr.audLyr.AudIntf)
	 */
	@Override
	synchronized public NtfyCmdJoinChnl joinChnl(AudIntf aud)
			throws CumExcpAudExists {
		if (auds.containsKey(aud.getAudName())) {
			throw new CumExcpAudExists(chnlName, aud.getAudName());
		}

		auds.put(aud.getAudName(), aud);

		return new NtfyCmdJoinChnl(seshName, chnlType, chnlName,
				aud.getAudName());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.chnlLyr.ChnlAudIntf#lvChnl(jp.happyhacking70
	 * .cum3.presSvr.audLyr.AudIntf)
	 */
	@Override
	synchronized public NtfyCmdLvChnl lvChnl(AudIntf aud)
			throws CumExcpAudNotExist {
		if (auds.containsKey(aud.getAudName()) == false) {
			throw new CumExcpAudNotExist(chnlName, aud.getAudName());
		}

		auds.remove(aud.getAudName());
		return new NtfyCmdLvChnl(seshName, chnlType, chnlName, aud.getAudName());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.presSvr.chnlLyr.ChnlPresSvrPrestrIntf#
	 * getNtfyCmdRegChnl()
	 */
	@Override
	public NtfyCmdRegChnl getNtfyCmdRegChnl() {
		NtfyCmdRegChnl cmd = new NtfyCmdRegChnl(seshName, chnlType, chnlName);
		for (ChnlRscIntf rsc : rsces.values()) {
			cmd.addRscData(rsc);
		}
		return cmd;
	}

	/**
	 * @param audName
	 * @throws CumExcpAudExists
	 */
	@Override
	public void rjctChnl(String audName) throws CumExcpAudExists {
		if (auds.containsKey(audName) == true) {
			throw new CumExcpAudExists(chnlName, audName);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.chnlLyr.ChnlPrestrIntf#getNtfyCmdClsChnl()
	 */
	@Override
	public NtfyCmdClsChnl getNtfyCmdClsChnl() {
		return new NtfyCmdClsChnl(seshName, chnlType, chnlName);
	}

	/**
	 * @param audName
	 */
	public void audDisconnected(String audName) {
		// TODO Auto-generated method stub
		if (auds.containsKey(audName)) {
			auds.remove(audName);
		}
	}

	public String getChnlType() {
		return chnlType;
	}

}
