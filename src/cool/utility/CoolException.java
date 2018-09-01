/*******************************************************************************
 * This file is used in CS4533/CS544, Compiler Construction & Techniques of
 * Language Translation, Worcester Polytechnic Institute.
 *
 * Copyright (c) 2016-17 Gary F. Pollice
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package cool.utility;

/**
 * The main exception in the COOL compiler.
 * @version Oct 25, 2017
 */
public class CoolException extends RuntimeException
{
	/**
	 * There must be at least a message in a COOL exception
	 * @param message the text of the  message
	 */
	public CoolException(String message)
	{
		super(message);
	}

	/**
	 * This is a constructor for an exception that is caused by another exception.
	 * @param message the text of the message
	 * @param cause the causing exception
	 */
	public CoolException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
