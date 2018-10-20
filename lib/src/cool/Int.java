/*******************************************************************************
 * This files was developed for CS4533/CS544: 
 *     Techniques of Program Translation/Compiler Construction.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package cool;

/**
 * The Cool-W Int class. There is no behavior added by this class
 * @version May 11, 2018
 */
public final class Int extends Object
{
	int value;
	
	public static Int makeInt(int x)
	{
		return new Int(x);
	}
	
	public static Int makeDefaultInt()
	{
		return new Int();
	}
	
	public Int()
	{
		value = 0;
	}
	
	public Int(int x)
	{
		value = x;
	}
}
