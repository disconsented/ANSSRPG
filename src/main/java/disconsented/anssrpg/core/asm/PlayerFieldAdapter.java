package disconsented.anssrpg.core.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

import scala.tools.asm.Type;

public class PlayerFieldAdapter extends ClassVisitor{
    private int fAcc = Opcodes.ACC_PUBLIC;
    private String fName = "playerData";
    //private String fDesc = Type.getObjectType(Type.getInternalName(disconsented.anssrpg.player.PlayerData.class)).getDescriptor();
    private String fDesc = Type.getObjectType(Type.getInternalName(String.class)).getDescriptor();
    private String fSig = null;
    private boolean isFieldPresent = false;
    public PlayerFieldAdapter(int api, ClassVisitor cv) {
        super(api, cv);
        // TODO Auto-generated constructor stub
    }
    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value){
        if(name.equals(fName)){
            isFieldPresent = true;
        }        
        return cv.visitField(access, name, desc, signature, value);
        
    }
    @Override
    public void visitEnd(){
        if(!isFieldPresent){
            FieldVisitor fv = cv.visitField(fAcc, fName, fDesc, fSig, "Hey look this works");
            if (fv != null){
                fv.visitEnd();
            }
        }
        cv.visitEnd();
        
    }
}
