/**
 * 
 */
package jp.happyhacking70.cum.presSvr.seshLyr.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import jp.happyhacking70.cum.cmd.CmdChnlAbst;
import jp.happyhacking70.cum.cmd.XMLableCmdIntf;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdAudDisconned;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdClsChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdClsSesh;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdJoinChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdJoinSesh;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdLvChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdLvSesh;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdRegChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdRjctChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdSeshDisconned;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscNull;
import jp.happyhacking70.cum.presSvr.audLyr.Aud;
import jp.happyhacking70.cum.presSvr.audLyr.AudIntf;
import jp.happyhacking70.cum.presSvr.chnlLyr.ChnlPresSvr;
import jp.happyhacking70.cum.presSvr.chnlLyr.ChnlPresSvrPrestrIntf;
import jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshPresSvrAudIntf;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshPresSvrDisconIntf;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshPresSvrPrestrIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class SeshPresSvr implements SeshPresSvrAudIntf, SeshPresSvrPrestrIntf,
		SeshPresSvrDisconIntf {
	protected String seshName;
	protected CmdSenderIntf sender;
	protected ConcurrentHashMap<String, ChnlPresSvr> chnls = new ConcurrentHashMap<String, ChnlPresSvr>();
	protected ConcurrentHashMap<String, AudIntf> auds = new ConcurrentHashMap<String, AudIntf>();

	/**
	 * @param seshName
	 * @param sender
	 */
	public SeshPresSvr(String seshName, CmdSenderIntf sender) {
		super();
		this.seshName = seshName;
		this.sender = sender;
	}

	/**
	 * @return session name
	 */
	public String getSeshName() {
		return seshName;
	}

	/**
	 * @param audName
	 * @return audience
	 * @throws CumExcpAudNotExist
	 */
	protected AudIntf getAud(String audName) throws CumExcpAudNotExist {
		AudIntf aud = auds.get(audName);
		if (aud == null) {
			throw new CumExcpAudNotExist(seshName, new CumExcpAudNotExist("",
					audName));
		}
		return aud;
	}

	/**
	 * @param chnlName
	 * @return channel
	 * @throws CumExcpChnlNotExist
	 */
	protected ChnlPresSvr getChnl(String chnlName) throws CumExcpChnlNotExist {

		ChnlPresSvr chnl = chnls.get(chnlName);
		if (chnl == null) {
			throw new CumExcpChnlNotExist(seshName, chnlName);
		}
		return chnl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.seshLyr.SeshPrestrIntf#regChnl(java.lang
	 * .String, java.util.ArrayList)
	 */
	@Override
	synchronized public void regChnl(String chnlType, String chnlName,
			List<ChnlRscIntf> chnlRsces) throws CumExcpChnlExists,
			CumExcpRscExists, CumExcpRscNull, CumExcpXMLGenFailed {

		if (chnls.containsKey(chnlName) == true) {
			throw new CumExcpChnlExists(seshName, chnlName);
		}

		// Create new channel
		ChnlPresSvr newChnl = null;
		try {
			newChnl = new ChnlPresSvr(seshName, chnlType, chnlName, chnlRsces);
		} catch (CumExcpRscExists e) {
			throw new CumExcpRscExists(seshName, e);
		} catch (CumExcpRscNull e) {
			throw new CumExcpRscNull(seshName, e);
		}
		chnls.put(chnlName, newChnl);

		NtfyCmdRegChnl cmd = new NtfyCmdRegChnl(seshName, chnlType, chnlName);
		for (ChnlRscIntf rsc : chnlRsces) {
			cmd.addRscData(rsc);
		}

		// Send NtfyCmdRegChnl to session audiences
		for (AudIntf aud : auds.values()) {
			aud.sendCmd(cmd);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.seshLyr.SeshPrestrIntf#sendChnlCmd(jp.
	 * happyhacking70.cum3.cmd.CmdChnlAbst, java.lang.String)
	 */
	@Override
	synchronized public void sendChnlCmd(CmdChnlAbst cmd, String audName)
			throws CumExcpChnlNotExist, CumExcpAudNotExist, CumExcpXMLGenFailed {

		String chnlName = cmd.getChnlName();

		ChnlPresSvr chnl;
		chnl = getChnl(chnlName);

		// send command to specific audience
		try {
			chnl.sendChnlCmd(cmd, audName);
		} catch (CumExcpAudNotExist e) {
			throw new CumExcpAudNotExist(seshName, e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.seshLyr.SeshPrestrIntf#sendChnlCmd(jp.
	 * happyhacking70.cum3.cmd.CmdChnlAbst)
	 */
	@Override
	synchronized public void sendChnlCmd(CmdChnlAbst cmd)
			throws CumExcpChnlNotExist, CumExcpAudNotExist, CumExcpXMLGenFailed {

		String chnlName = cmd.getChnlName();
		ChnlPresSvr chnl;

		chnl = getChnl(chnlName);

		// send command to all channel audiences
		// delegate to channel
		try {
			chnl.sendChnlCmd(cmd);
		} catch (CumExcpAudNotExist e) {
			throw new CumExcpAudNotExist(seshName, e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.seshLyr.SeshPrestrIntf#clsChnl(java.lang
	 * .String)
	 */
	@Override
	synchronized public void clsChnl(String chnlName)
			throws CumExcpChnlNotExist, CumExcpXMLGenFailed {
		ChnlPresSvr chnl;

		chnl = getChnl(chnlName);

		// send NtfyCmdClsChnl to all session audiences
		// delegate to channel

		NtfyCmdClsChnl cmd = chnl.getNtfyCmdClsChnl();
		for (AudIntf aud : auds.values()) {
			aud.sendCmd(cmd);
		}

		// close channel
		chnls.remove(chnlName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.seshLyr.SeshPresSvrAudIntf#joinSesh(java
	 * .lang.String, jp.happyhacking70.cum3.presSvr.comLyr.CmdSenderAbst)
	 */
	@Override
	synchronized public void joinSesh(String audName, CmdSenderIntf sender)
			throws CumExcpAudExists, CumExcpXMLGenFailed {

		if (auds.containsKey(audName) == true) {
			throw new CumExcpAudExists(seshName, new CumExcpAudExists("",
					audName));
		}

		AudIntf aud = new Aud(audName, sender);
		// add audience to session
		auds.put(audName, aud);

		// send NtfyCmdJoinSesh to presenter
		sendCmdToPrestr(new NtfyCmdJoinSesh(seshName, audName));
		// send NtfyCmdRegChnl to audience for all existing channels
		for (ChnlPresSvrPrestrIntf chnl : chnls.values()) {
			aud.sendCmd(chnl.getNtfyCmdRegChnl());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.seshLyr.SeshAudIntf#joinChnl(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	synchronized public void joinChnl(String chnlName, String audName)
			throws CumExcpChnlNotExist, CumExcpAudExists, CumExcpAudNotExist,
			CumExcpXMLGenFailed {
		ChnlPresSvr chnl;
		chnl = getChnl(chnlName);
		AudIntf aud;
		try {
			aud = getAud(audName);
		} catch (CumExcpAudNotExist e) {
			throw new CumExcpAudNotExist(seshName, chnlName, audName);
		}

		// add audience to channel
		NtfyCmdJoinChnl cmd;
		try {
			cmd = chnl.joinChnl(aud);
		} catch (CumExcpAudExists e) {
			throw new CumExcpAudExists(seshName, e);
		}

		// send NtfyCmdJoinChnl to presenter
		sendCmdToPrestr(cmd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.seshLyr.SeshAudIntf#lvChnl(java.lang.String
	 * , java.lang.String, java.lang.String)
	 */
	@Override
	synchronized public void lvChnl(String chnlName, String audName)
			throws CumExcpChnlNotExist, CumExcpAudNotExist, CumExcpXMLGenFailed {
		ChnlPresSvr chnl = getChnl(chnlName);
		AudIntf aud = null;
		try {
			aud = getAud(audName);
		} catch (CumExcpAudNotExist e) {
			throw new CumExcpAudNotExist(seshName, chnlName, audName);
		}

		// remove audience from channel
		NtfyCmdLvChnl cmd = chnl.lvChnl(aud);

		// send NtfyCmdLvChnl to presenter
		sendCmdToPrestr(cmd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.seshLyr.SeshAudIntf#getRsc(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	synchronized public ChnlRscIntf getRsc(String chnlName, String rscName)
			throws CumExcpChnlNotExist, CumExcpRscNotExist {
		ChnlPresSvr chnl;
		try {
			chnl = getChnl(chnlName);
		} catch (CumExcpChnlNotExist e) {
			throw new CumExcpChnlNotExist(seshName, chnlName);
		}
		ChnlRscIntf rsc = null;
		try {
			rsc = chnl.getRsc(rscName);
		} catch (CumExcpRscNotExist e) {
			throw new CumExcpRscNotExist(seshName, e);
		}
		return rsc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.seshLyr.SeshAudIntf#lvSesh(java.lang.String
	 * )
	 */
	@Override
	synchronized public void lvSesh(String audName) throws CumExcpAudNotExist,
			CumExcpXMLGenFailed {

		AudIntf aud = null;
		try {
			aud = getAud(audName);
		} catch (CumExcpAudNotExist e) {
			throw new CumExcpAudNotExist(seshName, "", audName);
		}

		// remove audience from session
		auds.remove(audName);

		for (ChnlPresSvr chnl : chnls.values()) {

			try {
				// remove audience from all channels
				// no need to send NtfyCmdLvChnl to presenter because presenter
				// takes care of it
				chnl.lvChnl(aud);
			} catch (CumExcpAudNotExist e) {
				// Just fine. Audience may have rejected some channel
			}
		}
		// send NtfyCmdLvSesh to presenter
		NtfyCmdLvSesh cmd = new NtfyCmdLvSesh(seshName, audName);

		sendCmdToPrestr(cmd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.presSvr.seshLyr.SeshPrestrIntf#clsSesh()
	 */
	@Override
	synchronized public void clsSesh() throws CumExcpXMLGenFailed {
		NtfyCmdClsSesh cmd = new NtfyCmdClsSesh(seshName);

		// send NtfyCmdClsSesh to all session audiences
		for (AudIntf aud : auds.values()) {
			aud.sendCmd(cmd);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.seshLyr.SeshAudIntf#rjctChnl(java.lang
	 * .String)
	 */
	@Override
	public void rjctChnl(String chnlName, String audName)
			throws CumExcpChnlNotExist, CumExcpAudExists, CumExcpXMLGenFailed {
		ChnlPresSvr chnl = getChnl(chnlName);
		try {
			chnl.rjctChnl(audName);
		} catch (CumExcpAudExists e) {
			throw new CumExcpAudExists(seshName, e);
		}

		NtfyCmdRjctChnl cmd = new NtfyCmdRjctChnl(seshName, chnl.getChnlType(),
				chnlName, audName);

		sendCmdToPrestr(cmd);
	}

	/**
	 * @param cmd
	 * @throws CumExcpXMLGenFailed
	 */
	private void sendCmdToPrestr(XMLableCmdIntf cmd) throws CumExcpXMLGenFailed {
		sender.sendCmd(cmd);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.seshLyr.SeshPresSvrDisconIntf#audDisconned
	 * (java.lang.String)
	 */
	@Override
	public void audDisconned(String audName) throws CumExcpXMLGenFailed {

		// TODO Auto-generated method stub
		NtfyCmdAudDisconned cmd = new NtfyCmdAudDisconned(audName);
		sender.sendCmd(cmd);

		for (ChnlPresSvr chnl : chnls.values()) {
			chnl.audDisconnected(audName);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.seshLyr.SeshPresSvrDisconIntf#prestrDisconned
	 * ()
	 */
	@Override
	public void prestrDisconned() throws CumExcpXMLGenFailed {
		NtfyCmdSeshDisconned cmd = new NtfyCmdSeshDisconned(seshName);
		for (AudIntf aud : auds.values()) {
			aud.sendCmd(cmd);
		}
		// TODO Auto-generated method stub

	}
}
