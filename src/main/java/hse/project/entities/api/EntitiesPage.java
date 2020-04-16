package hse.project.entities.api;

public class EntitiesPage<T> {
	
	private int pageNumber;
	
	private long pageTotal;
	
	private T entities;
	
	public EntitiesPage(int pageNumber, long pageTotal, T entities) {
		this.pageNumber = pageNumber;
		this.pageTotal = pageTotal;
		this.entities = entities;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
	
	public long getPageTotal() {
		return pageTotal;
	}
	
	public T getEntities() {
		return entities;
	}
}
