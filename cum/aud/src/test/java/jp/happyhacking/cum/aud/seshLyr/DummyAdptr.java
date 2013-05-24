/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import java.util.ArrayList;

import jp.happyhacking.cum.aud.adptrLyr.AudAdptrIntf;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscImg;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class DummyAdptr implements AudAdptrIntf {
	protected ArrayList<String[]> rscesFetched = new ArrayList<String[]>();
	protected String chnlNameJoin;
	protected String seshNameToJoinChnl;
	protected String audNameToJoinChnl;
	protected String chnlTypeToJoin;
	protected String seshNameToJin;
	protected String audNameToJoinSesh;

	public String getSeshNameToJin() {
		return seshNameToJin;
	}

	public String getAudNameToJoinSesh() {
		return audNameToJoinSesh;
	}

	public String getChnlTypeToJoin() {
		return chnlTypeToJoin;
	}

	public String getChnlNameJoin() {
		return chnlNameJoin;
	}

	public String getSeshNameToJoinChnl() {
		return seshNameToJoinChnl;
	}

	public String getAudNameToJoinChnl() {
		return audNameToJoinChnl;
	}

	public ArrayList<String[]> getRscesFetched() {
		return rscesFetched;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.adptrLyr.AudAdptrIntf#joinSesh(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void joinSesh(String seshName, String audName) {
		this.seshNameToJin = seshName;
		this.audNameToJoinSesh = audName;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.adptrLyr.AudAdptrIntf#lvSesh(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void lvSesh(String seshName, String audName) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.adptrLyr.AudAdptrIntf#joinChnl(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void joinChnl(String seshName, String chnlType, String chnlName,
			String audName) {
		this.chnlNameJoin = chnlName;
		this.seshNameToJoinChnl = seshName;
		this.audNameToJoinChnl = audName;
		this.chnlTypeToJoin = chnlType;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.adptrLyr.AudAdptrIntf#lvChnl(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void lvChnl(String seshName, String chnlType, String chnlName) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.adptrLyr.AudAdptrIntf#rjctChnl(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void rjctChnl(String seshName, String chnlType, String chnlName) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.adptrLyr.AudAdptrIntf#fetchRsc(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ChnlRscIntf fetchRsc(String seshName, String chnlType,
			String chnlName, String rscName) {

		String[] rsc = { seshName, chnlType, chnlName, rscName };
		rscesFetched.add(rsc);

		return new ChnlRscImg(rscName, null);
	}
}
