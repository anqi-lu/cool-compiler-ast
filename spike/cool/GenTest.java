/*******************************************************************************
 * This file is used in CS4533/CS544, Compiler Construction & Techniques of
 * Language Translation, Worcester Polytechnic Institute.
 *
 * Copyright (c) 2016-18 Gary F. Pollice
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package cool;

import static org.objectweb.asm.ClassWriter.*;
import java.io.*;
import org.objectweb.asm.*;
import cool.symbol.ClassDescriptor;

/**
 * Description
 * @version Aug 25, 2018
 */
public class GenTest implements Opcodes
{
    private ClassWriter cw = null;
    private MethodVisitor mv = null;
    private FieldVisitor fv = null;
    private final String COOL = "cool/";    // the package where everything is compiled
    private ClassDescriptor currentClass;
    private final boolean DEBUG = true;
    
    /**
     * Description
     */
    public GenTest()
    {
        // TODO Auto-generated constructor stub
    }
    
    public void generate() throws Exception
    {
        cw = new ClassWriter(COMPUTE_FRAMES + COMPUTE_MAXS);
        cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER, COOL + "Gen", null, 
                COOL + "Object", null);
        
        // Default constructor
        mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, COOL + "Object", "<init>", "()V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
        
        cw.visitEnd();
        
        FileOutputStream fos = new FileOutputStream("./coolcode/cool/Gen.class");
        byte[] code = cw.toByteArray();
        
        System.out.print("writing code ");
        fos.write(code);
        fos.close();
    }

    /**
     * Description
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception
    {
        GenTest gt = new GenTest();
        gt.generate();

    }

}
