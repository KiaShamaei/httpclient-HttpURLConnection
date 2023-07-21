package org.example;

import java.io.*;

public class TestDataOutPutStream
{
    public static void main( String[] args ) throws IOException {
        FileOutputStream fos = new FileOutputStream("data.txt");
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeInt(42);
        dos.writeDouble(3.14159);
        dos.writeBoolean(true);
        dos.writeUTF("Hello, world!");

        dos.close();

    }
}
