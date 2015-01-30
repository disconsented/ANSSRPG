package disconsented.anssrpg.core.asm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.TraceClassVisitor;

public class ASMplayer implements IClassTransformer{

    @Override
    public byte[] transform(String name, String transformedName,
            byte[] basicClass) {
        byte[] b = null;
        try{
            Class c = net.minecraft.entity.player.EntityPlayerMP.class;
            String className = c.getName();
            String classAsPath = className.replace('.', '/') + ".class";
            InputStream stream = c.getClassLoader().getResourceAsStream(classAsPath);
            ClassWriter cw = new ClassWriter(0);
            ClassVisitor ca = new PlayerFieldAdapter(Opcodes.ASM4, cw); //Actual transformation done here         
            ClassReader cr = new ClassReader(stream);
            cr.accept(ca, 0);
            b = cw.toByteArray();
        }catch(Exception e){
            System.err.println(e.getStackTrace());
        }
        return b;
    }

}
