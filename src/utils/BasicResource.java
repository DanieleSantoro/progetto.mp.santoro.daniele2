package utils;

import java.util.Objects;

public class BasicResource implements Resource {

	private final String name;
	
	public BasicResource(String name) {
		this.name = Objects.requireNonNull(name);
	}
	
	@Override
	public String name() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) return false;
	    if (obj.getClass() != BasicResource.class) return false;
	    BasicResource other = (BasicResource) obj; 
	    return name.equals(other.name);
	}


	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
}
