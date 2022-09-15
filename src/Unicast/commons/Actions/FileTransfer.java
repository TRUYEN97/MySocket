/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unicast.commons.Actions;

import Unicast.commons.Enum.ACTION;

/**
 *
 * @author Administrator
 */
public class FileTransfer extends SimplePackage{
    private final byte[] data;
    private final String filePath;
    private final long lenght;

    public FileTransfer(String filePath, byte[] data, long length) {
        super(ACTION.FILE_TRANSFER);
        this.filePath = filePath;
        this.data = data;
        this.lenght = length;
    }
    
    public String getFilePath() {
        return filePath;
    }

    public byte[] getData() {
        return data;
    }

    public long getLength() {
        return lenght;
    }
    
}
