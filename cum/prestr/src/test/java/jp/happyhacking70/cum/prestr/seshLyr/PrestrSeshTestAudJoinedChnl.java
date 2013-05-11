/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import jp.happyhacking70.cum.excp.prestr.CumExcpAudExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIgnoreSeshStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrSeshTestAudJoinedChnl extends PrestrSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_audJoinedChnl_INIT() throws CumExcpAudNotExist,
			CumExcpChnlNotExist, CumExcpAudExists, CumExcpIllegalChnlStatus,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = getSesh();
		sesh.audJoinedChnl(chnlNameA, audNameA);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_audJoinedChnl_REGING() throws TestExcp,
			CumExcpAudNotExist, CumExcpChnlNotExist, CumExcpAudExists,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReging();
		sesh.audJoinedChnl(chnlNameA, audNameA);

	}

	@Test
	public void TEST_audJoinedChnl_REGED() throws TestExcp, CumExcpAudNotExist,
			CumExcpChnlNotExist, CumExcpAudExists, CumExcpIllegalChnlStatus,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReged();

		sesh.audJoinedSesh(audNameC);
		sesh.audJoinedChnl(chnlNameA, audNameC);

	}

	@Test(expected = CumExcpChnlNotExist.class)
	public void TEST_audJoinedChnl_REGED_NOCHNL() throws TestExcp,
			CumExcpAudNotExist, CumExcpChnlNotExist, CumExcpAudExists,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReged();

		sesh.audJoinedSesh(audNameC);
		sesh.audJoinedChnl(chnlNameC, audNameC);

	}

	@Test(expected = CumExcpAudNotExist.class)
	public void TEST_audJoinedChnl_REGED_NOAUD() throws TestExcp,
			CumExcpAudNotExist, CumExcpChnlNotExist, CumExcpAudExists,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReged();

		sesh.audJoinedChnl(chnlNameA, audNameC);

	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audJoinedChnl_CLSING() throws TestExcp,
			CumExcpAudNotExist, CumExcpChnlNotExist, CumExcpAudExists,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsing();
		sesh.audJoinedChnl(chnlNameA, audNameA);
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audJoinedChnl_CLSED() throws TestExcp, CumExcpAudNotExist,
			CumExcpChnlNotExist, CumExcpAudExists, CumExcpIllegalChnlStatus,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsed();
		sesh.audJoinedChnl(chnlNameA, audNameA);
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audJoinedChnl_DISCNED() throws TestExcp,
			CumExcpAudNotExist, CumExcpChnlNotExist, CumExcpAudExists,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoDiscned();

		sesh.audJoinedChnl(chnlNameA, audNameA);

	}

}
