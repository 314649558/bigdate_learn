package rpc.rpc10;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by Administrator on 2020/12/11.
 */
public class HessianUtils {

    public static byte[] serialize(Object o) throws Exception{
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        Hessian2Output output=new Hessian2Output(baos);
        output.writeObject(o);
        output.flush();
        byte[] bytes = baos.toByteArray();
        baos.close();
        output.close();
        return bytes;
    }
    public static Object deserialize(byte[] bytes) throws Exception{
        ByteArrayInputStream ins=new ByteArrayInputStream(bytes);
        Hessian2Input input=new Hessian2Input(ins);
        Object o = input.readObject();
        ins.close();
        input.close();
        return o;
    }

}
