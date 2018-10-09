package com.bee.serialize;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HessianSerializer implements Serializer {
    @Override
    public <T> byte[] serialize(T obj) {
        // 1、创建字节输出流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        // 2、对字节数组流进行再次封装
        HessianOutput hessianOutput = new HessianOutput(bos);

        try {
            // 注意，obj 必须实现Serializable接口
            hessianOutput.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bos.toByteArray();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialize(byte[] data) {
        // 1、将字节数组转换成字节输入流
        ByteArrayInputStream bis = new ByteArrayInputStream(data);

        HessianInput hessianInput = new HessianInput(bis);

        Object object = null;

        try {
            object = hessianInput.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (T) object;
    }

}
