package net.gadgil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BlackScholesTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public BlackScholesTest( String testName )
	{
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( BlackScholesTest.class );
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testBlackScholesGeneralizedCallOption()
	{
		double optionValue = net.gadgil.cgoptprice.BlackScholes.callOptionGeneralizedBlackScholes(60, 65, 0.25, 0.08, 0.08, 0.3);
		double diff = Math.abs(2.1334 - optionValue);
		if(diff < 0.0001)
			assertTrue( true );
		else 
			assertTrue(false);
	}
	
	public void testBlackScholesGeneralizedPutOption()
	{
		double optionValue = net.gadgil.cgoptprice.BlackScholes.putOptionGeneralizedBlackScholes(75, 70, 0.5, 0.1, 0.05, 0.35);
		double diff = Math.abs(4.0870 - optionValue);
		if(diff < 0.0001)
			assertTrue( true );
		else 
			assertTrue(false);
	}
}
