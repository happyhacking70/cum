/**
 * 
 */
package jp.happyhacking70.cum.cmd.test;

import java.util.concurrent.ConcurrentLinkedQueue;

import jp.happyhacking70.cum.cmd.XMLableCmdIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class DummySender implements CmdSenderIntf {
	ConcurrentLinkedQueue<String> cmdsSent = new ConcurrentLinkedQueue<String>();

	/**
	 * @return
	 */
	public String pollCmd() {
		return cmdsSent.poll();
	}

	protected void sendCmd(String xmledCmd) {
		cmdsSent.add(xmledCmd);
	}

	public void clearQueue() {
		cmdsSent = new ConcurrentLinkedQueue<String>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.comLyr.CmdSenderIntf#sendCmd(jp.happyhacking70
	 * .cum3.cmd.XMLableCmdIntf)
	 */
	@Override
	public void sendCmd(XMLableCmdIntf cmd) {
		try {
			sendCmd(cmd.toXmlStr());
		} catch (CumExcpXMLGenFailed e) {

		}

	}

}
