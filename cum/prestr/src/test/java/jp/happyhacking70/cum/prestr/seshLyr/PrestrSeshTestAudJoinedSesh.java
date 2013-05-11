/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import static org.junit.Assert.assertEquals;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpIgnoreSeshStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrSeshTestAudJoinedSesh extends PrestrSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_audJoinedSesh_INIT() throws CumExcpAudExists,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = getSesh();
		sesh.audJoinedSesh(audNameA);
	}

	@Test
	public void TEST_audJoinedSesh_REGING() throws TestExcp, CumExcpAudExists,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReging();
		sesh.audJoinedSesh(audNameA);
		assertEquals(audNameA, seshView.getAudJoinedName());
	}

	@Test(expected = CumExcpAudExists.class)
	public void TEST_audJoinedSesh_REGING_AUDEXISTS() throws TestExcp,
			CumExcpAudExists, CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReging();
		try {
			sesh.audJoinedSesh(audNameA);
		} catch (CumExcpAudExists e) {
			e.printStackTrace();
			throw new TestExcp("audJoinedSesh");
		} catch (CumExcpIllegalSeshStatus e) {
			e.printStackTrace();
			throw new TestExcp("audJoinedSesh");
		} catch (CumExcpIgnoreSeshStatus e) {
			e.printStackTrace();
			throw new TestExcp("audJoinedSesh");
		}
		sesh.audJoinedSesh(audNameA);
	}

	@Test
	public void TEST_audJoinedSesh_REGED() throws TestExcp, CumExcpAudExists,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReged();

		sesh.audJoinedSesh(audNameC);
		assertEquals(audNameC, seshView.getAudJoinedName());
	}

	@Test(expected = CumExcpAudExists.class)
	public void TEST_audJoinedSesh_REGED_AUDEXISTS() throws TestExcp,
			CumExcpAudExists, CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReged();

		sesh.audJoinedSesh(audNameA);
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audJoinedSesh_CLSING() throws TestExcp, CumExcpAudExists,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsing();

		sesh.audJoinedSesh(audNameC);
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audJoinedSesh_CLSED() throws TestExcp, CumExcpAudExists,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsed();
		sesh.audJoinedSesh(audNameC);
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audJoinedSesh_DISCNED() throws TestExcp, CumExcpAudExists,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoDiscned();
		sesh.audJoinedSesh(audNameC);
	}

}
