package in.fssa.doboo.Interface;

import java.util.Set;

public interface Base <T>{
	public abstract Set<T> findAll();
	public abstract void create(T object);
	public abstract void update(int id, T object);
	public abstract void delete(int id);
	public abstract T findById(int id);
}
