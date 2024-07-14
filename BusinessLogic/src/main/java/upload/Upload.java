/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upload;
import awss3.Bucket;
/**
 *
 * @author martinez
 */
public class Upload {
    public String upload(String filePath, String keyName){
        Bucket bucket = new Bucket();
        return bucket.upload(filePath, keyName);
    }
}
