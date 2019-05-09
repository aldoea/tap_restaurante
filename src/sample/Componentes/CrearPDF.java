package sample.Componentes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class CrearPDF {
        Document document = new Document();

    public void crearPDF(String nombre, String contenido) {
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(nombre));
            document.open();
            document.add(new Paragraph(contenido));
            document.close();
            writer.close();
            System.out.println("Listo pdf");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}