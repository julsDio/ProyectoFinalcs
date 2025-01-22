package com.Tienda.CRUD.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicio para gestionar la carga y eliminación de archivos de imágenes.
 * Este servicio permite guardar imágenes subidas por los usuarios
 * y eliminar imágenes existentes.
 */
@Service
public class UploadFileService {
    /** Carpeta donde se almacenan las imágenes. */
    private String folder="images//";
	
    /**
     * Guarda una imagen en el sistema de archivos.
     *
     * @param file El archivo de imagen subido por el usuario.
     * @return El nombre del archivo guardado, o "default.jpg" si el archivo está vacío.
     * @throws IOException Si ocurre un error al guardar el archivo.
     */
    public String saveImage(MultipartFile file) throws IOException {
            if (!file.isEmpty()) {
                    byte [] bytes=file.getBytes();
                    Path path =Paths.get(folder+file.getOriginalFilename());
                    Files.write(path, bytes);
                    return file.getOriginalFilename();
            }
            return "default.jpg";
    }

    /**
     * Elimina una imagen del sistema de archivos.
     *
     * @param nombre El nombre del archivo a eliminar.
     */
    public void deleteImage(String nombre) {
            String ruta="images//";
            File file= new File(ruta+nombre);
            file.delete();
    }

}
