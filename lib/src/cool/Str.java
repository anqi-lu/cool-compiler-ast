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
 * Implementation of the Cool-W Str base class.
 * @version May 12, 2018
 */
public class Str extends Object
{
	public static Str makeStr(String s)
	{
		return new Str(s);
	}
	
	public static Str makeDefaultStr()
	{
		return new Str();
	}
	
	String value;
	
	public Str()
	{
		value = "";
	}
	
	public Str(String s)
	{
		value = s;
	}
	
	public Int length()
	{
		return new Int(value.length());
	}
	
	public Str concat(Str s)
	{
		return new Str(value + s.value);
	}
	
	public Str substr(Int i, Int l)
	{
		return new Str(value.substring(i.value, i.value + l.value));
	}
}
