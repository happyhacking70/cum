/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import static org.junit.Assert.assertEquals;
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
public class PrestrSeshAudRjctedChnlTest extends PrestrSeshAbstTest {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_audRjctedChnl_INIT() throws CumExcpAudNotExist,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus, CumExcpAudExists {
		PrestrSesh sesh = getSesh();
		sesh.audRjctedChnl(chnlNameA, audNameA);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_audRjctedChnl_REGING() throws TestExcp,
			CumExcpAudNotExist, CumExcpChnlNotExist, CumExcpIllegalChnlStatus,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus, CumExcpAudExists {
		PrestrSesh sesh = gotoReging();
		sesh.audRjctedChnl(chnlNameA, audNameA);
	}

	@Test
	public void TEST_audRjctedChnl_REGED() throws TestExcp, CumExcpAudExists,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus,
			CumExcpAudNotExist, CumExcpChnlNotExist, CumExcpIllegalChnlStatus,
			CumExcpAudExists {
		PrestrSesh sesh = gotoReged();
		sesh.audJoinedSesh(audNameC);
		sesh.audRjctedChnl(chnlNameA, audNameC);
		assertEquals(audNameC, chnlViewA.getAudRjcted());
	}

	@Test(expected = CumExcpAudExists.class)
	public void TEST_audRjctedChnl_REGED_AUDEXISTS() throws TestExcp,
			CumExcpAudExists, CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus, CumExcpAudNotExist, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus, CumExcpAudExists {
		PrestrSesh sesh = gotoReged();
		sesh.audRjctedChnl(chnlNameA, audNameA);
	}

	@Test(expected = CumExcpAudNotExist.class)
	public void TEST_audRjctedChnl_REGED_AUDNOTEXISTS() throws TestExcp,
			CumExcpAudExists, CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus, CumExcpAudNotExist, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus, CumExcpAudExists {
		PrestrSesh sesh = gotoReged();
		sesh.audRjctedChnl(chnlNameA, audNameC);
	}

	@Test(expected = CumExcpAudNotExist.class)
	public void TEST_audRjctedChnl_REGED_CHNLNOTEXISTS() throws TestExcp,
			CumExcpAudExists, CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus, CumExcpAudNotExist, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus, CumExcpAudExists {
		PrestrSesh sesh = gotoReged();
		sesh.audRjctedChnl(chnlNameC, audNameC);
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audRjctedChnl_CLSING() throws TestExcp,
			CumExcpAudNotExist, CumExcpChnlNotExist, CumExcpIllegalChnlStatus,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus, CumExcpAudExists {
		PrestrSesh sesh = gotoClsing();
		sesh.audRjctedChnl(chnlNameA, audNameA);
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audRjctedChnl_CLSED() throws TestExcp, CumExcpAudNotExist,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus, CumExcpAudExists {
		PrestrSesh sesh = gotoClsed();

		sesh.audRjctedChnl(chnlNameA, audNameA);
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audRjctedChnl_DISCNED() throws TestExcp,
			CumExcpAudNotExist, CumExcpChnlNotExist, CumExcpIllegalChnlStatus,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus, CumExcpAudExists {
		PrestrSesh sesh = gotoDiscned();
		sesh.audRjctedChnl(chnlNameA, audNameA);
	}

}
