package kripton70.persistence;

import kripton70.contexts.BinderContext;

public interface Visitor<E> {
	public void visit(BinderContext context, E object, JacksonSerializer jacksonSerializer, boolean writeStartAndEnd, boolean onlyText);

	public void visit(BinderContext context, E object, XmlSerializer xmlSerializer, boolean writeStartAndEnd, boolean onlyText);
	
	public void visit(BinderContext context, E object, JacksonParser jacksonParser, boolean writeStartAndEnd, boolean onlyText);

	public void visit(BinderContext context, E object, XmlParser xmlParser, boolean writeStartAndEnd, boolean onlyText);

}