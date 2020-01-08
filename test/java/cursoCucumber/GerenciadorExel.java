package cursoCucumber;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import jxl.Cell;
import jxl.JXLException;

public class GerenciadorExel {
	
	static ArrayList<String[]> a = new ArrayList<String[]>();
	Main ajuda = new Main();
	
	
	@SuppressWarnings({ "unchecked", "resource" })
	public List<cheque> criar() throws IOException, JXLException {
		
		List<cheque> cheques = new ArrayList<>();
		
		FileInputStream file = new FileInputStream("C:\\Users\\813899\\Documents\\teste.xls");
		org.apache.poi.ss.usermodel.Workbook web= new HSSFWorkbook(file);
		
		//setando a ABA
		org.apache.poi.ss.usermodel.Sheet sheet = web.getSheetAt(0);

		//setando as linhas
		List<Row> rows = (List<Row>) toList(sheet.iterator());
		
		rows.forEach(Row->{
			//setando as celulas
			List<Cell> cells =(List<Cell>) toList(Row.cellIterator());
			cheques.addAll((Collection<? extends cheque>) cells);
			
		});
		
		return cheques;
		
			
	
	}
	
	public List<?> toList(Iterator<?> interator){
		return IteratorUtils.toList(interator);
		
	}
	
	public void imprimir(List<cheque> cheques) {
//		for (int i = 0; i < cheques.lastIndexOf(cheques); i++) {
			System.out.println(cheques);
			
		
		
//		}
//		//cheques.forEach(System.out::println);
		
		
		
		
	}
	
	public void name(String a,String b, String c) {
		
	}
	
}
