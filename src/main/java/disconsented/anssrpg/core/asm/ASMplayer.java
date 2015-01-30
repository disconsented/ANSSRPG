package disconsented.anssrpg.core.asm;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class ASMplayer implements IClassTransformer{

    @Override
    public byte[] transform(String name, String transformedName,
            byte[] basicClass) {
        if (name.equals("net.minecraft.entity.player.EntityPlayerMP")){
            byte[] b = null;
            try{
                ClassReader cr = new ClassReader(basicClass);
                ClassWriter cw = new ClassWriter(0);
                ClassVisitor ca = new PlayerFieldAdapter(Opcodes.ASM4, cw); //Actual transformation done here                  
                cr.accept(ca, 0);
                b = cw.toByteArray();
            }catch(Exception e){
                System.err.println(e.getStackTrace());
            }
            return b;
        }else
            return basicClass;
    }

}
