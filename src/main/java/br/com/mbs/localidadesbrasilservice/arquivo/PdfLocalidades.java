package br.com.mbs.localidadesbrasilservice.arquivo;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import org.jboss.logging.Logger;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import br.com.mbs.localidadesbrasilservice.entidades.Localidades;

public class PdfLocalidades extends CriaFormataArquivo<Iterator<Localidades>> {

	private Logger LOG = Logger.getLogger(PdfLocalidades.class);
	
	public PdfLocalidades(Iterator<Localidades> t) {
		super(t);
	}


	@Override
	public byte[] executa() {
		
		byte[] ret = null;
		ByteArrayOutputStream outputStream = null;

		try {           
           
            outputStream = new ByteArrayOutputStream();
            writePdf(outputStream,super.obj);
            ret  = outputStream.toByteArray();
             
            
        } catch(Exception ex) {
            LOG.error(ex);
        } finally {
            if(null != outputStream) {
                try { outputStream.close(); outputStream = null; }
                catch(Exception ex) { }
            }
        }
		return ret;
	}

	
	private void writePdf(OutputStream outputStream,Iterator<Localidades> localidades) throws Exception {
        Document document = new Document();
      PdfWriter.getInstance(document, outputStream);
        
      //      	PdfWriter.getInstance(document, new FileOutputStream("c:\\temp\\resultado.pdf"));
        document.open();
         
        document.addTitle("LOCALIDADES");
        document.addAuthor("marcelo soares");
        document.addCreator("marcelo soares");
         
        while(localidades.hasNext()) {
        	Localidades localidade = localidades.next();
	        document.add(new Paragraph("Estado:" +localidade.getIdEstado()));
	        document.add(new Paragraph("Sigla:" +localidade.getSiglaEstado()));
	        document.add(new Paragraph("Região:" +localidade.getRegiaoNome()));
	        document.add(new Paragraph("Cidade:" +localidade.getNomeCidade()));
	        document.add(new Paragraph("Mesorregião:" +localidade.getNomeMesorregiao()));
	        String cidadeEstado = localidade.getNomeCidade() +"/"+ localidade.getSiglaEstado();
	        document.add(new Paragraph(cidadeEstado));
	        document.add(new Paragraph("------------------------------------------------"));
        }
         
        document.close();
    }


	
}
