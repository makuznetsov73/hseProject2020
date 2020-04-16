package hse.project.entities;

public class Response<T> {
	
	private T data;
	
	private boolean res = false;
	
	public Response(T data, boolean res){
		this.data = data;
		this.res = res;
	}
	
	public Response(){}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public boolean isRes() {
		return res;
	}
	
	public void setRes(boolean res) {
		this.res = res;
	}
}
