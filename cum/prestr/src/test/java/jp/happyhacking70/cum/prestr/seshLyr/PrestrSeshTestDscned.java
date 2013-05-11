/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import static org.junit.Assert.assertTrue;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrSeshTestDscned extends PrestrSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_discned_INIT() throws CumExcpIllegalSeshStatus {
		PrestrSesh sesh = getSesh();

		sesh.discned();
	}

	@Test
	public void TEST_discned_REGING() throws TestExcp, CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoReging();
		sesh.discned();

		assertTrue(seshView.isDscned());
	}

	@Test
	public void TEST_discned_REGED() throws TestExcp, CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoReged();
		sesh.discned();

		assertTrue(seshView.isDscned());
		assertTrue(chnlViewA.isDiscned());
		assertTrue(chnlViewB.isDiscned());
	}

	@Test
	public void TEST_discned_CLSING() throws TestExcp, CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoClsing();
		sesh.discned();
		assertTrue(seshView.isDscned());
		assertTrue(chnlViewA.isDiscned());
		assertTrue(chnlViewB.isDiscned());
	}

	@Test
	public void TEST_discned_CLSED() throws TestExcp, CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoClsed();
		sesh.discned();
		assertTrue(seshView.isDscned());
		assertTrue(chnlViewA.isDiscned());
		assertTrue(chnlViewB.isDiscned());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_discned_DISCNED() throws TestExcp,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoDiscned();

		sesh.discned();
	}
}
