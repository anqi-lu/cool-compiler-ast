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

import java.io.*;

/**
 * Implementation of the Cool-W IO base class. There are only static methods in 
 * this class.
 * 
 * TODO: Handle void output
 * 
 * @version May 12, 2018
 */
public class IO extends Object
{
	// These two variables can be modified for testing purposes
	protected BufferedReader coolIn;
	protected PrintStream coolOut;
	
	public static IO ioInstance = new IO();
	
	protected IO() 
	{
		coolIn = new BufferedReader(new InputStreamReader(System.in));
		coolOut = System.out;
	}
	
	public IO outInt(Int i)
	{
		coolOut.print(i.value);
		return this;
	}
	
	public IO outStr(Str s)
	{
		coolOut.print(s.value);
		return this.getClass().cast(this);
	}
	
	public IO outBool(Bool b)
	{
		coolOut.print(b == Bool.trueBool ? "true" : "false");
		return this;
	}
	
	// TODO: validate the input for all digits.
	public Int inInt()
	{
		Int result = null;
		try {
			int i = Integer.parseInt(coolIn.readLine().trim());
			result = new Int(i);
		} catch (NumberFormatException | IOException e) {
			System.err.println("Error reading an integer");
			e.printStackTrace();
		}
		return result;
	}
	
	public Str inStr()
	{
		Str result = null;
		try {
			result = new Str(coolIn.readLine());
		} catch (IOException e) {
			System.err.println("Error reading a string");
			e.printStackTrace();
		}
		return result;
	}
	
	public Bool inBool()
	{
		Bool result = null;
		try {
			String s = coolIn.readLine().trim();
			result = s.equals("true") ? Bool.trueBool
					: s.equals("false") ? Bool.falseBool : null;
		} catch (IOException e) {
			System.err.println("Error reading a string");
			e.printStackTrace();
		}
		return result;
	}
}
