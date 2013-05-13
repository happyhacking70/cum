/**
 * 
 */
package jp.happyhacking.cum.aud.chnlLyr;

import static org.junit.Assert.assertEquals;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudChnl_chnlRjcting_Test extends AudChnlTestAbst {

	@Test
	public void test_init() throws CumExcpIllegalChnlStatus {
		AudChnl chnl = getChnl();
		chnl.chnlRjcting();
		assertEquals(AudChnl.Status.rjcting, chnl.chnlStatus);
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_joining() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_joining();
		chnl.chnlRjcting();
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_joined() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_joined();
		chnl.chnlRjcting();
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_rjcting() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_rjcting();
		chnl.chnlRjcting();
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_lving() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_lving();

		chnl.chnlRjcting();
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_clsed() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_clsed();

		chnl.chnlRjcting();
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_dscned() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_dscned();
		chnl.chnlRjcting();
	}
}
