package hse.project.entities.prototypes;

public interface MapperPrevInterface<E, P> extends MapperInterface<E> {
	
	P mapToPreview(E source);
}
