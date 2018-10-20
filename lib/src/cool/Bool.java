/*******************************************************************************
 * This files was developed for CS4533/CS544: Techniques of Program Translation/Compiler
 * Construction. The course was taken at Worcester Polytechnic Institute. All rights
 * reserved. This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package cool;

/**
 * Implementation of the Cool-W Bool base class. There are only two methods, one for
 * getting the Bool for true and the other for false.
 * 
 * @version May 12, 2018
 */
public class Bool extends Object
{
    boolean value;

    public static Bool trueBool = new Bool(true);
    public static Bool falseBool = new Bool();

    private Bool()
    {
        this(false);
    }
    
    private Bool(boolean value) {
        this.value = value;
    }
}
