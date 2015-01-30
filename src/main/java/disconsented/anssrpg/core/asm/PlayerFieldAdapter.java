/*The MIT License (MIT)

Copyright (c) 2015 Disconsented, James Kerr

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
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
