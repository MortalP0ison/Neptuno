/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeptunoPackage;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Mortal_Poison
 */
public class Metodos {

    String destino = "E:\\Archivos\\";
    Path algo = Paths.get("E:/Archivos/");

    String username = System.getProperty("user.name");

    public void CopyToFantasy(String origenArchivo, Path directory) {
        Path origenPath = Paths.get(origenArchivo);
        Path conver_resolver = directory.resolve(origenPath.getFileName());
        System.out.println(conver_resolver);
        try {
            //sobreescribir el fichero de destino si existe y lo copia
            Files.copy(origenPath, conver_resolver, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
     public BufferedImage redimensionarImagen(String archivo, double porcentaje ){
        
        BufferedImage bf = null;
        try {
            bf = ImageIO.read(new File(archivo));
        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        int ancho = bf.getWidth();
        int alto = bf.getHeight();
        int escalaAncho = (int)(porcentaje* ancho);
        int escalaAlto = (int)(porcentaje*alto);
        BufferedImage bufim = new BufferedImage(escalaAncho, escalaAlto, bf.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bf, 0,0, escalaAncho,escalaAlto, 0,0,ancho,alto, null);
        g.dispose();
        return bufim;
}

    
    public void TravelToFantasy(File directory, Path get_path_directory) {
        
        File listFile[] = directory.listFiles();
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    TravelToFantasy(listFile[i], get_path_directory);
                } else {
                    System.out.println(listFile[i].getPath());
                    System.out.println(destino);
                    this.CopyToFantasy(listFile[i].getPath(), get_path_directory);

                }
            }
        }
    }
}
