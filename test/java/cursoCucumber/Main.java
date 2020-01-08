package cursoCucumber;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JSpinner.ListEditor;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Main {
	public static  WebDriver driver;
	public static ListEditor evidencia;
	public static Document  doc = new Document();
	public static int contador=0;
	
	@Test
	public void execel() throws Exception, Exception {
		GerenciadorExel gerenciador = new GerenciadorExel();
		
		
		List<cheque> cheque = gerenciador.criar();
		
		//System.out.println(cheque);
		
//		gerenciador.imprimir(cheque);
		inicializar_test(cheque);
	}
	
	
	public void inicializar_test(List<cheque> cheques) throws DocumentException, Exception{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\813899\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://www.buscacep.correios.com.br/sistemas/buscacep/BuscaCepEndereco.cfm");
		for (int j = 0; j < cheques.size(); j++) {
			System.out.println(cheques.get(j));
			Object a= cheques.get(j);
			driver.findElement(By.xpath("//*[@id=\"Geral\"]/div/div/span[2]/label/input")).sendKeys(a.toString());
			evidencia();
		
		}
	
	}
	
	
	@After
	public void fim() throws Exception, Exception {
		gravar();
		driver.quit();

	}
	
	public static void evidencia() throws Exception {

         
        try {
            Robot robot = new Robot();
            contador=contador+1;
            String num=Integer.toString(contador);
            String format = "jpg";
            String fileName = "C:\\Users\\813899\\Documents\\lixo\\"+num+"."+format;
             
           Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
           BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
           // BufferedImage screenFullImage = robot.createScreenCapture(new Rectangle(0, 0, 1920, 1080));
            ImageIO.write(screenFullImage, format, new File(fileName));
             
            System.out.println("Print da tela salva!");
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
       }
    }
	
	
	@Test
	public void gravar() throws Exception, DocumentException {
		
		//caminho pra Criar o PDF
		PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\813899\\Documents\\lixo\\PDF_DevMedia.pdf"));
        //abrindo o PDF
		doc.open();
		
		//Contador pra adicionar todas as imagem no PDF
        for (int i = 1; i <= contador; i++) {
        	doc.setPageSize(PageSize.A4);
           
        	//chamar o cabeçalho
        	doc.add(cabecalho());
        	
        	//pegando as imgem
        	Image figura = Image.getInstance("C:\\Users\\813899\\Documents\\lixo\\"+i+".jpg");
            figura.setAbsolutePosition(20, 400);
            figura.scaleToFit(550, 750);
           //Inserindo imagem  
            doc.add(figura);
             
           //insere um novo nova pagina
            doc.newPage();
		}
       
        
        doc.close();
        
	}
	
	
	
	
	public PdfPTable cabecalho() throws Exception, DocumentException {
		
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        String usuario =System.getProperty("user.name");
        
        PdfPTable tableheader = new PdfPTable(new float[] { 0.20f, 0.35f,0.13f,0.32f });
        PdfPCell header = new PdfPCell(new Paragraph("Evidencia De Teste"));
        
        header.setUseBorderPadding(true);
        header.setBorderColor(BaseColor.BLUE);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.setColspan(4);
        tableheader.setSpacingBefore(20);
        tableheader.addCell(header);
        
        PdfPCell lblSistema = new PdfPCell(new Paragraph("Sistema", boldFont));
        PdfPCell txtSistema = new PdfPCell(new Paragraph("Sistema Saude"));
        PdfPCell lblVersao = new PdfPCell(new Paragraph("Versão", boldFont));
        PdfPCell txtVersao = new PdfPCell(new Paragraph("V1"));
        PdfPCell lblCT = new PdfPCell(new Paragraph("CT", boldFont));
        PdfPCell txtCT = new PdfPCell(new Paragraph("Gravar evidencia em PDF"));
        PdfPCell lblExecutador = new PdfPCell(new Paragraph("Executador", boldFont));
        PdfPCell txtExecutador = new PdfPCell(new Paragraph(usuario));
    
        PdfPCell lblData = new PdfPCell(new Paragraph("Data", boldFont));
        PdfPCell txtData = new PdfPCell(new Paragraph("03/01/2020"));
        
        lblSistema.setBorderColor(BaseColor.BLUE);
        lblSistema.setHorizontalAlignment(Element.ALIGN_TOP);
        txtSistema.setBorderColor(BaseColor.BLUE);
        
        lblVersao.setBorderColor(BaseColor.BLUE);
        lblVersao.setHorizontalAlignment(Element.ALIGN_TOP);
        txtVersao.setBorderColor(BaseColor.BLUE);
        
        lblCT.setBorderColor(BaseColor.BLUE);
        lblCT.setHorizontalAlignment(Element.ALIGN_TOP);
        txtCT.setBorderColor(BaseColor.BLUE);
        
        lblExecutador.setBorderColor(BaseColor.BLUE);
        lblExecutador.setHorizontalAlignment(Element.ALIGN_TOP);
        txtExecutador.setBorderColor(BaseColor.BLUE);
        
        lblData.setBorderColor(BaseColor.BLUE);
        lblData.setHorizontalAlignment(Element.ALIGN_TOP);
        txtData.setBorderColor(BaseColor.BLUE);
        
        txtCT.setColspan(3);
        
        tableheader.addCell(lblSistema);
        tableheader.addCell(txtSistema);
        tableheader.addCell(lblVersao);
        tableheader.addCell(txtVersao);
        tableheader.addCell(lblCT);
        tableheader.addCell(txtCT);
        tableheader.addCell(lblExecutador);
        tableheader.addCell(txtExecutador);
        tableheader.addCell(lblData);
        tableheader.addCell(txtData);
        
        tableheader.setSpacingAfter(20);

        return tableheader;
	
	}
	
	
	

}
