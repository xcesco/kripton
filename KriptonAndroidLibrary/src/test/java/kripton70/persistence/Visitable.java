package kripton70.persistence;

import kripton70.contexts.BinderContext;

public interface Visitable {
	
	public <E> void accept(BinderContext context, E bean, Visitor<E> visitor, boolean writeStartAndEnd);
		
}