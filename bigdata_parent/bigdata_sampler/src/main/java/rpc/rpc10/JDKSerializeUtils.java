package rpc.rpc10;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.*;

/**
 * Created by Administrator on 2020/12/11.
 */
public class JDKSerializeUtils {
    public static byte[] serialize(Object o) throws Exception{
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ObjectOutputStream output=new ObjectOutputStream(baos);
        output.writeObject(o);
        output.flush();
        byte[] bytes = baos.toByteArray();
        baos.close();
        output.close();
        return bytes;
    }
    public static Object deserialize(byte[] bytes) throws Exception{
        ByteArrayInputStream ins=new ByteArrayInputStream(bytes);
        ObjectInputStream input=new ObjectInputStream(ins);
        Object o = input.readObject();
        ins.close();
        input.close();
        return o;
    }
}
