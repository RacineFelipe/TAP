package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CompraTableModel extends AbstractTableModel{
	
	private List<Produto> dados = new ArrayList<>();
	private String[] colunas = {"Nome", "Pre�o.", "Descri��o", "Quantidade"};
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
	@Override
	public int getRowCount() {
		return dados.size();
	}
	
	@Override
	public int getColumnCount() {
		return colunas.length;
	}
	
	@Override
	public Object getValueAt(int linha, int coluna) {		
		switch(coluna) {
			case 0:
				return dados.get(linha).getNome();			
			case 1:
				return dados.get(linha).getPreco();
			case 2:
				return dados.get(linha).getDescricao();
			case 3:
				return dados.get(linha).getQuantidade();				
		}
		
		return null;
	}
	
	@Override
	public void setValueAt(Object valor, int linha, int coluna) {
		switch(coluna) {
			case 1:
				dados.get(linha).setNome((String) valor);
				break;
			case 2:
				dados.get(linha).setPreco((String) valor);
				break;
			case 3:
				//TODO: possivel criador de error
				dados.get(linha).setQuantidade(0);
				break;
			case 4:
				dados.get(linha).setDescricao((String) valor);
				break;		
		}
		
		this.fireTableRowsUpdated(linha, linha);
	}
	
	public void addRow(Produto p) {
		this.dados.add(p);
		this.fireTableDataChanged();
	}
	
	public void removeRow(int linha) {
		this.dados.remove(linha);
		this.fireTableRowsDeleted(linha, linha);		
	}
	
	public Produto getRowProduto(int linha) {
		Produto p = new Produto();
		
		p.setId_produto((int)getValueAt(linha, 0));
		p.setNome((String)getValueAt(linha, 1));
		p.setPreco((String)getValueAt(linha, 2));
		p.setQuantidade((int)getValueAt(linha, 3));
		p.setDescricao((String)getValueAt(linha, 4));
		
		return p;
	}
	
	public void updateLista(List<Produto> _dados) {			
		removeAll();
		
		for (int i = 0; i < _dados.size(); i++) {
			addRow(_dados.get(i));
		}		
	}
	
	public void removeAll() {
		for (int i = getRowCount() - 1; i >= 0; i--) {
		    removeRow(i);
		}
	}
}
