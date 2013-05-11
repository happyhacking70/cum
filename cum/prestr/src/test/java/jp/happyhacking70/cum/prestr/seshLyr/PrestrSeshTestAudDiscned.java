/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIgnoreSeshStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatuMulti;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrSeshTestAudDiscned extends PrestrSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_audDiscned_INIT() throws CumExcpIllegalSeshStatus,
			CumExcpAudNotExist, CumExcpIllegalChnlStatuMulti,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = getSesh();
		sesh.audDiscned(audNameA);
	}

	@Test
	public void TEST_audDiscned_REGING_OK() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpAudNotExist,
			CumExcpIllegalChnlStatuMulti, CumExcpIgnoreSeshStatus,
			CumExcpAudExists, CumExcpChnlNotExist, CumExcpAudExists,
			CumExcpIllegalChnlStatus {
		PrestrSesh sesh = gotoReging();

		sesh.audJoinedSesh(audNameA);

		sesh.audDiscned(audNameA);

		assertEquals(audNameA, seshView.getAudDscnedName());
	}

	@Test(expected = CumExcpAudNotExist.class)
	public void TEST_audDiscned_REGING_NOAUD() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpAudNotExist,
			CumExcpIllegalChnlStatuMulti, CumExcpIgnoreSeshStatus,
			CumExcpAudExists, CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		PrestrSesh sesh = gotoReging();

		sesh.audJoinedSesh(audNameA);

		sesh.audDiscned(audNameB);

	}

	@Test
	public void TEST_audDiscned_REGED_OKA() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpAudNotExist,
			CumExcpIllegalChnlStatuMulti, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReged();
		sesh.audDiscned(audNameA);
		assertEquals(audNameA, seshView.getAudDscnedName());
		assertEquals(audNameA, chnlViewA.getAudDiscned());

	}

	@Test
	public void TEST_audDiscned_REGED_OKB() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpAudNotExist,
			CumExcpIllegalChnlStatuMulti, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReged();

		sesh.audDiscned(audNameB);
		assertEquals(audNameB, seshView.getAudDscnedName());
		assertNull(chnlViewA.getAudDiscned());

	}

	@Test(expected = CumExcpAudNotExist.class)
	public void TEST_audDiscned_REGED_NOAUD() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpAudNotExist,
			CumExcpIllegalChnlStatuMulti, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReged();

		sesh.audDiscned("XXX");

	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audDiscned_CLSING() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpAudNotExist,
			CumExcpIllegalChnlStatuMulti, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsing();
		sesh.audDiscned(audNameA);
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audDiscned_CLSED() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpAudNotExist,
			CumExcpIllegalChnlStatuMulti, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsed();
		sesh.audDiscned(audNameA);
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audDiscned_DISCNED() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpAudNotExist,
			CumExcpIllegalChnlStatuMulti, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoDiscned();
		sesh.audDiscned(audNameA);
	}

}
