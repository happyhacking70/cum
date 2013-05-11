/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscJustName;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpSeshDiscned;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrChnlTest {
	static final String chnlName = "testChannel";
	static final ChnlRscJustName rscA = new ChnlRscJustName("a");
	static final ChnlRscJustName rscB = new ChnlRscJustName("b");
	static final String audName = "testAudience";
	protected DummyPrestrSeshIntfForChnlView sesh;
	protected DummyPrestrChnlViewIntf chnlView;
	static final String actionName = "testAction";
	static final HashMap<String, String> params = new HashMap<String, String>();

	protected PrestrChnl getChnl() {
		HashMap<String, ChnlRscIntf> rsces = new HashMap<String, ChnlRscIntf>();

		rsces.put(rscA.getName(), rscA);
		rsces.put(rscB.getName(), rscB);
		chnlView = new DummyPrestrChnlViewIntf();
		sesh = new DummyPrestrSeshIntfForChnlView();
		PrestrChnl chnl = new PrestrChnl(chnlName, rsces, chnlView, sesh);

		return chnl;
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#clsChnl()}.
	 * 
	 * @throws CumExcpChnlCannotBeClsed
	 * @throws CumExcpChnlCannotBeReged
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpSeshDiscned
	 * @throws CumExcpIllegalSeshStatus
	 */
	@Test
	public void testClsChnl() throws CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus,
			CumExcpSeshDiscned {
		PrestrChnl chnl = getChnl();

		chnl.chnlReged();
		chnl.clsChnl();

		assertEquals(chnlName, sesh.getChnlToClose());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#clsChnl()}.
	 * 
	 * @throws CumExcpChnlCannotBeClsed
	 * @throws CumExcpChnlCannotBeReged
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpSeshDiscned
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpIllegalChnlStatus
	 */
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testClsChnl_CLOSING() throws CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus,
			CumExcpSeshDiscned {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.clsChnl();
		} catch (Exception e) {
			fail("XXX");
		}

		chnl.clsChnl();

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testClsChnl_DISCNED() throws CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus,
			CumExcpSeshDiscned {
		PrestrChnl chnl = getChnl();
		chnl.discnded();
		chnl.clsChnl();

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#clsChnl()}.
	 * 
	 * @throws CumExcpSeshDiscned
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpIllegalChnlStatus
	 * 
	 * @throws CumExcpChnlCannotBeClsed
	 * @throws CumExcpChnlCannotBeReged
	 * @throws CumExcpChnlNotExist
	 */
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testClsChnl_CLOSED() throws CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus,
			CumExcpSeshDiscned {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.clsChnl();
			chnl.chnlClsed();
		} catch (Exception e) {
			fail("XXX");
		}

		chnl.clsChnl();

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#sendChnlCmd(java.lang.String, java.util.HashMap)}
	 * .
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpSeshDiscned
	 * @throws CumExcpIllegalSeshStatus
	 * 
	 * @throws CumExcpChnlCannotBeReged
	 * @throws CumExcpChnlCmdCannotBeSent
	 * @throws CumExcpChnlNotExist
	 */
	@Test
	public void testSendChnlCmd() throws CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus, CumExcpSeshDiscned {
		PrestrChnl chnl = getChnl();
		chnl.chnlReged();
		chnl.sendChnlCmd(actionName, params);

		assertEquals(actionName, sesh.getActionName());
		assertEquals(sesh.getParams(), params);
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testSendChnlCmd_REGING() throws CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus,
			CumExcpSeshDiscned {
		PrestrChnl chnl = getChnl();
		chnl.sendChnlCmd(actionName, params);
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testSendChnlCmd_DISCNED() throws CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus,
			CumExcpSeshDiscned {
		PrestrChnl chnl = getChnl();
		chnl.discnded();
		chnl.sendChnlCmd(actionName, params);
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testSendChnlCmd_CLOSING() throws CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus,
			CumExcpSeshDiscned {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.clsChnl();
		} catch (Exception e1) {
			fail("XXX");
		}

		chnl.sendChnlCmd(actionName, params);
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testSendChnlCmd_CLOSED() throws CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus,
			CumExcpSeshDiscned {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.clsChnl();
			chnl.chnlClsed();
		} catch (Exception e) {
			fail("XXX");
		}

		chnl.sendChnlCmd(actionName, params);
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#audJoinedChnl(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpAudExist
	 */
	@Test
	public void testAudJoinedChnl() throws CumExcpAudExists,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		chnl.audJoinedChnl(audName);

		assertEquals(audName, chnlView.getAudJoined());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testAudJoinedChnl_DISCNED() throws CumExcpAudExists,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		chnl.discnded();

		chnl.audJoinedChnl(audName);

		assertNull(chnlView.getAudJoined());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#audJoinedChnl(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpAudExists
	 * 
	 * @throws CumExcpChnlCannotBeReged
	 * @throws CumExcpChnlCannotBeClsed
	 * @throws CumExcpChnlNotExist
	 */
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testAudJoinedChnl_CLOSING() throws CumExcpAudExists,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.clsChnl();
		} catch (Exception e) {
			fail("XXX");
		}

		chnl.audJoinedChnl(audName);

		assertNull(chnlView.getAudJoined());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#audJoinedChnl(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpAudExists
	 * 
	 * @throws CumExcpChnlCannotBeReged
	 * @throws CumExcpChnlCannotBeClsed
	 * @throws CumExcpChnlNotExist
	 */
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testAudJoinedChnl_CLOSED() throws CumExcpAudExists,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.clsChnl();
			chnl.chnlClsed();

		} catch (Exception e) {
			fail("XXXX");
		}
		chnl.audJoinedChnl(audName);
		assertNull(chnlView.getAudJoined());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#audRjctedChnl(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpAudExists
	 */
	@Test
	public void testAudRjctedChnl() throws CumExcpIllegalChnlStatus,
			CumExcpAudNotExist, CumExcpAudExists {
		PrestrChnl chnl = getChnl();
		chnl.audRjctedChnl(audName);

		assertEquals(audName, chnlView.getAudRjcted());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testAudRjctedChnl_DISCNED() throws CumExcpIllegalChnlStatus,
			CumExcpAudNotExist, CumExcpAudExists {
		PrestrChnl chnl = getChnl();
		chnl.discnded();

		chnl.audRjctedChnl(audName);
		assertNull(chnlView.getAudRjcted());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#audRjctedChnl(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpAudExists
	 * 
	 * @throws CumExcpChnlCannotBeClsed
	 * @throws CumExcpChnlNotExist
	 */
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testAudRjctedChnl_CLOSING() throws CumExcpIllegalChnlStatus,
			CumExcpAudNotExist, CumExcpAudExists {
		PrestrChnl chnl = getChnl();

		try {
			chnl.clsChnl();
		} catch (Exception e) {
			fail("XXX");
		}
		chnl.audRjctedChnl(audName);
		assertNull(chnlView.getAudRjcted());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#audRjctedChnl(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpAudExists
	 * 
	 * @throws CumExcpChnlCannotBeClsed
	 * @throws CumExcpChnlNotExist
	 */
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testAudRjctedChnl_CLOSED() throws CumExcpIllegalChnlStatus,
			CumExcpAudNotExist, CumExcpAudExists {
		PrestrChnl chnl = getChnl();

		try {
			chnl.clsChnl();
			chnl.chnlClsed();
		} catch (Exception e) {
			fail("XXX");
		}
		chnl.audRjctedChnl(audName);
		assertNull(chnlView.getAudRjcted());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#audLftChnl(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpAudExists
	 */
	@Test
	public void testAudLftChnl() throws CumExcpAudExists,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		chnl.audJoinedChnl(audName);

		chnl.audLftChnl(audName);

		assertEquals(audName, chnlView.getAudLft());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#audLftChnl(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * 
	 * @throws CumExcpChnlCannotBeReged
	 */
	@Test
	public void testAudLftChnl_REGED() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.audJoinedChnl(audName);

		} catch (Exception e) {
			fail("XXX");
		}

		chnl.audLftChnl(audName);

		assertEquals(audName, chnlView.getAudLft());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testAudLftChnl_DISCNED() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		try {
			chnl.audJoinedChnl(audName);
			chnl.discnded();
		} catch (Exception e) {
			fail("XXX");
		}
		chnl.audLftChnl(audName);

		assertNull(chnlView.getAudLft());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#audLftChnl(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * 
	 * @throws CumExcpChnlCannotBeReged
	 * @throws CumExcpChnlCannotBeClsed
	 * @throws CumExcpChnlNotExist
	 */
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testAudLftChnl_CLOSING() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();

		try {
			chnl.chnlReged();
			chnl.clsChnl();
		} catch (Exception e) {
			fail("XXX");
		}
		chnl.audLftChnl(audName);

		assertNull(chnlView.getAudLft());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#audLftChnl(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * 
	 * @throws CumExcpChnlCannotBeReged
	 * @throws CumExcpChnlCannotBeClsed
	 * @throws CumExcpChnlNotExist
	 */
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testAudLftChnl_CLOSED() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.clsChnl();
			chnl.chnlClsed();

		} catch (Exception e) {
			fail("XXX");
		}

		chnl.audLftChnl(audName);

		assertNull(chnlView.getAudLft());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#audDiscned(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpAudExists
	 */
	@Test
	public void testAudDiscned() throws CumExcpAudExists,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		chnl.audJoinedChnl(audName);
		chnl.audDiscned(audName);

		assertEquals(audName, chnlView.getAudDiscned());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#audDiscned(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * 
	 * @throws CumExcpChnlCannotBeReged
	 */
	@Test
	public void testAudDiscned_REGED() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.audJoinedChnl(audName);
		} catch (Exception e) {
			fail("XXX");
		}

		chnl.audDiscned(audName);

		assertEquals(audName, chnlView.getAudDiscned());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testAudDiscned_DISCNED() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		chnl.discnded();
		chnl.audDiscned(audName);

		assertNull(chnlView.getAudDiscned());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#audDiscned(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * 
	 * @throws CumExcpChnlCannotBeReged
	 * @throws CumExcpChnlCannotBeClsed
	 * @throws CumExcpChnlNotExist
	 */
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testAudDiscned_CLOSING() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.clsChnl();
		} catch (Exception e) {
			fail("XXX");
		}

		chnl.audDiscned(audName);

		assertNull(chnlView.getAudDiscned());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#audDiscned(java.lang.String)}
	 * .
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * 
	 * @throws CumExcpChnlCannotBeReged
	 * @throws CumExcpChnlCannotBeClsed
	 * @throws CumExcpChnlNotExist
	 */
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testAudDiscned_CLOSED() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.clsChnl();
			chnl.chnlClsed();
		} catch (Exception e) {
			fail("XXX");

		}

		chnl.audDiscned(audName);

		assertNull(chnlView.getAudDiscned());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#chnlClsed()}.
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * 
	 * @throws CumExcpChnlCannotBeReged
	 * @throws CumExcpChnlCannotBeClsed
	 * @throws CumExcpChnlNotExist
	 */
	@Test
	public void testChnlClsed() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.clsChnl();
		} catch (Exception e) {
			fail("XXX");

		}

		chnl.chnlClsed();
		assertTrue(chnlView.isClsed());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testChnlClsed_DISCNED() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		chnl.discnded();
		chnl.chnlClsed();
		assertFalse(chnlView.isClsed());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#chnlClsed()}.
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * 
	 * @throws CumExcpChnlCannotBeReged
	 * @throws CumExcpChnlCannotBeClsed
	 */
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testChnlClsed_REGING() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		chnl.chnlClsed();
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#chnlClsed()}.
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * 
	 * @throws CumExcpChnlCannotBeReged
	 * @throws CumExcpChnlCannotBeClsed
	 */
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testChnlClsed_REGED() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
		} catch (Exception e) {
			fail("XXX");

		}
		chnl.chnlClsed();
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#chnlClsed()}.
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * 
	 * @throws CumExcpChnlCannotBeReged
	 * @throws CumExcpChnlCannotBeClsed
	 * @throws CumExcpChnlNotExist
	 */
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testChnlClsed_CLOSED() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();

		try {
			chnl.chnlReged();
			chnl.clsChnl();
			chnl.chnlClsed();
		} catch (Exception e) {
			fail(">>>>");
		}
		chnl.chnlClsed();
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#chnlReged()}.
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * 
	 * @throws CumExcpChnlCannotBeReged
	 */
	@Test
	public void testChnlReged() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		chnl.chnlReged();

		assertTrue(chnlView.isReged());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testChnlReged_DISCNED() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		chnl.discnded();
		chnl.chnlReged();

		assertFalse(chnlView.isReged());
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#chnlReged()}.
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * 
	 * @throws CumExcpChnlCannotBeReged
	 */
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testChnlReged_REGED() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
		} catch (Exception e) {
			fail("XXX");

		}
		chnl.chnlReged();

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#chnlReged()}.
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * 
	 * @throws CumExcpChnlCannotBeReged
	 * @throws CumExcpChnlCannotBeClsed
	 * @throws CumExcpChnlNotExist
	 */
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testChnlReged_CLOSING() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.clsChnl();
		} catch (Exception e) {
			fail("XXX");

		}

		chnl.chnlReged();

	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnl#chnlReged()}.
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * 
	 * @throws CumExcpChnlCannotBeReged
	 * @throws CumExcpChnlCannotBeClsed
	 * @throws CumExcpChnlNotExist
	 */
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testChnlReged_CLOSED() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.clsChnl();
			chnl.chnlClsed();
		} catch (Exception e) {
			fail("XXX");

		}

		chnl.chnlReged();

	}

	@Test
	public void testDiscned() {
		PrestrChnl chnl = getChnl();
		chnl.discnded();
		assertTrue(chnlView.isDiscned());
	}

	@Test
	public void testDiscned_REGED() {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
		} catch (Exception e) {
			fail("XXX");

		}
		chnl.discnded();
		assertTrue(chnlView.isDiscned());
	}

	@Test
	public void testDiscned_CLOSING() {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.clsChnl();
		} catch (Exception e) {
			fail("XXX");

		}

		chnl.discnded();
		assertTrue(chnlView.isDiscned());
	}

	@Test
	public void testDiscned_CLOSED() {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.clsChnl();
			chnl.chnlClsed();
		} catch (Exception e) {
			fail("XXX");

		}

		chnl.discnded();
		assertTrue(chnlView.isDiscned());
	}

	@Test
	public void testSeshClsing() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		chnl.seshClsing();

		assertTrue(chnlView.isSeshClsing());
	}

	@Test
	public void testSeshClsing_REGED() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
		} catch (Exception e) {
			fail("XXX");

		}
		chnl.seshClsing();

		assertTrue(chnlView.isSeshClsing());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testSeshClsing_CLOSING() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.clsChnl();
		} catch (Exception e) {
			fail("XXX");

		}

		chnl.seshClsing();

		assertTrue(chnlView.isSeshClsing());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testSeshClsing_CLOSED() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
			chnl.clsChnl();
			chnl.chnlClsed();
		} catch (Exception e) {
			fail("XXX");

		}

		chnl.seshClsing();

		assertTrue(chnlView.isSeshClsing());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void testSeshClsing_DISCNED() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		chnl.discnded();
		chnl.seshClsing();

		assertTrue(chnlView.isSeshClsing());
	}

}
