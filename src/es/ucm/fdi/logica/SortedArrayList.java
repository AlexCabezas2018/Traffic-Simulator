//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.logica;

import javax.naming.OperationNotSupportedException;
import java.util.Collection;
import java.util.Comparator;
import java.util.ArrayList;

public class SortedArrayList<E> extends ArrayList<E>{
	private Comparator<E> cmp;
	public SortedArrayList(Comparator<E> cmp) {
		this.cmp = cmp;
	}
	@Override
	public boolean add(E e){
		int i = 0;
		while(i < super.size() &&
				(this.cmp.compare(e, super.get(i)) == -1 || this.cmp.compare(e, super.get(i)) == 0)){
			i++;
		}
		super.add(i, e);
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c){
		for(E e: c){
			this.add(e);
		}
		return true;
	}


	public void add(int index, E element){
		try {
			throw new OperationNotSupportedException();
		} catch (OperationNotSupportedException e) {
			e.printStackTrace();
		}
	}

	public boolean addAll(int index, Collection<? extends E> e){
		try{
			throw new OperationNotSupportedException();
		}
		catch(OperationNotSupportedException ex){
			ex.printStackTrace();
		}
		return false;
	}

	public E set(int index, E element){
		try{
			throw new OperationNotSupportedException();
		}
		catch(OperationNotSupportedException e){
			e.printStackTrace();
		}
		return null;
	}

}
