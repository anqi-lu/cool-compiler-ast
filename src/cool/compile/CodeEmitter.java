package cool.compile;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.*;

import cool.ast.ASTBaseVisitor;
import cool.ast.ASTNodeFactory.*;
import cool.symbol.BindingFactory.*;
import cool.symbol.ClassDescriptor;
import static org.objectweb.asm.ClassWriter.*;


public class CodeEmitter extends ASTBaseVisitor<String>{

    private ClassWriter cw = null;
    private MethodVisitor mv = null;
    private FieldVisitor fv = null;
    private final String COOL = "cool/";    // the package where everything is compiled
    private ClassDescriptor currentClass;
    private final boolean DEBUG = true;

	/**
	 * Constructor
	 */
	public CodeEmitter() {
		
	}
	
	/**
	 * 
	 */
	public void emitStartClass() {
		cw = new ClassWriter(COMPUTE_FRAMES + COMPUTE_MAXS);
		cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER, COOL + currentClass.className, 
				null, COOL + currentClass.inherits, null);
	}
	
	/**
	 * 
	 * @return
	 */
	public byte[] emitEndClass() {
		cw.visitEnd();
		return cw.toByteArray();
	}
	
	/**
	 * 
	 */
	public void emitStartConstructur() {
		mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
		mv.visitCode();
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, COOL+currentClass.inherits, "<init>", "()V", false);
				
	}
	
	/**
	 * 
	 */
	public void emitEndConstructor() {
		mv.visitInsn(RETURN);
		mv.visitMaxs(1, 1);
		mv.visitEnd();
	}
	
	/**
	 * 
	 */
//	@Override
//	public String visit(Formal node) {
//		if (node.binding.isAttribute) { 
//			// attribute or let statement etc- not a java instance variable? 
//			et.emitNewAttribute()
//		}
//	}
//	
	
	/**
	 * 
	 * @return
	 */
	public Map<String, byte[]> getBytecodes() {
		Map<String, byte[]> byteCodes = new HashMap<>();
		return byteCodes;
	}
}
