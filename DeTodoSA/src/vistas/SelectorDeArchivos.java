/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

/**
 *
 * @author adam
 */
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SelectorDeArchivos extends JFileChooser {

    private JButton newFileButton;
    private String headerText;
    private Long maxFileSize;

    public SelectorDeArchivos() {
        super();
        init();
    }

    public SelectorDeArchivos(String headerText) {
        this();
        this.headerText = headerText;
    }

    public SelectorDeArchivos(String headerText, Long maxFileSize) {
        this();
        this.headerText = headerText;
        this.maxFileSize = maxFileSize;
    }

    public SelectorDeArchivos(File currentDirectory) {
        super(currentDirectory);
        init();
    }

    public SelectorDeArchivos(File currentDirectory, String headerText) {
        super(currentDirectory);
        init();
        this.headerText = headerText;
    }

    private void init() {
        // Add a new button for creating files
        newFileButton = new JButton("Archivo Nuevo");
        newFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewFile();
            }
        });

        JPanel accessoryPanel = new JPanel(new BorderLayout());
        accessoryPanel.add(newFileButton, BorderLayout.NORTH);
        setAccessory(accessoryPanel);
    }
    
    @Override
    public void approveSelection() {
        File selectedFile = getSelectedFile();
        if (selectedFile != null && maxFileSize != null && selectedFile.length() > maxFileSize) {
            JOptionPane.showMessageDialog(this,
                    "El archivo es muy grande como para abrir, "
                            + "pues no se puede");
            return;
        }
        if (headerText != null && !headerText.isEmpty() && !checkFileHeader(selectedFile)) {
            JOptionPane.showMessageDialog(this,
                    "File does not match the expected header.");
            return;
        }
        super.approveSelection();
    }
    
    private boolean checkFileHeader(File file) {
        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                String firstLine = scanner.nextLine();
                System.out.println("firstLine=" + firstLine);
                return firstLine.equals(headerText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private void createNewFile() {
        File currentDirectory = getCurrentDirectory();
        String newFileName = JOptionPane.showInputDialog(this,
                "Nombre del archivo nuevo:");
        if (newFileName != null && !newFileName.isEmpty()) {
            FileNameExtensionFilter filter = (FileNameExtensionFilter) getFileFilter();
            String[] extensions = filter.getExtensions();
            // Agregar la extensión desde el filtro
            if (extensions.length > 0) {
                if (!newFileName.endsWith(extensions[0])) {
                    newFileName += "." + extensions[0];
                }
            }
            File newFile = new File(currentDirectory, newFileName);
            try {
                if (newFile.createNewFile()) {
//                    JOptionPane.showMessageDialog(this,
//                            "New file created: " + newFile.getAbsolutePath());
                    refreshFileSystemView(currentDirectory);
                    setSelectedFile(newFile);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "No se pudo crear el archivo, ¿ya exite uno con ese nombre?");
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Salió un error al intentar a crear el archivo nuevo.");
            }

            if (headerText != null) {
                try {
                    FileWriter writer = new FileWriter(newFile.getAbsolutePath());
                    writer.write(headerText + "\n");
                    writer.close();
                    System.out.println("Se escrbió el headerText en el archivo nuevo.");
                } catch (IOException e) {
                    System.out.println("Pasó un error al intentar a escribir al archivo.");
                    e.printStackTrace();
                }
            }
        }
    }

    private void refreshFileSystemView(File directory) {
        File parent = directory.getParentFile();
        if (parent != null) {
            setCurrentDirectory(null); // Reset current directory to trigger refresh
            setCurrentDirectory(directory); // Set it back to the desired directory
        }
    }

}
