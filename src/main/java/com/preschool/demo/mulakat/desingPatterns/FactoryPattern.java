package com.preschool.demo.mulakat.desingPatterns;

// Üst sınıf
abstract class Document {
    public abstract void open();

    public abstract void close();
}

// Alt sınıflar
class PdfDocument extends Document {
    @Override
    public void open() {
        System.out.println("PDF belgesi açıldı.");
    }

    @Override
    public void close() {
        System.out.println("PDF belgesi kapatıldı.");
    }
}

class TextDocument extends Document {
    @Override
    public void open() {
        System.out.println("Metin belgesi açıldı.");
    }

    @Override
    public void close() {
        System.out.println("Metin belgesi kapatıldı.");
    }
}

// Factory sınıfı
class DocumentFactory {
    public Document createDocument(String type) {
        if (type.equalsIgnoreCase("pdf")) {
            return new PdfDocument();
        } else if (type.equalsIgnoreCase("text")) {
            return new TextDocument();
        }
        return null;
    }
}

// Kullanım
public class FactoryPattern {
    public static void main(String[] args) {
        DocumentFactory factory = new DocumentFactory();
        Document doc1 = factory.createDocument("pdf");
        doc1.open();
        doc1.close();

        Document doc2 = factory.createDocument("text");
        doc2.open();
        doc2.close();
    }
}
