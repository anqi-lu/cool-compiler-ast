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

import java.lang.reflect.Field;

/**
 * Implementation of the Cool-W Object base class.
 * @version May 10, 2018
 */
public class Object implements Cloneable
{
	private static final int CLASS_NAME_START = 5;
	
	public void abort()
	{
		System.err.println("Program aborting due to call to Object.abort()");
		System.exit(-1);
	}
	
	public String typeName()
	{
		return this.getClass().getName().substring(CLASS_NAME_START);
	}
	
	public Object copy()
	{
	    try {
            return (Object) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            abort();
        }
	    return null;
	}
}
